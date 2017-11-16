<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>
<script>
	//判断是否唯一选择
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
	
 
	
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //提交
	     function tosubmit(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_submit','_self')
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //取消
	     function tocancel(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_cancel','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //打印
	     function toprint(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_print','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //删除
	     function toDelete(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('financeAction_delete','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     </script>
<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view" ><a href="#" onclick="javascript:toView();this.blur();";">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('financeAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate();this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete();this.blur();">删除</a></li>
<li id="new"><a href="#" onclick="tosubmit();this.blur();">提交</a></li>
<li id="new"><a href="#" onclick="tocancel();this.blur();">取消</a></li>
<li id="new"><a href="#" onclick="formSubmit('financeAction_print','_self');this.blur();">打印</a></li>
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
    财务合同列表
  </div> 
  </div>
  </div>

<div>
<%-- <c:if test="${o.state==0}">disabled="disabled"</c:if> --%>
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">制单日期</td>
	    <td class="tableHeader">创建人</td>
		<td class="tableHeader">创建部门</td>
		<td class="tableHeader">创建日期</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader"></td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"  /></td>
		<%-- <td>${status.index+1}</td>
		 <td>${o.customName}</td>--%>
		<td><a href="contractAction_toview?id=${o.id}">${o.id}</a></td>
	<%-- 	 <td>
		    ${fn:length(o.contractProducts) }
		    ${o.contractProducts.size() }
		    /
		    <c:set var="extNo" value="0"></c:set>
		    <c:forEach items="${o.contractProducts }"  var="cp" >
		        <c:if test="${fn:length(cp.extCproducts)!=0 }">
		            <c:set var="extNo" value="${extNo+cp.extCproducts.size() }"></c:set>
		        </c:if>
		    	
		    </c:forEach>
		    ${extNo }
		</td> --%>
		<td><fmt:formatDate value="${o.creatTime}" pattern="yyyy-MM-dd"/></td>
		
		<td>${o.creatBy}</td>
		<td>${o.creatDept}</td>
		<td><fmt:formatDate value="${o.inputDate}" pattern="yyyy-MM-dd"/></td>
		<td>
			<c:if test="${o.state==0}">待提交</c:if>
			<c:if test="${o.state==1}"><font color="green">已提交</font></c:if>
			
		</td>
		<%-- <td>
			<c:if test="${o.state==0}">
				<a href="${ctx }/cargo/contractProductAction_tocreate?contract.id=${o.id}">[货物]</a>
			</c:if>
		</td> --%>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

