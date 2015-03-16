<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function pwd(){
		var pwd="${user.password}";
		var oldPwd=$("#oldPwd").val();
		var newPwd=$("#newPwd").val();
		var pwd2=$("#pwd2").val();
		if(newPwd!=pwd2){
			var json={"statusCode":"300","message":"操作失败！<br />密码两次输入不一致！"};
			DWZ.ajaxDone(json);
			return;
		}
		if(oldPwd!= pwd){
			var json={"statusCode":"300","message":"操作失败！<br />原密码错误！"};
			DWZ.ajaxDone(json);
			return;
		}
		$("#myform").submit();
	}
</script> 

<form id="myform" method="post" action="user_pwd.do"
	onsubmit="return validateCallback(this);">
	<div class="unitBox"
		style="float:left; display:block; overflow:auto; width:100%;">
		<div class="panel" defH="90%">
			<h1>修改密码</h1>
			<div class="pageFormContent">
				<div class="unit">
					<label>原密码：</label><input type="password" name="oldPwd" size="30"
						maxlength="20" class="required" id="oldPwd" />
				</div>
				<div class="unit" id="div_add_type">
					<label>新密码：</label><input type="password" name="newPwd" size="30"
						maxlength="20" class="required" id="newPwd" />
				</div>
				<div class="unit" id="div_add_type">
					<label>确认密码：</label><input type="password" name="pwd2" size="30"
						maxlength="20" class="required" id="pwd2" />
				</div>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="pwd();">确认修改</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</form>