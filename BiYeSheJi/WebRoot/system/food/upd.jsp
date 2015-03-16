<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">
<!--
	$(function(){
		var plant="${food.plant.id}";
		$("#plant").val(plant);
	});	<!--当页面加载完时再加载此函数，目的是选中下拉列表框中的值-->
//-->
</script>

<div class="pageContent">
	<form method="post" action="food_upd.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="food.id" value="${food.id}" />
			<div class="unit">
				<label>粮食种类：</label> <select class="combox" name="food.plant.id"
					id="plant"><!-- 此处的name必须是id而不能是其他的属性，因为water的外键是plant_id -->
					<s:iterator value="#plants" id="p">
						<option value="${p.id}">${p.kind}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>土地面积：</label> <input type="text" name="food.area" size="30"
					maxlength="20" value="${food.area}" />
			</div>
			<div class="unit">
				<label>收获数量：</label> <input type="text" name="food.number"
					size="30" maxlength="20" value="${food.number}" />
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

