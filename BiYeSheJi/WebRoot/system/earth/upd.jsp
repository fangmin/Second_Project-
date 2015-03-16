<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
<!--
	$(function() {
		var power = "${user.power}";
		$("#power").val(power);
	});
//-->
</script>
<div class="pageContent">
	<form method="post" action="earth_upd.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="earth.id" value="${earth.id}" />
			<div class="unit">
				<label>土地编号：</label> <input type="text" name="earth.earth_num"
					size="30" maxlength="20" class="required"
					value="${earth.earth_num}" />
			</div>
			<div class="unit">
				<label>土地面积：</label> <input type="text" name="earth.area" size="30"
					maxlength="20" value="${earth.area}" />&nbsp;&nbsp;(单位;亩)
			</div>
			<div class="unit">
				<label>土地位置：</label> <input type="text" name="earth.position"
					size="30" maxlength="20" value="${earth.position}" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

