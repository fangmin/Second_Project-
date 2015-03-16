package com.zy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.Food;
import com.zy.bean.Plant;
import com.zy.bean.Sell;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.Constants;

public class SellAction extends BaseAction {
	private HttpServletRequest request = null;
	private Sell sell;
	private FactoryService factoryService;

	/******************** method ****************/

	public void upd() {
		Food food = factoryService.getFoodService().getById(
				sell.getFood().getId());
		Plant plant = factoryService.getPlantService().getById(
				food.getPlant().getId());
		sell.getFood().setPlant(plant);
		int sb = factoryService.getSellService().exist(sell);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			// Water model = factoryService.getWaterService().getById(
			// water.getId());
			// model.setAdd_time(model.getAdd_time());
			factoryService.getSellService().update(sell);
			Constants.out(Constants.Success("selllist", true));
		}
	}

	public String initUpd() {// 初始更新
		/*
		 * List<Plant> plants = factoryService.getPlantService().get();
		 * ActionContext ac = ActionContext.getContext(); ac.put("plants",
		 * plants);
		 */
		/* System.out.println("--------------" + water.getId()); */
		List lists = factoryService.getPlantService().get_Sell();
		/*
		 * System.out.println("==========++++++++++"+lists.toString());
		 * System.out.println("==========++++++++++"+lists);
		 */
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("lists", lists);
		sell = factoryService.getSellService().getById(sell.getId());
		request = ServletActionContext.getRequest();
		return "upd";
	}

	public void del() {
		factoryService.getSellService().delete(sell);
		Constants.out(Constants.Success("selllist", false));
	}

	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		factoryService.getSellService().delete(ids);
		Constants.out(Constants.Success("selllist", false));
	}

	/*
	 * 判断当前的售出总量是否超过收入总量
	 */
	public void comparison() {
		request = ServletActionContext.getRequest();
		int earthId = Integer.parseInt(request.getParameter("earthId"));
		/* System.out.println("===================" + earthId); */
		List listss = factoryService.getPlantService().get_Sell();
		if (listss != null && listss.size() > 0) {
			Object[] cells = null;
			int x = 0;
			double y = 0;
			for (int i = 0; i < listss.size(); i++) {
				cells = (Object[]) listss.get(i);
				x = Integer.parseInt(cells[0].toString());
				/* System.out.println("------=====------====" + cells[0]); */
				if (x == earthId) {
					y = Double.valueOf(cells[2].toString());
					/*
					 * y = Integer.parseInt(new
					 * java.text.DecimalFormat("0").format(x));
					 */
					Constants.out(y);
					/* System.out.println("-------------------"+cells[2]); */
					break;

				}
			}

		}
	}

	public void add() {
		Food food = factoryService.getFoodService().getById(
				sell.getFood().getId());
		Plant plant = factoryService.getPlantService().getById(
				food.getPlant().getId());// 通过前台传过来的id获取plant表的数据
		/*
		 * System.out.println("================="+sell.getFood().getId());
		 * System.out.println("========================="+sell.getId());
		 */
		if (plant != null) {
			sell.getFood().setPlant(plant);
		}
		// System.out.println("================="+sell.getFood().getId());
		// System.out.println("========================="+sell.getId());
		int rs = factoryService.getSellService().exist(sell);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			/* water.setAdd_time(DateTime.getDate()) */;// 通过JPA存入数据库
			factoryService.getSellService().save(sell);// 保存用户数据进入数据库,没有food id
			Constants.out(Constants.Success("selllist", true));
		}
	}

	@SuppressWarnings("rawtypes")
	public String initAdd() {// 初始添加
		List lists = factoryService.getPlantService().get_Sell();
		ActionContext ac = ActionContext.getContext();// 获取页面上下文
		ac.put("lists", lists);

		/* System.out.println("--------" + earths.size()); */
		return "add";
	}

	public String list() {
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String kind = request.getParameter("kind");
		/* System.out.println("===========" + earth_num); */
		request.setAttribute("kind", kind);

		if (kind != null && !"".equals(kind)) {
			sell = new Sell();
			Food food = new Food();
			Plant plant = new Plant();
			plant.setKind(kind);
			food.setPlant(plant);
			sell.setFood(food);
		}

		List<Sell> sells = null;
		int totalCount = factoryService.getSellService().count(sell);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			sells = factoryService.getSellService().get(sell, start,
					this.getNumPerPage());
		}
		if (sells != null && sells.size() > 0) {
			double total_amount = 0;
			for (int i = 0; i < sells.size(); i++) {
				/* System.out.println("==========="+foods.get(i).getArea()); */
				total_amount = (sells.get(i).getNumber())
						* (sells.get(i).getPrice());
				sells.get(i).setTotal_amount(total_amount);
			}
		}
		ActionContext ac = ActionContext.getContext();
		ac.put("sells", sells);
		return "list";
	}

	/**************** set and get *****************/

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

}
