package com.zy.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.bean.Spray;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class SprayAction extends BaseAction {
	private HttpServletRequest request = null;
	private Spray spray;
	private FactoryService factoryService;

	/******************** method ****************/
	
	public void upd() {
		Earth earth=factoryService.getEarthService().getById(spray.getEarth().getId());
		spray.setEarth(earth);
		int sb = factoryService.getSprayService().exist(spray);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*Spray model = factoryService.getSprayService().getById(
					spray.getId());
			model.setAdd_time(model.getAdd_time());*/
			factoryService.getSprayService().update(spray);
			Constants.out(Constants.Success("spraylist", true));
		}
	}
	
	public String initUpd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();
		ac.put("earths", earths);
		/*System.out.println("--------------" + water.getId());*/
		spray = factoryService.getSprayService().getById(spray.getId());
		request = ServletActionContext.getRequest();
		request.setAttribute("spray", spray);
		return "upd";
	}
	
	public void del() {
		factoryService.getSprayService().delete(spray);
		Constants.out(Constants.Success("spraylist", false));
	}
	
	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getSprayService().delete(ids);
		Constants.out(Constants.Success("spraylist", false));
	}

	public void add() {
		Earth earth=factoryService.getEarthService().getById(spray.getEarth().getId());
		spray.setEarth(earth);
		int rs = factoryService.getSprayService().exist(spray);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*spray.setAdd_time(DateTime.getDate());*/// 通过JPA存入数据库
			factoryService.getSprayService().save(spray);// 保存用户数据进入数据库
			Constants.out(Constants.Success("spraylist", true));//
		}
	}

	public String initAdd() {
		List<Earth> earths = factoryService.getEarthService().get();
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("earths", earths);
		/* System.out.println("--------" + earths.size()); */
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
			spray = new Spray();
			Earth earth = new Earth();
			earth.setEarth_num(earth_num);
			spray.setEarth(earth);
		}

		List<Spray> sprays = null;
		int totalCount = factoryService.getSprayService().count(spray);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			sprays = factoryService.getSprayService().get(spray, start,
					this.getNumPerPage());
		}

		if (sprays != null && sprays.size() > 0) {
			Date date = new Date();
			Date add_time = null;
			long l = 0;
			String day = null;
			for (int i = 0; i < sprays.size(); i++) {
				System.out.println("==========="+sprays.get(i).getAdd_time());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					add_time =format.parse(sprays.get(i).getAdd_time());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				l = (date.getTime() - add_time.getTime())
						/ (24 * 60 * 60 * 1000);
				day = Long.toString(l);
				sprays.get(i).setOverTime_remind(day);
			}
		}

		ActionContext ac = ActionContext.getContext();
		ac.put("sprays", sprays);
		return "list";
	}

	/**************** set and get *****************/

	
	public FactoryService getFactoryService() {
		return factoryService;
	}

	public Spray getSpray() {
		return spray;
	}

	public void setSpray(Spray spray) {
		this.spray = spray;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
}
