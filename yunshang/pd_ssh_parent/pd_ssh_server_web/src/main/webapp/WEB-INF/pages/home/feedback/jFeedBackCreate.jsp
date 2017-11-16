<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<li id="save"><a href="#" onclick="formSubmit('feedBackAction_insert','_self');this.blur();">保存</a></li>
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
   新增反馈
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent"><input type="text" name="title" value=""/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">内容：</td>
	            <td class="tableContent"><textarea name="content" style="height:150px;"></textarea>
	        </tr>
	        <tr> 
	            <td class="columnTitle">分类：</td>
	            <td class="tableContent">
	            	<s:select list="#{'1':'管理','2':'安全','3':'建议','4':'其他'}" name="classType"  ></s:select>
	            </td>
	        </tr>
	        <tr> 
	            <td class="columnTitle">解决人：</td>
	            <td class="tableContent">
	            	<s:select list="userList" name="answerBy" headerKey="" headerValue="--请选择--" listKey="userinfo.name" listValue="userinfo.name" ></s:select>
	            </td>
	        </tr>
	        <tr> 
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="tel" value=""/></td>
	          </tr>
	        <tr>   
	            <td class="columnTitle">是否公开：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="isShare"  value="1"/>公开
	           		<input type="radio" name="isShare"  value="0"/>不公开
	            </td>
	         </tr>		
		</table>
	</div>
 
 
 
</form>
</body>
</html>

