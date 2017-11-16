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
	<input name="id" type="hidden" value="${id}"/>

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('financeAction_update','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 修改财务单
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					
					<td class="columnTitle" name="inputBy" >制单人  :
						<input name="inputBy"  type="text" value="${inputBy}"/>
					</td> 
					
				
					 <td class="columnTitle" name="creatBy">创建人 :
						<input name="creatBy" type="text" value="${creatBy }"/>
					</td> 
					
					<td class="columnTitle" name="creatDept">创建部门:
						 <input name="creatDept"type="text" value="${creatDept }"/>
					</td> 
				</tr>	
				
				
				
				
				<tr>
					<td class="columnTitle" name="state" value="${state }">状态：<c:if test="${state==0}">待提交</c:if>
					<input name="state" type="hidden" value="${state }">
					<c:if test="${state==1}"><font color="green">已提交</font></c:if></td>
					<td class="columnTitle" >创建日期</td>
					<td class="tableContent"><input type="text" 
						style="width: 200px;" name="creatTime" value="${creatTime}"
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
					<td class="columnTitle" >制单日期</td>
					<td class="tableContent"><input type="text" 
						style="width: 200px;" name="inputDate" value="${inputDate}"
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
				</tr>
			</table>
		</div>

	
	</form>
</body>
</html> 