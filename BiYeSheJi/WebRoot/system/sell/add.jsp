<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
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
	<form method="post" id="sellAdd" action="sell_add.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<%-- <div class="unit">
				<label>土地编号：</label> <select class="combox"
					name="water.plant.earth.id" id="earth" onchange="sel();">
					<s:iterator value="#plants" id="p">
						<option value="${p.earth.id}">${p.earth.earth_num}</option>
					</s:iterator>
				</select>
			</div> --%>
			<div class="unit">
				<label>粮食种类：</label> <select class="combox" name="sell.food.id"
					id="sellfoodid">
					<s:iterator value="#lists" id="l">
						<option value="${l[0]}">${l[1]}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>售出数量：</label><input type="text" name="sell.number"
					id="sell_number" size="30" maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>售出单价：</label> <input type="text" name="sell.price" size="30"
					maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>售出时间：</label><input type="text" name="sell.add_time"
					class="date" readonly="readonly" class="required" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="match();">提交</button>
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

