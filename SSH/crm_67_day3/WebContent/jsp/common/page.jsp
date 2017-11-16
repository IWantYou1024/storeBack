<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function to_page(page){
	if(page){
		$("#page").val(page);
	}
	document.linkManForm.submit();	
}
</script>
<SPAN id=pagelink>
	<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
		共[<B>${page.totalRecordNum}</B>]条记录,[<B>${page.totalPageNum}</B>]页
		,每页显示
		<s:select list="{5,10}" name="ps">
			
		</s:select>
		条
		[<A href="javascript:to_page(${page.prePageNum})">前一页</A>]
		<B>${page.currentPageNum}</B>
		[<A href="javascript:to_page(${page.nextPageNum})">后一页</A>] 
		到
		<s:textfield  size="3" id="page" name="num"></s:textfield>
		页
		<input type="button" value="Go" onclick="to_page()"/>
	</DIV>
</SPAN>