<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ include file="../../baselist.jsp"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">
<input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('factoryAction_update','_self');this.blur();">保存</a></li>
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
   修改工厂信息
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">厂家类型：</td>
	            <td class="tableContent"><input type="text" name="ctype" value="${ctype}"/></td>
	            <td class="columnTitle">厂家全称：</td>
	            <td class="tableContent"><input type="text" name="fullName" value="${fullName}"/></td>
	         
	            <td class="columnTitle">厂家简称：</td>
	            <td class="tableContent"><input type="text" name="factoryName" value="${factoryName }"/></td>
	        </tr>
	        <tr> 
	            <td class="columnTitle">联系人：</td>
	            <td class="tableContent"><input type="text" name="contacts" value="${contacts }"/></td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="phone" value="${phone }"/></td>
	            <td class="columnTitle">手机：</td>
	            <td class="tableContent"><input type="text" name="mobile" value="${mobile }"/></td>
	        </tr> 
	        <tr>   
	            <td class="columnTitle">传真：</td>
	            <td class="tableContent"><input type="text" name="fax" value="${fax }"/></td>
	            <td class="columnTitle">地址：</td>
	            <td class="tableContent"><input type="text" name="address" value="${address }"/></td>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector" value="${inspector }"/></td>
	         </tr>
	         <tr>   
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark }"/></td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="ORDER_NO" value="${ORDER_NO }"/></td>
	         </tr>		
		</table>
	</div>
 <div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
  <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   产品列表
  </div> 
  </div>
  </div>

<div>
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">产品编号</td>
		<td class="tableHeader">生产厂家</td>
	    <td class="tableHeader">价格</td>
		<td class="tableHeader">颜色</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">录入时间</td>
		<td class="tableHeader">创建时间</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="" value="${o.id}" ${o.factory.id==id? "checked='checked'":""}/></td>
		<td>${status.index+1}</td>
		<td>${o.productNo}</td>
		<td>${o.factoryName}</td>
		<td>${o.price}</td>
		<td>${o.color}</td>
		<td>${o.qty}</td>
		<td><fmt:formatDate value="${o.inputTime}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</form>
</body>
</html>

