<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>编辑联系人</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<body>
	<s:form action="editLinkMan" namespace="/linkman" method="post">
		<s:textfield type="hidden" name="lkmId"></s:textfield>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"  height=20></TD>
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
								<TD class=manageHead>当前位置：联系人管理 &gt; 修改联系人</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>所属客户：</td>
								<td colspan="3">
									<s:select list="customers" name="customer.custId" headerKey="" headerValue="--请选择--"
										listKey="custId" listValue="custName"
									></s:select>
								</td>
							</tr>
							<TR>
								<td>联系人名称：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="lkmName"></s:textfield>
								</td>
								<td>联系人性别：</td>
								<td>
									<%--
										s:radio : strtus提供radio标签
											list可以接受自定的集合
												数组 {'',''};
												map #{'key':'value','表单提交的数据':'页面显示的内容'}
									 --%>
									<s:radio name="lkmGender" list="#{'male':'男','female':'女'}"></s:radio>
								</td>
							</TR>
							<TR>
								<td>联系人办公电话 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="lkmPhone"></s:textfield>
								</td>
								<td>联系人手机 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="lkmMobile"></s:textfield>
								</td>
							</TR>
							<TR>
								<td>联系人邮箱 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="lkmEmail"></s:textfield>
								</td>
								<td>联系人职位 ：</td>
								<td>
									<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 180px" maxLength="50" name="lkmPosition"></s:textfield>
								</td>
							</TR>
							<TR>
								<td>联系人简介 ：</td>
								<td colspan="2">
									<%-- s:textarea  相当于  html 中 textarea--%>
									<s:textarea class="textbox" id="sChannel2" style="WIDTH: 180px"  name="lkmMemo"></s:textarea>
								</td>
							</TR>
							<tr>
								<td rowspan=2>
									<INPUT class=button id=sButton2 type=submit value="修改 " name=sButton2>
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
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</body>
</HTML>
