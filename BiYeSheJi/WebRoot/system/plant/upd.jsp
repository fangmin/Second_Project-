<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">
<!--
	$(function(){
		var earth="${plant.earth.id}";
		$("#earth").val(earth);
	});	<!--当页面加载完时再加载此函数，目的是选中下拉列表框中的值-->
//-->
</script>

<div class="pageContent">
	<form method="post" action="plant_upd.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="plant.id" value="${plant.id }" />
			<div class="unit">
				<label>土地编号：</label> <select class="combox" name="plant.earth.id"
					id="earth">
					<s:iterator value="#earths" id="e">
						<option value="${e.id}">${e.earth_num}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>耕种种类：</label> <input type="text" name="plant.kind" size="30"
					maxlength="20" value="${plant.kind}" />
			</div>
			<div class="unit">
				<label>耕种时间：</label><input type="text" name="plant.add_time"
						class="date" readonly="readonly" value="${plant.add_time}" /> <%-- <input type="text" name="plant.add_time" size="30"
					maxlength="20" value="${plant.add_time}" /> --%>
			</div>
			<div class="unit">
				<label>备注信息：</label> <input type="text" name="plant.remark"
					size="30" maxlength="20" value="${plant.remark}" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

