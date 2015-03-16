<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">	$(function(){
		var plant="${water.plant.id}";
		//alert(earth);
		$("#plant").val(plant);
	});	<!--当页面加载完时再加载此函数，目的是选中下拉列表框中的值-->
</script>

<div class="pageContent">
	<form method="post" action="water_upd.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="water.id" value="${water.id}" />
			<div class="unit">
					<!-- 此处的name必须是id而不能是其他的属性，因为water的外键是plant_id -->
				<label>浇灌种类：</label> <select class="combox" name="water.plant.id"
					id="plant">				
					<s:iterator value="#plants" id="p">
						<option value="${p.id}">${p.kind}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>浇灌时间：</label><input type="text" name="water.add_time"
					class="date" readonly="readonly" value="${water.add_time}" />
				<%-- <input type="text" name="water.add_time" size="30"
					maxlength="20" value="${water.add_time}" /> --%>
			</div>
			<div class="unit">
				<label>备注信息：</label> <input type="text" name="water.remark"
					size="30" maxlength="20" value="${water.remark}" />
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

