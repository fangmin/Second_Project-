<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type="text/javascript">	$(function(){
		var plant="${sell.food.id}";
		$("#sellfoodid").val(plant);
	});	<!--当页面加载完时再加载此函数，目的是选中下拉列表框中的值-->	
     function match(){
         var sellNumber=document.getElementById("sell_number").value; //获取当前jsp文档中文本框中的值，“.val”获取的是已经存在的值
         var earthId=document.getElementById("sellfoodid").options[document.getElementById("sellfoodid").selectedIndex].value;
          $.post("sell_comparison.do",{"earthId":earthId},function(allNumbers){
         if(parseInt(sellNumber) > parseInt(allNumbers)){
            /*  alert("---------"+sellNumber);
             alert("+++++++++++"+allNumbers); */
             alert("当前售出数量超过收入总量,收入总量为："+allNumbers+"\n\n"+"请重新输入");
             return;
         }
         $("#sellAdd").submit();
         })
}
</script>
<div class="pageContent">
	<form method="post" action="sell_upd.do"
		class="pageForm required-validate" id="sellAdd"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="sell.id" value="${sell.id}" />
			<div class="unit">
				<label>粮食种类：</label> <select class="combox" name="sell.food.id"
					id="sellfoodid">
					<!-- 此处的name必须是id而不能是其他的属性，因为water的外键是plant_id -->
					<s:iterator value="#lists" id="l">
						<option value="${l[0]}">${l[1]}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>售出数量：</label> <input type="text" name="sell.number"
					id="sell_number" size="30" maxlength="20" value="${sell.number}" />
			</div>
			<div class="unit">
				<label>售出单价：</label> <input type="text" name="sell.price" size="30"
					maxlength="20" value="${sell.price}" />
			</div>
			<div class="unit">
				<label>售出时间：</label> <input type="text" name="sell.add_time"
					class="date" readonly="readonly" value="${sell.add_time}" />
				<%-- <input type="text" name="sell.add_time" size="30"
					maxlength="20" value="${sell.add_time}" /> --%>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="match();">提交</button>
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

