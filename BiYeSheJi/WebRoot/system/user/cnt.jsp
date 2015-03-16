<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">	
	$(function(){
		var date="${date}";
		var start_time="${start_time}";
		var end_time="${end_time}";
		if(date==null||date==""){
			date=2;
		}
		$("#date").val(date);
		
		if (date==4){
			$("#td_date").css("display","block");
			$("#start_time").val(start_time);
			$("#end_time").val(end_time);
		}else{
			$("#td_date").css("display","none");
		}
		
		var total="${total}";
		var txt="";
		if(total==null || total<=0){
			txt="暂无数据";
		}else{
			txt="总收入"+total+"元";
		}
		$("#trTotal").html(txt);
		
	});

	
	function sel(){
		var date=$("#date").val();
		if (date==4){
			$("#td_date").css("display","block");
		}else{
			$("#td_date").css("display","none");
		}
	}
	
	function sub(){
		var start_time=$("#start_time").val();
		var end_time=$("#end_time").val();
		if(start_time>end_time){
			var json={"statusCode":"300","message":"操作失败！<br />结束时间应大于起始时间！"};
			DWZ.ajaxDone(json);
		}else{
			$("#pagerForm").submit();
		}		
	}	
</script>

<form id="pagerForm" onsubmit="return navTabSearch(this);" method="post"
	action="user_cnt.do">
	<div class="pageHeader">
		<!--<搜索栏>-->
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label style="width:30%">条件：</label><select class="combox"
						name="date" id="date" onchange="sel();">
							<option value="1">本日</option>
							<option value="2">本周</option>
							<option value="3">本月</option>
							<option value="4">自定义</option>
					</select></td>
					<td id="td_date"><input type="text" name="start_time"
						class="date" readonly="readonly" id="start_time" /> ~ <input
						type="text" name="end_time" class="date" readonly="readonly"
						id="end_time" />
					</td>
					<td><div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="javascript:sub();">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!--</搜索栏>-->
	</div>
</form>

<div class="pageContent">
	<table class="table" width="100%" layouth="85">
		<thead>
			<tr>
				<th>粮食种类</th>
				<th>售出总数</th>
				<th>售出总额</th>
				<th>售出时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="u" value="#list">
				<tr>
					<td>${u[0]}</td>
					<td>${u[1]}</td>
					<td>${u[2]}</td>
					<td>${u[3]}</td>
				</tr>
			</s:iterator>
			<%-- <s:iterator id="u" value="#list">
			<tr align="center"><td  colspan="4">总收入${u[4]}</td></tr>
			</s:iterator> --%>
			<!-- <tr align="center"><td colspan="4">总收入:</td></tr> -->

			<tr align="center">
				<td colspan="4" id="trTotal"></td>
			</tr>
		</tbody>
	</table>
</div>