<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function deleteLinkMan(lkmId){
		var sure = window.confirm("您确定要删除此条数据吗？");
		if(sure){
			window.location.href="${pageContext.request.contextPath }/linkman/deleteLinkMan.action?lkmId="+lkmId;
		}
	} 
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:form id="linkManForm" name="linkManForm" action="findAllLinkMan" namespace="/linkman" method="post">
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
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>联系人名称：</TD>
													<TD>
														<s:textfield class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="lkmName"></s:textfield>
													</TD>
													<TD>所属客户：</TD>
													<TD>
														<s:select list="customers" headerKey="" headerValue="--请选择--"
															listKey="custId" listValue="custName" name="customerId"
														></s:select>
													</TD>
													<TD>性别</TD>
													<TD>
														<s:radio list="#{'male':'男','female':'女' }"  name="lkmGender"></s:radio>
													</TD>
													<TD><INPUT class=button id=sButton2 type=submit value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>邮箱</TD>
													<TD>职位</TD>
													<TD>备注</TD>
													<TD>所属客户</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="page.records">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${lkmName}</TD>
													<TD>${lkmGender }</TD>
													<TD>${lkmPhone }</TD>
													<TD>${lkmMobile }</TD>
													<TD>${lkmEmail }</TD>
													<TD>${lkmPosition }</TD>
													<TD>${lkmMemo }</TD>
													<TD>${customer.custName}</TD>
													<TD>
													<s:a action="editLinkManUI" namespace="/linkman">
														<s:param name="lkmId" value="%{lkmId}"></s:param>修改
													</s:a>
													&nbsp;&nbsp;
													<s:a href="#" onclick="deleteLinkMan('%{lkmId}')">删除</s:a>
													</TD>
												</TR>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<!-- 引入分页的代码块 -->
										<jsp:include page="../common/page.jsp"></jsp:include>
									</TD>
								</TR> 
							</TBODY>
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
</BODY>
</HTML>
