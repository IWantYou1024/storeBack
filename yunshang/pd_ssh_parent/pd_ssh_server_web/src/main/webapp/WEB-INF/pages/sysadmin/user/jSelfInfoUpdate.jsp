<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">

</script>
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('userAction_saveselfInfo','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
<li id="deploy"><a href="#" onclick="formSubmit('userAction_loginpwdpdate','_self');">修改密码</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   修改用户个人信息
  </div> 
  </div>
  </div>
    <div>
		<table class="commonTable" cellspacing="1">
       		<tr><td class="columnTitle"><h5>工作信息</h5></td> </tr> 
        	<tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="columnTitle">${userName}</td>
	             <td class="columnTitle">岗位：</td>
	            <td class="columnTitle">${userinfo.station}</td>
	        </tr>
	        <tr>
	        	<td class="columnTitle">所在部门：</td>
	        	<td class="columnTitle">${dept.deptName}</td>
	        	 <td class="columnTitle">入职时间：</td>
	        	 <td class="columnTitle"><fmt:formatDate value="${userinfo.joinDate}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	        
	         <tr><td class="columnTitle"><h5>可修改信息</h5></td> </tr>
        	<tr>
	            <td class="columnTitle">姓名：</td>
	            <td class="tableContent"><input type="text" name="userinfo.name" value="${userinfo.name }"/></td>
	            <td class="columnTitle">性别：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="userinfo.gender" value="1" class="input" ${userinfo.gender==1?'checked':'' }/>男
	            	<input type="radio" name="userinfo.gender" value="0" class="input" ${userinfo.gender==0?'checked':'' }/>女
	            </td>
	        </tr>		
	       	<tr>
	       		<td class="columnTitle">出生年月：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="userinfo.birthday"
	            	 value="${userinfo.birthday }"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
				
	       	</tr>
	       	<tr>
	       		<td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="userinfo.telephone" value="${userinfo.telephone }"/></td>
        	    <td class="columnTitle">邮箱：</td>
	            <td class="tableContent"><input type="text" name="userinfo.email" value="${userinfo.email}"/></td> 
	        </tr>
        	<tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="userinfo.remark" style="height:120px;">${userinfo.remark }</textarea>
	            </td>
	        </tr>	
		</table>
	</div>
 
</form>
</body>
</html>

