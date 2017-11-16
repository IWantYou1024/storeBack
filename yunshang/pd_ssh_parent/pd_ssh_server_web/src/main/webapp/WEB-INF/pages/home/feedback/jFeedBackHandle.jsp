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
<li id="save"><a href="#" onclick="formSubmit('feedBackAction_handle','_self');this.blur();">保存</a></li>
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
   处理反馈信息
  </div> 
  </div>
  </div>
  
  
 

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td>${inputBy}</td>
	            </tr>
	        <tr> 
	            <td class="columnTitle">提出时间：</td>
	            <td>${inputTime}</td>
	         </tr>
	        <tr> 
	            <td class="columnTitle">标题：</td>
	            <td>${title}</td>
	        </tr>
	        <tr> 
	            <td class="columnTitle">内容：</td>
	            <td>${content }</td>
	         </tr>	
	         
	         <tr>
	         <td class="columnTitle">解决人：</td>
	         <td>${answerBy }</td>
	         </tr>
	        <tr>   
	            <td class="columnTitle">解决办法：</td>
	            <td class="tableContent"><textarea  name="solveMethod">${solveMethod}</textarea>
	        </tr> 
	        <tr>   
	            <td class="columnTitle">解决方式：</td>
	            <td class="tableContent"><s:select list="#{'1':'已修改','2':'无需修改','3':'重复问题','4':'描述不完整','5':'无法再现','6':'其他' }" name="resolution"   value="resolution"></s:select>
	            </td>
	         </tr>
	         <tr>   
	            <td class="columnTitle">解决难度：</td>
	            <td class="tableContent"><s:select list="#{'1':'极难','2':'比较难','3':'有难度','4':'一般'}" name="difficulty"  value="difficulty"></s:select>
	          	</td>
	            </tr>	
	         <tr>   
	           <td class="columnTitle">是否公开：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="isShare"   value="1" ${isShare=='1'?"checked='checked'":""}/>公开
	           		<input type="radio" name="isShare"  value="0" ${isShare=='0'?"checked='checked'":""}/>不公开
	            </td>
	           </tr>	
		</table>
	</div>
 
 
 
</form>
</body>
</html>

