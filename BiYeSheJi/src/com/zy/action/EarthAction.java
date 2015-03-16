package com.zy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class EarthAction extends BaseAction {
	private HttpServletRequest request = null;
	private Earth earth;
	private FactoryService factoryService;
	
	public void upd() {
		int sb = factoryService.getEarthService().exist(earth);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			factoryService.getEarthService().update(earth);
			Constants.out(Constants.Success("earthlist", true));
		}
	}
	public String initUpd(){
		earth = factoryService.getEarthService().getById(earth.getId());
		request = ServletActionContext.getRequest();
		request.setAttribute("earth", earth);
		return "upd";
	}

	public void del() {
		factoryService.getEarthService().delete(earth);
		Constants.out(Constants.Success("earthlist", false));
	}
	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");//
		factoryService.getEarthService().delete(ids);
		Constants.out(Constants.Success("earthlist", false));
	}
	public void add() {
		int rs = factoryService.getEarthService().exist(earth);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			factoryService.getEarthService().save(earth);//保存用户数据进入数据库
			Constants.out(Constants.Success("earthlist", true));//
		}
	}
	public String initAdd() {
		//ActionContext ac = ActionContext.getContext();//获取页面上下文
		return "add";
	}
	public String list() {
		System.out.println("----------进入");
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String earth_num = request.getParameter("earth_num");
		System.out.println("..................................."+earth_num);
		request.setAttribute("earth_num", earth_num);

		earth = new Earth();
		earth.setEarth_num(earth_num);

		List<Earth> earths = null;
		int totalCount = factoryService.getEarthService().count(earth);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			earths = factoryService.getEarthService().get(earth, start,
					this.getNumPerPage());
		}

		ActionContext ac = ActionContext.getContext();
		ac.put("earths", earths);
		/*if(earths!=null){
			System.out.println("................"+earths.size());
		}*/
		
		return "list";
	}

	/************** set and get **********************/

	public Earth getEarth() {
		return earth;
	}

	public void setEarth(Earth earth) {
		this.earth = earth;
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
}
