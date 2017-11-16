<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js">

	</script>
	<script type="text/javascript">
	 function gobacktoFirst(){
			top.main.location.href = '${ctx}/home/memoAction_query';
			linkHighlightMenu(this);
		} </script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
	<ul>
	<li id="back"><a href="#" onclick="javascript:gobacktoFirst()">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   用户个人信息
  </div> 
  </div>
  </div>
    <div>
		<table class="commonTable" cellspacing="1">
       		 <tr><td class="columnTitle"><h5>工作信息</h5></td> </tr> 
        	<tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="tableContent">${userName}</td>
	             <td class="columnTitle">岗位：</td>
	            <td class="tableContent">${userinfo.station}</td>
	        </tr>
	         <tr>
	        	<td class="columnTitle">所在部门：</td>
	        	<td class="tableContent">${dept.deptName}</td>
	        	 <td class="columnTitle"  >入职时间：</td>
	        	 <td class="tableContent" ><fmt:formatDate value="${userinfo.joinDate}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	     
	     
	     
	        <tr><td class="columnTitle"><h5>可修改信息</h5></td> </tr> 
        	<tr>
	            <td class="columnTitle">姓名：</td>
	            <td class="tableContent">${userinfo.name }</td>
	            <td class="columnTitle">性别：</td>
	            <td class="tableContent"> ${userinfo.gender==1 ? "男" : "女"}</td>
	            
	        </tr>		
	       	<tr>
	       		<td class="columnTitle">出生年月：</td>
	             <td class="tableContent"><fmt:formatDate value="${userinfo.birthday }" pattern="yyyy-MM-dd"/></td>
	       	</tr>
	       	<tr>
	       		<td class="columnTitle">电话：</td>
	            <td class="tableContent">${userinfo.telephone }</td>
        	    <td class="columnTitle">邮箱：</td>
	            <td class="tableContent">${userinfo.email}</td> 
	        </tr>
        	<tr>
	            <td class="columnTitle">说明：</td>
	            	<td class="tableContent">${userinfo.remark }</td> 
	            </td>
	        </tr>	
		</table>
	</div>
 
</form>
</body>
</html>

