<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form id="pagerForm" onsubmit="return navTabSearch(this);" method="post"
	action="earth_list.do">
	<input type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="pageNum" value="1" />

	<div class="pageHeader">
		<!--<搜索栏>-->
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>土地编号：<input type="text" name="earth_num"
						value="${earth_num}" /></td>
					<td><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!--</搜索栏>-->
	</div>
</form>
<s:if test="#session.user.power==1">
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="dialog" href="earth_initAdd.do"><span>
						添加</span> </a>
			</li>
			<li><a class="delete" href="earth_dels.do" posttype="string"
				rel="ids" target="selectedTodo" title="确定删除这些记录?"><span>批量删除</span>
			</a>
			</li>
		</ul>
	</div>
</s:if>
	<table class="table" width="100%" layouth="113">
		<thead>
			<tr>
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th>土地编号</th>
				<th>土地面积(亩)</th>
				<th>土地位置</th>
				<s:if test="#session.user.power==1">
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="e" value="#earths">
				<tr>
					<td><input name="ids" value="${e.id }" type="checkbox">
					</td>
					<td>${e.earth_num }</td>
					<td>${e.area}</td>
					<td>${e.position}</t>
					<s:if test="#session.user.power==1">
					<td><a title="删除" target="ajaxTodo"
						href="earth_del.do?earth.id=${e.id}" class="btnDel">删除</a> <a
						title="编辑" target="dialog"
						href="earth_initUpd.do?earth.id=${e.id}" class="btnEdit">编辑</a>
					</td>
					</s:if>
				</tr>
			</s:iterator>
			<!-- <tr><td colspan="5">abc</td></tr> -->
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