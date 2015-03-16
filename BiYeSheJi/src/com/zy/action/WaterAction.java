package com.zy.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.bean.Plant;
import com.zy.bean.Water;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class WaterAction extends BaseAction {
	private HttpServletRequest request = null;
	private Water water;
	private FactoryService factoryService;

	/******************** method ****************/
	public void upd() {
		Plant plant = factoryService.getPlantService().getById(
				water.getPlant().getId());// 通过前台传过来的id获取plant表的数据
		water.setPlant(plant);
		int sb = factoryService.getWaterService().exist(water);
		System.out.println("+======================="+sb);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			// Water model = factoryService.getWaterService().getById(
			// water.getId());
			// model.setAdd_time(model.getAdd_time());
			factoryService.getWaterService().update(water);
			Constants.out(Constants.Success("waterlist", true));
		}
	}

	public String initUpd() {
		List<Plant> plants = factoryService.getPlantService().get();
		ActionContext ac = ActionContext.getContext();
		ac.put("plants", plants);
		/* System.out.println("--------------" + water.getId()); */
		water = factoryService.getWaterService().getById(water.getId());
		request = ServletActionContext.getRequest();
		request.setAttribute("water", water);
		return "upd";
	}

	public void del() {
		factoryService.getWaterService().delete(water);
		Constants.out(Constants.Success("waterlist", false));
	}

	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getWaterService().delete(ids);
		Constants.out(Constants.Success("waterlist", false));
	}

	public void add() {
		/*
		 * System.out.println("++++++++++++++++++++++++"+water.getPlant().getId()
		 * );
		 */
		Earth earth = factoryService.getEarthService().getById(
				water.getPlant().getEarth().getId());
		/*
		 * System.out.println("----------"+water.getPlant().getEarth().getId());
		 * System.out.println("--------------"+water.getPlant().getId());
		 * System.out.println("--------------------------"+water.getId());
		 */
		/*
		 * if(plant!=null)
		 * System.out.println("---------------"+plant.toString()); else
		 * System.out.println("_______________+++++++++++++++"); Earth
		 * earth=factoryService
		 * .getEarthService().getById(plant.getEarth().getId());
		 */
		/* if(earth!=null){ */
		water.getPlant().setEarth(earth);
		//System.out.println("----------" + water.getPlant().getEarth().getId());
		//System.out.println("--------------" + water.getPlant().getId());
		//System.out.println("--------------------------" + water.getId());
		/*
		 * System.out.println("---------------"+earth.toString()); }else
		 * System.out.println("----------------------earth為空");
		 */
		int rs = factoryService.getWaterService().exist(water);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/* water.setAdd_time(DateTime.getDate()) */;// 通过JPA存入数据库
			// factoryService.getEarthService().save(earth);
			factoryService.getWaterService().save(water);// 保存用户数据进入数据库
			Constants.out(Constants.Success("waterlist", true));//
		}
	}

	public String initAdd() {
		List<Earth> earths = factoryService.getEarthService().get();

		Earth earth = null;
		if (earths != null && earths.size() > 0) {
			earth = earths.get(0);
		}

		List<Plant> plants = null;
		if (earth != null) {
			Plant plant = new Plant();
			plant.setEarth(earth);
			plants = factoryService.getPlantService().get(plant);

		}

		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("earths", earths);
		ac.put("plants", plants);

		return "add";
	}

	public String list() {
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String earth_num = request.getParameter("earth_num");
		System.out.println("===========" + earth_num);
		request.setAttribute("earth_num", earth_num);

		if (earth_num != null && !"".equals(earth_num)) {
			water = new Water();
			Earth earth = new Earth();
			Plant plant = new Plant();
			earth.setEarth_num(earth_num);
			plant.setEarth(earth);
			water.setPlant(plant);
		}

		List<Water> waters = null;
		int totalCount = factoryService.getWaterService().count(water);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			waters = factoryService.getWaterService().get(water, start,
					this.getNumPerPage());
		}

		if (waters != null && waters.size() > 0) {
			Date date = new Date();
			Date add_time = null;
			long l = 0;
			String day = null;
			for (int i = 0; i < waters.size(); i++) {
				System.out.println("===========" + waters.get(i).getAdd_time());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					add_time = format.parse(waters.get(i).getAdd_time());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				l = (date.getTime() - add_time.getTime())
						/ (24 * 60 * 60 * 1000);
				day = Long.toString(l);
				waters.get(i).setOverTime_remind(day);
			}
		}

		ActionContext ac = ActionContext.getContext();
		ac.put("waters", waters);
		return "list";
	}

	/**************** set and get *****************/
	public Water getWater() {
		return water;
	}

	public void setWater(Water water) {
		this.water = water;
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
}
