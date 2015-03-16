<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<form method="post" action="user_add.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>&nbsp;</label> <label>初始密码为111111</label>
			</div>
			<div class="unit">
				<label>真实姓名：</label> <input type="text" name="user.name" size="30"
					maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>登陆账号：</label> <input type="text" name="user.account" size="30"
					maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>联系电话：</label> <input type="text" name="user.phone" size="30"
					maxlength="20" />
			</div>
			<div class="unit">
				<label>身份证号码：</label> <input type="text" name="user.idCard" size="30"
					maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>联系地址：</label> <input type="text" name="user.address" size="30"
					maxlength="30" />
			</div>
			<div class="unit">
				<label>联系邮箱：</label> <input type="text" name="user.email" size="30"
					maxlength="20" />
			</div>
			<div class="unit">
				<label>权限：</label> <select class="combox" name="user.power">
					<option value="0">用户</option>
					<option value="1">管理员</option>
				</select>
			</div>
			<div class="unit">
				<label>在用/停用：</label> <select class="combox" name="user.is_use">
					<option value="0">在用</option>
					<option value="1">停用</option>
				</select>
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

