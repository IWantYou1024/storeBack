<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('shippingOrderAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增货物
  </div> 

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	        	<td class="columnTitle">货主</td>
	            <td class="tableContent"><input type="text" name="shipper" value="${shipper}"/></td>
	            <td class="columnTitle">运输方式：</td>
	            <td class="tableContent"><input type="text" name="orderType" value="${orderType}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">提单抬头</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${consignee}"/></td>
	            <td class="columnTitle">正本通知人</td>
	            <td class="tableContent"><input type="text" name="notifyParty" value="${notifyParty}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent"><input type="text" name="portOfLoading" value="${portOfLoading}"/></td>
	            <td class="columnTitle">转运港：</td>
	            <td class="tableContent"><input type="text" name="portOfTrans" value="${portOfTrans}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">目的港：</td>
	            <td class="tableContent"><input type="text" name="portOfDischarge" value="${portOfDischarge}"/></td>
	        	<td class="columnTitle">信用证号</td>
	            <td class="tableContent"><input type="text" name="lcNo" value="${lcNo}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装期</td>
	        	<td class="tableContent">
	            <input type="text" style="width:90px;" name="loadingDate"
	            	 value="${loadingDate }"onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	            <td class="columnTitle">效期</td>
	            <td class="tableContent">
	            <input type="text" style="width:90px;" name="limitDate"
	            	 value="${limitDate }"onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>
	        
	        <tr>
	         	<td class="columnTitle">是否分批：</td>
	            <td class="tableContentAuto">
	            <input type="radio" name="isBatch"  value="0" <c:if test="${isBatch == 0}">checked="checked"</c:if>/>否
	            <input type="radio" name="isBatch"  value="1" <c:if test="${isBatch == 1}">checked="checked"</c:if>/>是
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">是否转船：</td>
	            <td class="tableContentAuto">
	            <input type="radio" name="isTrans" value="0" <c:if test="${isBatch == 0}">checked="checked"</c:if>/>否
	            <input type="radio" name="isTrans" value="1" <c:if test="${isBatch == 1}">checked="checked"</c:if>/>是
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">份数：</td>
	            <td class="tableContent"><pre><input type="text" name="copyNum" value="${copyNum}"/></pre></td>
	            <td class="columnTitle">扼要说明：</td>
	            <td class="tableContent"><pre><input type="text" name="remark" value="${remark}"/></pre></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">运输要求：</td>
	            <td class="tableContent"><pre><input type="text" name="specialCondition" value="${specialCondition}"/></pre></td>
	            <td class="columnTitle">运费说明：</td>
	            <td class="tableContent"><input type="text" name="freight" value="${freight}"/></td>
	        </tr>
		</table>
	</div>


  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    装箱单列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="radio" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">卖方</td>
		<td class="tableHeader">买方</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">发票日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
 <c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
	
		<td><input type="radio" name="shippingOrderId" value="${o.packingListId}" <c:if test="${ o.packingListId == shippingOrderId }">checked="checked"</c:if>/></td>
		<td>${status.count}</td>
		<td>${o.seller}</td>
		<td>${o.buyer}</td>
		<td>${o.invoiceNo}</td>
		
		<td>
		 <fmt:formatDate value="${o.invoiceDate}" pattern="yyyy-MM-dd"/> 
		</td>
		
		<td>
		   <c:if test="${o.state==0}">草稿</c:if>
		   <c:if test="${o.state==1}">已提交</c:if>
		   <c:if test="${o.state==2}">已报运</c:if>
		</td>
		
	</tr>
	</c:forEach>

	</tbody>
</table>
</div> 
 
</form>
</body>
</html>

