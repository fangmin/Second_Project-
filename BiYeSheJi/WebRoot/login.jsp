<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>土地信息管理系统</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script type="text/javascript">
	//页面加载完成后设置焦点
	$(document).ready(function() {
		$("#account").focus();
	});

	//切换验证码
	function chgAuth() {
		$("#imgauthcode").attr("src",
				"user_code.do?t=" + new Date().getMilliseconds());
	}

	//登陆
	function login() {
		var acct = $("#account").val();
		var pwd = $("#password").val();//关于jquery中使用.val()取值，
		var auth = $("#authcode").val();

		if (acct.length < 1) {
			$("#msg").html("请输入用户名");
			$("#account").focus();
		} else if (pwd.length < 1) {
			$("#msg").html("请输入密码");
			$("#password").focus();
		} else if (auth.length < 1) {
			$("#msg").html("请输入验证码");
			$("#authcode").focus();
		} else {
			$("#theform").submit();
		}
	}

	//回车键提交
	function enterKey() {
		if (event.keyCode == 13) {//event是js一个对象,用于处理事件.它的属性keyCode就是这个激发这个事件的键盘的键码.keyCode == 13代表按下了enter回车键
			login();
		}
	}
</script>
</head>

<body onkeypress="enterKey();">
	<div id="logo"></div>
	<div id="main">
		<form action="user_login.do" method="post" id="theform">
			<table width="600" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="90" height="50" align="right">用户名：</td>
					<td width="300" height="50"><input type="text"
						name="user.account" id="account" class="input_box" tabindex="1" value="${user.account}" />
					</td>
					<td width="210" rowspan="4" align="center"><input
						name="button" type="button" class="but_box" value=" "
						onclick="login();" tabindex="4" /></td>
				</tr>
				<tr>
					<td width="90" height="50" align="right">密码：</td>
					<td width="300" height="50"><input type="password"
						name="user.password" id="password" class="input_box" tabindex="2" value="${user.password}" />
					</td>
				</tr>
				<tr>
					<td width="90" height="50" align="right">验证码：</td>
					<td width="300" height="50"><input type="text" name="authcode"
						id="authcode" class="input_box" style="width:80px" tabindex="3" />
						<img id="imgauthcode" alt="验证码"
						style="padding-left: 5px; height: 35px" onclick="chgAuth();"
						src="user_code.do" />
					</td>
				</tr>
				<tr>
					<td height="20" colspan="2">
						<p id="msg">${msg }</p></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>