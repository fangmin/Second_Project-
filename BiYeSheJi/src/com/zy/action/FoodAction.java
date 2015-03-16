package com.zy.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Earth;
import com.zy.bean.Food;
import com.zy.bean.Plant;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class FoodAction extends BaseAction {
	private HttpServletRequest request = null;
	private Food food;
	private FactoryService factoryService;

	/******************** method ****************/
	
	public void upd() {
		Plant plant=factoryService.getPlantService().getById(food.getPlant().getId());
		food.setPlant(plant);
		int sb = factoryService.getFoodService().exist(food);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
//			Water model = factoryService.getWaterService().getById(
//					water.getId());
//			model.setAdd_time(model.getAdd_time());
			factoryService.getFoodService().update(food);
			Constants.out(Constants.Success("foodlist", true));
		}
	}
	
	public String initUpd() {
		List<Plant> plants = factoryService.getPlantService().get();
		ActionContext ac = ActionContext.getContext();
		ac.put("plants", plants);
		/*System.out.println("--------------" + water.getId());*/
		food = factoryService.getFoodService().getById(food.getId());
		request = ServletActionContext.getRequest();
		return "upd";
	}
	
	public void del() {
		factoryService.getFoodService().delete(food);
		Constants.out(Constants.Success("foodlist", false));
	}
	
	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getFoodService().delete(ids);
		Constants.out(Constants.Success("foodlist", false));
	}

	public void add() {
		Plant plant=factoryService.getPlantService().getById(food.getPlant().getId());
		food.setPlant(plant);
		int rs = factoryService.getFoodService().exist(food);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/*water.setAdd_time(DateTime.getDate())*/;// 通过JPA存入数据库
			factoryService.getFoodService().save(food);// 保存用户数据进入数据库
			Constants.out(Constants.Success("foodlist", true));
		}
	}

	public String initAdd() {
		List<Plant> plants = factoryService.getPlantService().get();
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("plants", plants);
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
		/* System.out.println("===========" + earth_num); */
		request.setAttribute("earth_num", earth_num);

		if (earth_num != null && !"".equals(earth_num)) {
			food = new Food();
			Earth earth = new Earth();
			Plant plant = new Plant();
			earth.setEarth_num(earth_num);
			plant.setEarth(earth);
			food.setPlant(plant);
		}

		List<Food> foods = null;
		int totalCount = factoryService.getFoodService().count(food);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			foods = factoryService.getFoodService().get(food, start,
					this.getNumPerPage());
		}
		if (foods != null && foods.size() > 0) {
			double aver_product = 0;
			for (int i = 0; i < foods.size(); i++) {
				/*System.out.println("==========="+foods.get(i).getArea());*/
				aver_product =(foods.get(i).getNumber())/(foods.get(i).getArea());
				foods.get(i).setAver_product(aver_product);
			}
		}
		ActionContext ac = ActionContext.getContext();
		ac.put("foods", foods);
		return "list";
	}

	/**************** set and get *****************/

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
}
