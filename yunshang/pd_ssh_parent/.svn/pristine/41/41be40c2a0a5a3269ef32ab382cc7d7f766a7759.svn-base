<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
	<script type="text/javascript">
		//动态设置生产厂家的名称 
		function setFactoryName(val){
			document.getElementById("factoryName").value = val;
		}
	</script>
</head>

<body>
<form name="icform" method="post">
<input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('productAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
  修改产品
  </div> 
  </div>
  </div>
  

 
<div>
	<table class="commonTable" cellspacing="1">
        <tr>
            <td class="columnTitle">生产厂家：</td>
            <td class="tableContent">
            	 <s:select name="factory.id" list="factoryList" 
            				onchange="setFactoryName(this.options[this.selectedIndex].text);"
            				listKey="id" listValue="factoryName" 
            				headerKey="" headerValue="--请选择--"/>
            				
            	<input type="hidden" id="factoryName" name="factoryName" value="${factoryName}"/>
            </td>
            <td class="columnTitle">产品编号：</td>
            <td class="tableContent"><input type="text" name="productNo" value="${productNo}"/></td>
        </tr>		
        <tr>
            <td class="columnTitle">产品照片：</td>
            <td class="tableContent"><input type="text" name="productImage" value="${productImage}"/></td>
            <td class="columnTitle">颜色：</td>
            <td class="tableContent"><input type="text" name="color" value="${color}"/></td>
        </tr>		
        <tr>
            <td class="columnTitle">数量：</td>
            <td class="tableContent"><input type="text" name="qty" value="${qty}"/></td>
            <td class="columnTitle">包装单位：</td>
            <td class="tableContentAuto">
	            	<input type="radio" name="packingUnit" value="PCS" ${packingUnit=='PCS'?"checked":"" } class="input">只
	            	<input type="radio" name="packingUnit" value="SETS" ${packingUnit=='SETS'?"checked":"" } class="input">套
            </td>
        </tr>		
        <tr>
            <td class="columnTitle">尺寸长：</td>
            <td class="tableContent"><input type="text" name="sizeLenght" value="${sizeLenght}"/></td>
            <td class="columnTitle">尺寸宽：</td>
            <td class="tableContent"><input type="text" name="sizeWidth" value="${sizeWidth}"/></td>
        </tr>		
        <tr>
            <td class="columnTitle">尺寸高：</td>
            <td class="tableContent"><input type="text" name="sizeHeight" value="${sizeHeight}"/></td>
            <td class="columnTitle">价格：</td>
            <td class="tableContent"><input type="text" name="price" value="${price}"/></td>
        </tr>
        <tr>
            <td class="columnTitle">描述：</td>
            <td class="tableContent"><textarea name="description" style="height:150px;">${description}</textarea>
            <td class="columnTitle">备注：</td>
            <td class="tableContent"><textarea name="remark" style="height:150px;">${remark}</textarea>
        </tr>		
	</table>
</div>
</form>
</body>
</html>

