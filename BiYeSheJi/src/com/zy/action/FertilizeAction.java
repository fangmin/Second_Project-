package com.zy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.bean.Fertilize;
import com.zy.bean.Plant;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class FertilizeAction extends BaseAction {
	private HttpServletRequest request = null;
	private Fertilize fertilize;
	private FactoryService factoryService;

	/*********************** method ******************/

	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getFertilizeService().delete(ids);
		Constants.out(Constants.Success("fertilizelist", false));
	}

	public void add() {
		Earth earth=factoryService.getEarthService().getById(fertilize.getEarth().getId());//通过前台传过来的id获取plant表的数据
		fertilize.setEarth(earth);
		int rs = factoryService.getFertilizeService().exist(fertilize);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*fertilize.setAdd_time(DateTime.getDate());*/// 通过JPA存入数据库
			factoryService.getFertilizeService().save(fertilize);// 保存用户数据进入数据库
			Constants.out(Constants.Success("fertilizelist", true));//
		}
	}

	public void upd() {
		Earth earth=factoryService.getEarthService().getById(fertilize.getEarth().getId());//通过前台传过来的id获取plant表的数据
		fertilize.setEarth(earth);
		int sb = factoryService.getFertilizeService().exist(fertilize);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*Fertilize model = factoryService.getFertilizeService().getById(
					fertilize.getId());
			model.setAdd_time(model.getAdd_time());*/
			factoryService.getFertilizeService().update(fertilize);
			Constants.out(Constants.Success("fertilizelist", true));
		}
	}

	public void del() {
		factoryService.getFertilizeService().delete(fertilize);
		Constants.out(Constants.Success("fertilizelist", false));
	}

	public String initUpd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();
		ac.put("earths", earths);
		/*System.out.println("--------------" + fertilize.getId());*/
		fertilize = factoryService.getFertilizeService().getById(fertilize.getId());
		request = ServletActionContext.getRequest();
		return "upd";
	}

	public String initAdd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("earths", earths);
		/*System.out.println("--------" + earths.size());*/
		return "add";
	}

	public String list() {
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String earth_num = request.getParameter("earth_num");
		/*System.out.println("===========" + earth_num);*/
		request.setAttribute("earth_num", earth_num);

		if (earth_num != null && !"".equals(earth_num)) {
			fertilize = new Fertilize();
			Earth earth = new Earth();
			earth.setEarth_num(earth_num);
			fertilize.setEarth(earth);
		}

		List<Fertilize> fertilizes = null;
		int totalCount = factoryService.getFertilizeService().count(fertilize);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			fertilizes = factoryService.getFertilizeService().get(fertilize, start,
					this.getNumPerPage());
		}
		ActionContext ac = ActionContext.getContext();
		ac.put("fertilizes", fertilizes);
		return "list";
	}

	/**************** set and get *********************/
	

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public Fertilize getFertilize() {
		return fertilize;
	}

	public void setFertilize(Fertilize fertilize) {
		this.fertilize = fertilize;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

}
