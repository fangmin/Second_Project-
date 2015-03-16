<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form id="pagerForm" onsubmit="return navTabSearch(this);" method="post"
	action="user_list.do">
	<input type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="pageNum" value="1" />

	<div class="pageHeader">
		<!--<搜索栏>-->
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>姓名：<input type="text" name="name" value="${name}" />
					</td>
					<td><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<!--</搜索栏>-->
	</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="dialog" href="user_initAdd.do"><span>
						添加</span> </a></li>
			<li><a class="delete" href="user_dels.do" posttype="string"
				rel="ids" target="selectedTodo" title="确定删除这些记录?"><span>批量删除</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layouth="113">
		<thead>
			<tr>
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th>真实姓名</th>
				<th>登陆帐号</th>
				<th>联系电话</th>
				<th>身份证号码</th>
				<th>联系地址</th>
				<th>联系邮箱</th>
				<th>权限</th>
				<th>创建时间</th>
				<th>在用/停用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="u" value="#users">
				<tr>
					<td><input name="ids" value="${u.id }" type="checkbox">
					</td>
					<td>${u.name }</td>
					<td>${u.account}</td>
					<td>${u.phone}</td>
					<td>${u.idCard}</td>
					<td>${u.address}</td>
					<td>${u.email}</td>
					<td><s:if test="#u.power==0">用户</s:if> <s:else>管理员</s:else>
					</td>
					<td>${u.add_time}</td>
					<td><s:if test="#u.is_use==0">在用</s:if> <s:else>停用</s:else>
					</td>
					<td><a title="删除" target="ajaxTodo"
						href="user_del.do?user.id=${u.id}" class="btnDel">删除</a> <a
						title="编辑" target="dialog" href="user_initUpd.do?user.id=${u.id}"
						class="btnEdit">编辑</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option <s:if test="numPerPage==20">selected="selected"</s:if>
					value="20">20</option>
				<option <s:if test="numPerPage==50">selected="selected"</s:if>
					value="50">50</option>
				<option <s:if test="numPerPage==100">selected="selected"</s:if>
					value="100">100</option>
				<option <s:if test="numPerPage==200">selected="selected"</s:if>
					value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="${ pageNumShown}"
			currentPage="${pageNum}"></div>
	</div>
</div>