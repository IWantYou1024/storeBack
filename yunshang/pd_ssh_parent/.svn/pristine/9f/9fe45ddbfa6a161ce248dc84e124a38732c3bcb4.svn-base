<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
   浏览购销合同
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	         <tr>
	            <td class="columnTitle">产品编号：</td>
	            <td class="tableContent">${o.productNo}</td>
	            <td class="columnTitle">生产厂家：</td>
	            <td class="tableContent">${o.factoryName}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent">${description}</td>
	            <td class="columnTitle">价格：</td>
	            <td class="tableContent">${price}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">尺寸长：</td>
	            <td class="tableContent">${sizeLenght}</td>
	            <td class="columnTitle">尺寸宽：</td>
	            <td class="tableContent">${sizeWidth}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">尺寸高：</td>
	            <td class="tableContent">${sizeHeight}</td>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent">${color}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent">${packing}</td>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContent">${packingUnit}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent">${qty}</td>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent">${cbm}</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><pre>${remark}</pre></td>
	            <td class="columnTitle">录入时间：</td>
	            <td class="tableContent">
					<fmt:formatDate value='${inputTime}' pattern='yyyy-MM-dd' />
				</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent">
					<fmt:formatDate value='${createTime}' pattern='yyyy-MM-dd' />
				</td>
	        </tr>	
		</table>
	</div>
 </form>
 
</body>
</html>