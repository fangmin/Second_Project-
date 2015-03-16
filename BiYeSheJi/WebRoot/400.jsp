<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>400</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.box {
	width: 730px;
	height: 450px;
	margin: 0 auto;
}
</style>
</head>

<body>
	<div class="box">
		<a href="#"><img src="themes/default/images/400.jpg" width="730" height="450" />
		</a>
	</div>
</body>
</html>
