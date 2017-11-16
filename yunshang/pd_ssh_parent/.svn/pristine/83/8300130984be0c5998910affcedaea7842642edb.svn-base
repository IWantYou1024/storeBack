<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<!--  <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
        <title>JSP实验欢迎您！</title>
        <link href="CSS/style.css" rel="stylesheet" type="text/css" />
        <script language="javascript">
        function checkForm(form){
            if(form.oldPwd.value==""){
                alert("请输入的原密码!");form.oldPwd.focus();return false;
            }
            if(form.oldPwd.value!=form.holdPwd.value){
                alert("您输入的原密码不正确，请重新输入!");form.oldPwd.value="";
                form.oldPwd.focus();return false;
            }
            if(form.newPwd.value==""){
                alert("请输入的新密码!");form.newPwd.focus();return false;
            }
            if (form.newPwd.value.length>25) { 
                alert("新密码长度不能太长了!");form.newPwd.focus();return false;
            }
            if(form.newPwdConfirm.value==""){
                alert("请确认新密码!");form.newPwdConfirm.focus();return false;
            }
            if(form.newPwd.value!=form.newPwdConfirm.value){
                alert("您两次输入的新密码不一致，请重新输入!");
                form.newPwd.value="";form.newPwdConfirm.value="";
                form.newPwd.focus();return false;
            }
        }
    </script> -->
</head>

<body>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('userAction_saveAlterPwd','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
<li id="deploy"><a href="#" onclick="formSubmit('userAction_loginpwdpdate','_self');">重置密码</a></li>
</ul>
  </div>
</div>
</div>
</div>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>

   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改用户密码
  </div> 
  
    <div>
		<table border="0" align="center" width="60%">
			 <tr>
			<td></td>
			<td>
			<c:if test="${!empty olderror}">
						${olderror}
					</c:if>
					<c:if test="${!empty newPwderror}">
						${newPwderror}
					</c:if>
					<c:if test="${!empty newPwderror}">
						${emptyerror1}
					</c:if>
					<c:if test="${!empty newPwderror}">
						${emptyerror2}
					</c:if>
			</td>
			</tr>
			<tr></tr>
			
	        <tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="columnTitle">${userName}</td>
	            <!-- <td></td> -->
	        </tr>	
	        <tr>
	        	<td class="columnTitle">请输入原密码：</td>
	            <td class="tableContent"><input type="password" style="width:350px;" name="password" value="" /></td>
	          	
	          <!--   <td></td> --> 
               <!--  <td>请输入原密码：<input type="password" name="oldPwd" value="" /></td> -->
            </tr>
            <tr>
            	<td class="columnTitle">请输入新密码：</td>
	            <td class="tableContent"><input type="password"  style="width:350px;" name="newPwds" value="" /></td>
	            <!-- <td></td> -->
               <!--  <td>请输入新密码：<input type="password" name="newPwd" value=""/></td> -->
            </tr>
            <tr>
            	<td class="columnTitle">请确认新密码：</td>
	            <td class="tableContent"><input type="password" style="width:350px;" name="newPwds" value="" /></td>
	         	
	          <!--   <td></td> -->
                <!-- <td>请确认新密码：<input type="password" name="newPwdConfirm" value=""/></td> -->
            </tr>
           

 </form> 
 
  
</body>
</html>