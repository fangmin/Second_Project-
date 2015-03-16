package com.zy.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.zy.bean.User;
import com.zy.logic.BaseAction;
import com.zy.logic.FactoryService;
import com.zy.util.AuthCode;
import com.zy.util.Constants;
import com.zy.util.DateTime;

public class UserAction extends BaseAction {
	private HttpServletRequest request = null;
	private User user;
	private FactoryService factoryService;

	public String logout() {
		request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("user");// 此处的user是login()方法中request.getSession().setAttribute("user",
														// model)时存入session的，目的是保存用户记录，便于权限控制
		return "login";
	}

	public void del() {
		factoryService.getUserService().delete(user);
		Constants.out(Constants.Success("userList", false));
	}

	public String initUpd() {
		user = factoryService.getUserService().getById(user.getId());
		System.out.println("******************"+user.getId());
		request = ServletActionContext.getRequest();
		request.setAttribute("user", user);
		//System.out.println("******************"+user);
		return "upd";
	}

	public void upd() {
		int sb = factoryService.getUserService().exist(user);
		if (sb > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			User model = factoryService.getUserService().getById(user.getId());
			/*user.setAdd_time(model.getAdd_time());*/
			user.setPassword(model.getPassword());
			factoryService.getUserService().update(user);
			Constants.out(Constants.Success("userList", true));
		}
	}

	public void dels() {
		request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");//
		factoryService.getUserService().delete(ids);
		Constants.out(Constants.Success("userList", false));
	}

	public void add() {
		int rs = factoryService.getUserService().exist(user);
		if (rs > 0) {
			Constants.out(Constants.Failure(Constants.INFO_EXIST));
		} else {
			user.setAdd_time(DateTime.getDate());// 通过JPA存入数据库
			user.setPassword("111111");// 存入数据库，此为用户默认密码
			factoryService.getUserService().save(user);// 保存用户数据进入数据库
			Constants.out(Constants.Success("userList", true));//
		}
	}

	public String initAdd() {
		// ActionContext ac = ActionContext.getContext();//获取页面上下文
		return "add";
	}

	public String list() {
		request = ServletActionContext.getRequest();
		// HttpServletRequest是接口,ServletActionContext.getRequest()得到一个实现HttpServletRequest
		// 接口的实例,HttpServletRequest request = ServletActionContext.getRequest();
		// 成立
		// 此时request 就是 一个实现了HttpServletRequest 接口的实例。
		String name = request.getParameter("name");
		request.setAttribute("name", name);

		user = new User();
		user.setName(name);

		List<User> users = null;
		int totalCount = factoryService.getUserService().count(user);
		if (totalCount > 0) {
			this.setTotalCount(totalCount);
			int start = this.getNumPerPage() * (this.getPageNum() - 1);
			users = factoryService.getUserService().get(user, start,
					this.getNumPerPage());
		}

		ActionContext ac = ActionContext.getContext();
		ac.put("users", users);
		return "list";
	}

	public String login() {
		request = ServletActionContext.getRequest();
		if (user == null)
			return "login";

		if ("".equals(user.getAccount()) || "".equals(user.getPassword())) {
			request.setAttribute("msg", "用户名或密码为空");
			return "login";
		}

		String authcode = request.getParameter("authcode").toUpperCase();
		String code = request.getSession()
				.getAttribute(Constants.RANDOMCODEKEY).toString();
		if (!code.equals(authcode)) {
			request.setAttribute("msg", "验证码输入错误");
			return "login";
		}

		// System.out.println("-----------用户名："+user.getAccount());
		// System.out.println("------------密码："+user.getPassword());
		User model = factoryService.getUserService().check(user);
		if (model == null) {
			request.setAttribute("msg", "用户名或密码错误");
			return "login";
		}

		request.getSession().setAttribute("user", model);
		return "index";
	}

	/**
	 * session会话超时登陆
	 */
	public void timeout() {
		request = ServletActionContext.getRequest();
		user = factoryService.getUserService().check(user);
		if (user == null) {
			Constants.out(Constants.Failure("用户名或密码错误"));
		}

		request.getSession().setAttribute("user", user);
		Constants.out(Constants.Success(true));
	}

	public void pwd() {
		request = ServletActionContext.getRequest();
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String pwd2 = request.getParameter("pwd2");
		User user = (User) request.getSession().getAttribute("user");
		if (!oldPwd.equals(user.getPassword())) {
			Constants.out(Constants.Failure("原密码输入错误"));
			return;
		}

		if (!newPwd.equals(pwd2)) {
			Constants.out(Constants.Failure("两次密码输入不一致"));
			return;
		}

		user.setPassword(pwd2);
		factoryService.getUserService().update(user);
		Constants.out(Constants.Success());
	}

	// 收入统计情况
	@SuppressWarnings("rawtypes")
	public String cnt() {
		request = ServletActionContext.getRequest();
		int date = Integer.parseInt(request.getParameter("date"));
		String start_time = DateTime.getDate();
		String end_time = DateTime.getDate();
		if (date == 1) {
			// 本日
		} else if (date == 2) {
			// 本周
			String[] week = DateTime.getWeek();
			start_time = week[0];
		} else if (date == 3) {
			// 本月
			start_time = DateTime.getDateTimeByFormat("yyyy-MM") + "-01";
		} else {
			// 自定义
			String s_time = request.getParameter("start_time");
			String e_time = request.getParameter("end_time");
			if (s_time != null && !"".equals(s_time)) {
				start_time = s_time;
			} else {
				start_time = "1970-01-01";
			}

			if (e_time != null && !"".equals(e_time)) {
				end_time = e_time;
			}
			request.setAttribute("start_time", s_time);
			request.setAttribute("end_time", e_time);
		}

		request.setAttribute("date", date);

		List list = factoryService.getUserService().cnt(start_time, end_time);
		ActionContext ac = ActionContext.getContext();
		ac.put("list", list);

		double sum = 0;
		if (list != null && list.size() > 0) {
			Object[] cells=null;
			for (Object row : list) {
				cells = (Object[]) row;
				/*System.out.println("----------"+cells[2]);*/
				sum +=Double.parseDouble(cells[2].toString()) ;
				/*System.out.println("-----sum累加："+sum);*/
			}
		}

		request.setAttribute("total", sum);
		return "cnt";
	}

	/**
	 * 生成验证码
	 */
	public void code() {
		request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		AuthCode randomValidateCode = new AuthCode();
		try {
			randomValidateCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*************** set and get ******************/
	public User getUser() {
		return user;
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
