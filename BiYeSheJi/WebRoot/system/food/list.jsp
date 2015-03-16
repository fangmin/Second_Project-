<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form id="pagerForm" onsubmit="return navTabSearch(this);" method="post"
	action="food_list.do">
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

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="dialog" href="food_initAdd.do"><span>
						添加</span> </a>
			</li>
			<li><a class="delete" href="food_dels.do" posttype="string"
				rel="ids" target="selectedTodo" title="确定删除这些记录?"><span>批量删除</span>
			</a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layouth="113">
		<thead>
			<tr>
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th>土地编号</th>
				<th>土地面积(单位：亩)</th>
				<th>粮食种类</th>
				<th>收获数量(公斤)</th>
				<th>平均产量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="f" value="#foods">
				<tr>
					<td><input name="ids" value="${f.id }" type="checkbox">
					</td>
					<td>${f.plant.earth.earth_num }</td>
					<td>${f.area}</td>
					<td>${f.plant.kind}</td>
					<td>${f.number}</td>
					<td>${f.aver_product}</td>
					<td><a title="删除" target="ajaxTodo"
						href="food_del.do?food.id=${f.id}" class="btnDel">删除</a> <a
						title="编辑" target="dialog"
						href="food_initUpd.do?food.id=${f.id}" class="btnEdit">编辑</a></td>
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