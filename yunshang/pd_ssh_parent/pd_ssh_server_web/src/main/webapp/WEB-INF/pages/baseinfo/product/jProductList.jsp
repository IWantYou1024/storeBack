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
	    		 formSubmit('productAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('productAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //删除
	     function todelete(){
	    	 if(isChecked()){
	    		 confirm("确认删除选中项吗？");
	    		 formSubmit('productAction_delete','_self');
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
<li id="view"><a href="javascript:toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('productAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="javascript:todelete()">删除</a></li>
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
   产品列表
  </div> 
  </div>
  </div>

<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">产品编号</td>
		<td class="tableHeader">生产厂家</td>
	    <td class="tableHeader">价格</td>
		<td class="tableHeader">颜色</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">录入时间</td>
		<td class="tableHeader">创建时间</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.productNo}</td>
		<td>${o.factoryName}</td>
		<td>${o.price}</td>
		<td>${o.color}</td>
		<td>${o.qty}</td>
		<td><fmt:formatDate value="${o.inputTime}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

