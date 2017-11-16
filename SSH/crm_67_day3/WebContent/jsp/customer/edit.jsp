<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>编辑客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:debug></s:debug>
	<%--
		s:textfield 带有自动回显功能，
			前提条件：表单提交的name值需要在值栈中可以查找到(对象需要在栈顶)
			
	 --%>
	<s:form action="editCustomer" namespace="/customer" method="post">
		<s:textfield type="hidden" name="custId"></s:textfield>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg" height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
					</TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<TR>
								<td>客户名称：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="custName"></s:textfield>
								</td>
								<td>所属行业 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="custIndustry"></s:textfield>
								</td>
							</TR>							
							<TR>	
								<td>信息来源 ：</td>
								<td>
									<%--struts2提供的下拉框的标签 ： s:select(会经过服务器转化为html标签)
										list：从后台获取到的列表的集合数据
										value：
										headerKey：提交时候的值
										headerValue：默认展示页面信息
										listKey：提交时候的值对应的属性名
										listValue：查询出的是一个list中元素用来展示的属性名，
										name：input表单提交的name
										** 使用struts2的标签库，属性必须要添加“”号
										
										* 是由于默认使用的struts2提供的ui布局，我们可以修改
									 --%>
									<s:select class="textbox" id="sChannel2" style="WIDTH: 180px" list="%{customerSources}" headerKey="" headerValue="---请选择---"
										listKey="dictId" listValue="dictItemName" name="custSource.dictId"
									></s:select>
								</td>
								<td>客户级别：</td>
								<td>
									<s:select class="textbox" id="sChannel2" style="WIDTH: 180px" list="%{customerLevels}" headerKey="" headerValue="---请选择---"
										listKey="dictId" listValue="dictItemName" name="custLevel.dictId"
									></s:select>								
								</td>
							</TR>
							<TR>
								<td>联系地址 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="custAddress"></s:textfield>
								</td>
								<td>联系电话 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="custPhone"></s:textfield>
								</td>
							</TR>
							<tr>
								<td rowspan=2>
								<%--
									Struts2的提交按钮
								 --%>
									<s:submit name="sButton2"  class="button" id="sButton2" value=" 保存"></s:submit>
								</td>
							</tr>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg" border=0></TD>
					<TD align="center" width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"	border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
</HTML>
