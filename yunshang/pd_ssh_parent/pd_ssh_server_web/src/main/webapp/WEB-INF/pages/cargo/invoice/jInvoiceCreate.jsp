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
								onclick="formSubmit('invoiceAction_insert','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增发票
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">发票号：</td>
					<td class="tableContent"><input type="text" name="blNo"
						value="" /></td>
					<td class="columnTitle">合同单号</td>
					<td class="tableContent"><input type="text" name="scNo"
						value="" /></td>
					</td>
				</tr>
				<tr>
					<td class="columnTitle">贸易条款：</td>
					<td class="tableContent"><input type="text" name="tradeTerms"
						value="" /></td>
					<td class="columnTitle">创建日期：</td>
					<td class="tableContent"><input type="text"
						style="width: 90px;" name="createTime" value=""
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td>
				</tr>
			</table>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
			委托单列表
		</div>


		<div class="eXtremeTable">
			<table id="ec_table" class="tableRegion" width="98%">
				<thead>
					<tr>
						<td class="tableHeader"><input type="radio" name="selid"
							onclick="checkAll('id',this)"></td>
						<td class="tableHeader">序号</td>
						<td class="tableHeader">SEA海运AIR空运</td>
						<td class="tableHeader">货主</td>
						<td class="tableHeader">装船港</td>
						<td class="tableHeader">转船港</td>
						<td class="tableHeader">卸货港</td>
						<td class="tableHeader">装期</td>
						<td class="tableHeader">效期</td>
						<td class="tableHeader">是否分批</td>
						<td class="tableHeader">是否转船</td>
						<td class="tableHeader">复核人</td>
						<td class="tableHeader">状态</td>
					</tr>
				</thead>
				<tbody class="tableBody">
					${links}
					<c:forEach items="${results}" var="o" varStatus="status">
						<tr bgcolor="#c3f3c3" height="30" class="odd"
							onmouseover="this.className='highlight'"
							onmouseout="this.className='odd'">
							<td><input type="radio" name="id" value="${o.shippingOrderId}" /></td>
							
							<td>${status.index+1}</td>
							<td>${o.orderType}</td>
							<td align="center">
								${o.shipper}
							</td>									
							<td>${o.portOfLoading}</td>
							<td>${o.portOfTrans}</td>
							<td>${o.portOfDischarge}</td>
							<td>${o.loadingDate}</td>
							<td>${o.limitDate}</td>
							<td>${o.isBatch}</td>
							<td>${o.isTrans}</td>
							<td>${o.checkBy}</td>
							<td><c:if test="${o.state==1}">
									<font color="red">待开发票</font>
								</c:if> 
								<c:if test="${o.state==2}">									
									<font color="green">已委托</font>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</form>
</body>
</html>

