<%@page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>土地信息管理系统</title>
<link href="themes/default/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="themes/css/core.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="themes/css/print.css" rel="stylesheet" type="text/css"
	media="print" />
<link href="uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" media="screen" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
<style type="text/css">
#header {
	height: 85px
}

#leftside,#container,#splitBar,#splitBarProxy {
	top: 90px
}
</style>

<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor/xheditor-1.1.14-zh-cn.min.js"
	type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.js"
	type="text/javascript"></script>

<script src="js/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.print.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		DWZ.init("dwz.frag.xml", {
			loginTitle : "登录", // 弹出登录对话框
			loginUrl : "login_dialog.jsp", // 跳到登录页面
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "themes"
				}); // themeBase 相对于index页面的主题base路径
			}
		});
	});
</script>
</head>
<body scroll="no"><!-- scroll表示滚动条 -->
	<div id="layout"><!-- (div) 是一个块级元素。这意味着它的内容自动地开始一个新行 -->
		<div id="header"><!-- 而 id 用于标识单独的唯一的元素 -->
			<div class="headerNav"><!-- class 用于元素组（类似的元素，或者可以理解为某一类元素） -->
				<a class="logo">土地信息管理系统</a>
				<ul class="nav">
					<li><a href="user_logout.do">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default">
						<div class="selected">蓝色</div>
					</li>
					<li theme="green">
						<div>绿色</div>
					</li>
					<li theme="purple">
						<div>紫色</div>
					</li>
					<li theme="silver">
						<div>银色</div>
					</li>
					<li theme="azure">
						<div>天蓝</div>
					</li>
				</ul>
			</div>

			<div id="navMenu">
				<ul>
					<li class="selected"><a href="menu/earth.jsp"><span>土地管理</span>
					</a>
					</li>
					<li><a href="menu/storage.jsp"><span>仓库管理</span> </a>
					</li>
					<li><a href="menu/user.jsp"><span>用户管理</span> </a>
					</li>
				</ul>
			</div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				 <div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				 </div>
				<div class="accordion" fillspace="sidebar">
				    <div class="accordionHeader">
		                <h2>
			                 <span>Folder</span>土地信息管理
		                </h2>
	                </div>
	                <div class="accordionContent">
		                 <ul class="tree treeFolder">
			                 <li><a href="earth_list.do" target="navTab" rel="earthlist">土地信息管理</a>
			                 </li>
		                 </ul>
	                </div>
                    <div class="accordionHeader">
		                <h2>
			               <span>Folder</span>土地耕种信息
		                </h2>
	                </div>
                    <div class="accordionContent">
		                <ul class="tree treeFolder">
			                <li><a href="plant_list.do" target="navTab" rel="plantlist">土地耕种信息</a>
			                </li>
		                </ul>
	                </div>
	                <div class="accordionHeader">
		                 <h2>
			                 <span>Folder</span>土地浇灌信息
		                 </h2>
	                </div>
                    <div class="accordionContent">
		                 <ul class="tree treeFolder">
			                  <li><a href="water_list.do" target="navTab" rel="waterlist">土地浇灌信息</a>
			                  </li>
		                 </ul>
	                </div>
	                <div class="accordionHeader">
		                <h2>
			                 <span>Folder</span>土地施肥信息
		                </h2>
	                 </div>
                     <div class="accordionContent">
		                <ul class="tree treeFolder">
			                <li><a href="fertilize_list.do" target="navTab" rel="fertilizelist">土地施肥信息</a>
			                </li>
		                </ul>
	                  </div>
	                <div class="accordionHeader">
		                <h2>
			                <span>Folder</span>土地喷药信息
		                </h2>
	                </div>
                    <div class="accordionContent">
		                <ul class="tree treeFolder">
			                <li><a href="spray_list.do" target="navTab" rel="spraylist">土地喷药信息</a>
			                </li>
		                </ul>
	                </div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span> </span> </a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0);">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>
								<span>【${user.account}】您好! </span>
							</p>
							<p>欢迎使用本系统</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2014</div>
</body>
</html>
