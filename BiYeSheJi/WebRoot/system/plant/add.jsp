<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<form method="post" action="plant_add.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>土地编号：</label> <select class="combox" name="plant.earth.id">
					<s:iterator value="#earths" id="e">
						<option value="${e.id}">${e.earth_num}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>耕种种类：</label> <input type="text" name="plant.kind" size="30"
					maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>备注信息：</label> <input type="text" name="plant.remark"
					size="30" maxlength="20" />
			</div>
			<div class="unit">
				<label>耕种时间：</label><input type="text" name="plant.add_time"
						class="date required" readonly="readonly" /> <!--<input type="text" name="plant.remark"
					size="30" maxlength="20" /> -->
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

