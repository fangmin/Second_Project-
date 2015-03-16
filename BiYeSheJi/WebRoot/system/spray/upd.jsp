<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">
<!--
	$(function(){
		var earth="${spray.earth.id}";
		$("#earth").val(earth);
	});	<!--当页面加载完时再加载此函数，目的是选中下拉列表框中的值-->
//-->
</script>

<div class="pageContent">
	<form method="post" action="spray_upd.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="spray.id" value="${spray.id}" />
			<div class="unit">
				<label>土地编号：</label> <select class="combox" name="spray.earth.id"
					id="plant"><!-- 此处的name必须是id而不能是其他的属性，因为water的外键是plant_id -->
					<s:iterator value="#earths" id="e">
						<option value="${e.id}">${e.earth_num}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>喷药时间：</label> <input type="text" name="spray.add_time"
						class="date" readonly="readonly" value="${spray.add_time }"/>
			</div>
			<div class="unit">
				<label>喷药种类：</label> <input type="text" name="spray.kind" size="30"
					maxlength="20" value="${spray.kind}" />
			</div>
			<div class="unit">
				<label>备注信息：</label> <input type="text" name="spray.remark"
					size="30" maxlength="20" value="${spray.remark}" />
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

