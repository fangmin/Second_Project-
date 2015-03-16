<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form>
	<div class="unitBox"
		style="float:left; display:block; overflow:auto; width:100%;">
		<div class="panel" defH="90%">
			<h1>个人信息</h1>
			<div class="pageFormContent">
				<div class="unit">
					<label>真实姓名：</label><label>${user.name }</label>
				</div>
				<div class="unit" id="div_add_type">
					<label>登陆账号：</label><label>${user.account }</label>
				</div>
				<div class="unit" id="div_add_type">
					<label>联系电话：</label><label>${user.phone}</label>
				</div>
				<s:if test="#session.user.power==0">
				<div class="unit" id="div_add_type">
					<label>身份证号码：</label><label>${user.idCard}</label>
				</div>
				</s:if>
				<div class="unit" id="div_add_type">
					<label>联系地址：</label><label>${user.address}</label>
				</div>
				<div class="unit" id="div_add_type">
					<label>联系邮箱：</label><label>${user.email }</label>
				</div>
				<div class="unit" id="div_add_type">
					<label>职务权限： </label> <label><s:if test="#session.user.power==0">用户</s:if>
						<s:else>管理员</s:else>
					</label>
				</div>
				<div class="unit" id="div_add_type">
					<label>在用/停用： </label> <label><s:if test="#session.user.is_use==0">在用</s:if>
						<s:else>停用</s:else>
					</label>
				</div>
			</div>
		</div>
	</div>
</form>