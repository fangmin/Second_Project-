<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">	
	function sel(){
		var earth=$("#earth").val();
	   
		$.post("plant_get.do",{"earth_id":earth},function(rs){
			//alert(rs);
			var obj = eval('(' + rs + ')');/* eval() 函数可计算某个字符串，并执行其中的的 JavaScript 代码。 */
			if(obj==null || obj.length==0)
				return;				
			var sb="<label>浇灌植物：</label>";
			sb +="<select class=\"combox\" name=\"water.plant.id\">";
			var o=null;
			for(var i=0;i<obj.length;i++){
				o=obj[i];
				sb +="<option value=\""+o.id+"\">"+o.kind+"</option>";
			}
			sb +="</select>";
			$("#div_plant").html(sb);
		});
	}
</script>

<div class="pageContent">
	<form method="post" action="water_add.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58" >
			<div class="unit">
				<label>土地编号：</label> <select class="combox"
					name="water.plant.earth.id" id="earth" onchange="javascript:sel();">
					<s:iterator value="#earths" id="l">
						<option value="${l.id}">${l.earth_num}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit" id="div_plant">
				<label>浇灌植物：</label> <select class="combox" name="water.plant.id"
					id="plant">
					<s:iterator value="#plants" id="p">
						<option value="${p.id}">${p.kind}</option>
					</s:iterator>
				</select>
			</div>
			<div class="unit">
				<label>浇灌时间：</label><input type="text" name="water.add_time"
					class="date required" readonly="readonly" />
				<!-- <input type="text" name="water.add_time"
					size="30" maxlength="20" class="required"/> -->
			</div>
			<div class="unit">
				<label>备注信息：</label>
				<textarea name="water.remark" rows="10" cols="50" ></textarea>
				  <!-- <input type="textarea" name="water.remark"
					size="50" maxlength="100"  clos="50" rows="50"/> -->
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
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

