package com.zy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zy.bean.User;

public class PageInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1514075296720933629L;

	private static Logger log = Logger.getLogger(PageInterceptor.class
			.getName());

	@Override
	public void init() {
		log.info("拦截开始");
		super.init();
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getServletPath();
		HttpSession session = request.getSession();
		log.info(url);

		if (url.startsWith("/user_login")) {
			if (session.getAttribute(Constants.RANDOMCODEKEY) == null)
				return "login";
		}

		if (url.startsWith("/user_code") || url.startsWith("/user_login")
				|| url.startsWith("/user_timeout")) {
			return arg0.invoke();
		}

		User user = (User) session.getAttribute("user");
		if (user == null) {// 转入管理员登陆页面
			Constants.out(Constants.Timeout());
			return null;
		}
		return arg0.invoke();
	}

}
