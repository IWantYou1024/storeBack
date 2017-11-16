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
	    		 formSubmit('feedBackAction_toView','_self');
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
<li id="work_assign"><a href="#" onclick=" formSubmit('feedBackAction_myList','_self');">我发出的</a></li>
<li id="work_assign"><a href="#" onclick=" formSubmit('feedBackAction_myHandleHList','_self');">我解决的</a></li>
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
    全部反馈
  </div> 
  </div>
  </div>

<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		
		<td class="tableHeader">反馈序号</td>
		<td class="tableHeader">提出人</td>
		<td class="tableHeader">提出时间</td>
		<td class="tableHeader">标题</td>
		<td class="tableHeader">分类</td>
	    <td class="tableHeader">解决人</td>
		<td class="tableHeader">解决时间</td>
		<td class="tableHeader">解决方式</td>
		<td class="tableHeader">是否公开</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputBy}</td>
		<td>${o.inputTime}</td>
		<td>${o.title}</td>
		<td>
		<c:if test="${o.classType == '1'}"  >管理</c:if>
		<c:if test="${o.classType == '2'}"  >安全</c:if>
		<c:if test="${o.classType == '3'}"  >建议</c:if>
		<c:if test="${o.classType == '4'}"  >其他</c:if>
		</td>
		<td>${o.answerBy}</td>
		<td>${o.answerTime}</td>
		<td>
				<c:if test="${o.resolution == '1' }"  >已修改</c:if>
	            <c:if test="${o.resolution == '2' }"  >无需修改</c:if>
	            <c:if test="${o.resolution == '3' }"  >重复问题</c:if>
	            <c:if test="${o.resolution == '4' }"  >描述不完全</c:if>
	            <c:if test="${o.resolution == '5' }"  >无法再现</c:if>
	            <c:if test="${o.resolution == '6' }"  >其他</c:if>
		</td>
		<td>
			<c:if test="${o.isShare=='0'}"><font color="red">不公开</c:if>
			<c:if test="${o.isShare=='1'}"><font color="green">公开</font></c:if>
		</td>
		<td>
			<c:if test="${o.state=='0'}"><font color="red">未处理</c:if>
			<c:if test="${o.state=='1'}"><font color="green">已处理</font></c:if>
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

