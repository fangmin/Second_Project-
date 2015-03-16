<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<form method="post" action="food_add.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<%-- <div class="unit">
				<label>土地编号：</label> <select class="combox"
					name="water.plant.earth.id" id="earth">
					<s:iterator value="#plants" id="p">
						<option value="${p.earth.id}">${p.earth.earth_num}</option>
					</s:iterator>
				</select>
			</div> --%>
			<div class="unit">
				<label>土地面积：</label> <input type="text" name="food.area"
					size="30" maxlength="20" class="required"/>
			</div>
			<div class="unit">
				<label>粮食种类：</label> <select class="combox" name="food.plant.id">
					<s:iterator value="#plants" id="p">
						<option value="${p.id}">${p.kind}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>收获数量：</label> <input type="text" name="food.number"
					size="30" maxlength="20" class="required"/>
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

