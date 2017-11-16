<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error Page</title>
    <script language="javascript">
        function lookSelfInfo(){
			top.main.location.href = '${ctx}/sysadmin/userAction_toselfInfo';
			linkHighlightMenu(this);
		} 
        function gobacktoFirst(){
			top.main.location.href = '${ctx}/home/memoAction_query';
			linkHighlightMenu(this);
		} 
    </script>
</head>

<body style="font-family:微软雅黑;">

<div id="content" style="text-align:left;">

<table>
<tr>
	<br>  
	<b><h1>恭喜您,密码修改成功！</h1></b>
 	
 	<div style="color:blue;padding:15px;">
 		<s:property value="exception.message"/>
 	</div>
    <button onclick="gobacktoFirst()">回到首页</button>
	<button onclick="lookSelfInfo()">查看个人信息</button>

 	<br/>
	
	</p>

	</td>
</tr>
</table>

	<div id="detail_system_error_msg" style="display:none;text-align:left;padding-bottom:100px;">  
		<pre><s:property value="exceptionStack"/></pre>  
	</div>
</div>
</body>
</html>