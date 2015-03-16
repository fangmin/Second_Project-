<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.*,com.zy.dao.*,com.zy.bean.*" %>

 <%--
   List list = new ArrayList();
   FoodDao dao = new FoodDao();
   list = dao.get();
   Food food = null;
   for(int i=0;i< list.size();i++){
       food = (Food)list.get(i);
   }
   
   User user = (User)request.getAttribute("user");
 --%>
<div class="accordion" fillspace="system">
<s:if test="#session.user.power==1">
	<div class="accordionHeader">
		<h2>
			<span>Folder</span>普通用户管理
		</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<%-- 管理员权限 开始 --%>
				<li><a href="user_list.do" target="navTab" rel="userList">普通用户管理</a>
				</li>
		</ul>
    </div>
</s:if>
	<div class="accordionHeader">
		<h2>
			<span>Folder</span>密码信息管理
		</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			    <li><a href="system/user/pwd.jsp" target="navTab" rel="pwd">修改个人密码</a>
			    </li>
		</ul>
	</div>

	
	<div class="accordionHeader">
		<h2>
			<span>Folder</span>个人信息管理
		</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			    <li><a href="system/user/view.jsp" target="navTab" rel="view">查看信息</a>
			    </li>
		</ul>
	</div>

</div>