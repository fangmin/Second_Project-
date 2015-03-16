package com.zy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.bean.Plant;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class PlantAction extends BaseAction {
	private HttpServletRequest request = null;
	private Plant plant;
	private FactoryService factoryService;

	/*********************** method ******************/

	@SuppressWarnings("rawtypes")
	public void get() {
		request = ServletActionContext.getRequest();
		int earth_id = Integer.parseInt(request.getParameter("earth_id"));
		Earth earth = new Earth();
		earth.setId(earth_id);
		Plant plant = new Plant();
		plant.setEarth(earth);
		List<Plant> plants = factoryService.getPlantService().get(plant);

		if (plants != null && plants.size() > 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			List<Map> list = new ArrayList<Map>();
			for (int i = 0; i < plants.size(); i++) {
				plant = plants.get(i);
				map.put("id", plant.getId());
				map.put("kind", plant.getKind());
				list.add(map);
			}
			Constants.out(JSONArray.fromObject(map));
		} else {
			Constants.out(null);
		}

	}

	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getPlantService().delete(ids);
		Constants.out(Constants.Success("plantlist", false));
	}

	public void add() {

		Earth earth = factoryService.getEarthService().getById(
				plant.getEarth().getId());
		plant.setEarth(earth);
		int rs = factoryService.getPlantService().exist(plant);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/* plant.setAdd_time(DateTime.getDate()); */// 通过JPA存入数据库
			factoryService.getPlantService().save(plant);// 保存用户数据进入数据库
			Constants.out(Constants.Success("plantlist", true));//
		}
	}

	public void upd() {
		Earth earth = factoryService.getEarthService().getById(
				plant.getEarth().getId());
		plant.setEarth(earth);
		int sb = factoryService.getPlantService().exist(plant);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*
			 * Plant model = factoryService.getPlantService().getById(
			 * plant.getId()); model.setAdd_time(model.getAdd_time());
			 */
			factoryService.getPlantService().update(plant);
			Constants.out(Constants.Success("plantlist", true));
		}
	}

	public void del() {
		factoryService.getPlantService().delete(plant);
		Constants.out(Constants.Success("plantlist", false));
	}

	public String initUpd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();
		ac.put("earths", earths);
		//System.out.println("--------------" + plant.getId());
		plant = factoryService.getPlantService().getById(plant.getId());
		request = ServletActionContext.getRequest();
		return "upd";
	}

	public String initAdd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("earths", earths);
		System.out.println("--------" + earths.size());
		return "add";
	}

	public String list() {
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String earth_num = request.getParameter("earth_num");
		/* System.out.println("===========" + earth_num); */
		request.setAttribute("earth_num", earth_num);

		if (earth_num != null && !"".equals(earth_num)) {
			plant = new Plant();
			Earth earth = new Earth();
			earth.setEarth_num(earth_num);
			plant.setEarth(earth);
		}

		List<Plant> plants = null;
		int totalCount = factoryService.getPlantService().count(plant);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			plants = factoryService.getPlantService().get(plant, start,
					this.getNumPerPage());
		}
		ActionContext ac = ActionContext.getContext();
		ac.put("plants", plants);
		return "list";
	}

	/**************** set and get *********************/
	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

}
