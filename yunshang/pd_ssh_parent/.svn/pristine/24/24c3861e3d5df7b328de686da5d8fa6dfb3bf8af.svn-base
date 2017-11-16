<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script>
	     function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				} 
			if(count==1)
				return true;
			else
				return false;
	     }
	     function isChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				} 
			if(count!=0)
				return true;
			else
				return false;
	     }
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('factoryAction_toView','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('factoryAction_toUpdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //删除
	     function toDelete(){
	    	 if(isChecked()){
	    		 confirm("确认删除选中项吗？");
	    		 formSubmit('factoryAction_delete','_self');
	    	 }else{
	    		 alert("请先至少选择一项，再进行操作！");
	    	 }
	     }

	
	
	
		
	</script>
	
	
</head>

<body>
<form name="icform" method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('factoryAction_toCreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="toUpdate()">修改</a></li>
<li id="delete"><a href="javascript:void(0)" onclick=" toDelete()">删除</a></li>
<li id="update"><a href="#" onclick="formSubmit('factoryAction_toStop','_self');this.blur();">停用</a></li>
<li id="update"><a href="#" onclick="formSubmit('factoryAction_toNormal','_self');this.blur();">正常</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
  <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    工厂信息列表
  </div> 
  </div>
  </div>

<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		
		<td class="tableHeader">工厂序号</td>
		<td class="tableHeader">产品类型</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">厂家简称</td>
	    <td class="tableHeader">联系人</td>
		<td class="tableHeader">电话</td>
		<td class="tableHeader">手机</td>
		<td class="tableHeader">传真</td>
		<td class="tableHeader">地址</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">说明</td>
		<td class="tableHeader">排序号</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.ctype}</td>
		<td>${o.fullName}</td>
		<td>${o.factoryName}</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>
		<td>${o.mobile}</td>
		<td>${o.fax}</td>
		<td>${o.address}</td>
		<td>${o.inspector}</td>
		<td>${o.remark}</td>
		<td>${o.ORDER_NO}</td>
		<td>
			<c:if test="${o.state==0}"><font color="red">停用</c:if>
			<c:if test="${o.state==1}"><font color="green">正常</font></c:if>
		</td>
		
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

