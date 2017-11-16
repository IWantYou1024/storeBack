<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ include file="../../baselist.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   浏览工厂信息
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	         <tr>
	            <td class="columnTitle">厂家全称：</td>
	            <td class="tableContent">${fullName }</td>
	            <td class="columnTitle">厂家简称：</td>
	            <td class="tableContentAuto">
	            	${factoryName } 
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">联系人：</td>
	            <td class="tableContent">${contacts }</td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent">${phone }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">地址：</td>
	            <td class="tableContent">${address }</td>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent">${inspector }</td>
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
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
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
 
</div>
 </form>
 
 



</body>
</html>