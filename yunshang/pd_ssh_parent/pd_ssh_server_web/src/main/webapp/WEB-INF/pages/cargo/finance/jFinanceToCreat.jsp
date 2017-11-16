<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('financeAction_insert','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增财务单
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					
					<td class="columnTitle"  >制单人:${userinfo.name }</td>
						<input name="inputBy" type="hidden" value="${userinfo.name }"/>
						
					<td class="columnTitle" >创建人:${userinfo.name }</td>
						<input name="creatBy" type="hidden" value="${userinfo.name }"/>
					
				</tr>
				<tr>
					
					<td class="columnTitle" name="creatDept">创建部门:${dept.deptName }</td>	
						<input name="creatDept" type="hidden" value="${dept.deptName }"/>
					<td class="columnTitle" name="state" value="3">状态：开发票</td>
					<td class="columnTitle" >创建日期</td>
					<td class="tableContent"><input type="text" 
						style="width: 200px;" name="creatTime" value=""
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
					<td class="columnTitle" >制单日期</td>
					<td class="tableContent"><input type="text" 
						style="width: 200px;" name="inputDate" value=""
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
				</tr>
			</table>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
			发票列表
		</div>


		<div class="eXtremeTable">
			<table id="ec_table" class="tableRegion" width="98%">
				<thead>
					<tr>
					<!-- 	<td class="tableHeader"><input type="radio" name="selid"
							onclick="checkAll('ec_table',this)"></td> -->
						<td class="tableHeader"></td>
						<td class="tableHeader">序号</td>
						<td class="tableHeader">合同单号</td>
						<td class="tableHeader">发票号</td>
						<td class="tableHeader">贸易条款</td>
						
						<td class="tableHeader">创建者</td>
						<td class="tableHeader">所在部门</td>
						<td class="tableHeader">创建时间</td>
						
						<td class="tableHeader">状态</td>
					</tr>
				</thead>
				<tbody class="tableBody">
					${links}
					<c:forEach items="${results}" var="o" varStatus="status">
						<tr bgcolor="#c3f3c3" height="30" class="odd"
							onmouseover="this.className='highlight'"
							onmouseout="this.className='odd'">
							<td><input type="radio" name="id" value="${o.id}" /></td>
							<td>$${o.id}</td>
							<td>${o.scNo}</td>
							<td>${o.blNo}</td>
							<td>${o.tradeTerms}</td>
							<td>${o.createBy}</td>
							<td>${o.createDept}</td>
							<td>${o.createTime}</td>
							
							<td>${o.state}</td>
							<td><c:if test="${o.state==1}">待开发票</c:if> <c:if
									test="${o.state==2}">
									<font color="green">已委托</font>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</form>
</body>
</html> 