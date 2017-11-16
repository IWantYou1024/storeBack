<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true,border:0">
	    <form id="edit_category_form" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>分类名称:</td>
	    			<td>
	    				<input type="hidden" name="method" value="editCategory"/>
	    				<input type="hidden" name="cid"/>
	    				<input class="easyui-textbox" type="text" name="cname" ></input>
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submiteditCategoryForm()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cleareditCategoryForm()">Clear</a>
	    </div>
	 </div>
	<script>
		$(function(){
			//页面加载完后 需要加载分类数据
			//1.使用ajax加载分类数据
			//2.easyui提供加载数据的方法  跟表格的url属性一样 可以加载远程的数据 要求 表单中组件的name名称 必须 和 json的key一致 自动数据
			//此处传入cid查询数据库 返回数据 
			//3.直接传入cid和cname 动态添加到文本框 (数据量少) 理由 如果数据量过大 获得 手动赋值繁琐
			$('#edit_category_form').form('load','${pageContext.request.contextPath}/CategoryServlet?method=findByCid&cid='+tempCid);
			
			
			//将普通表单渲染成 ajax提交的表单
			$('#edit_category_form').form({    
			    url:"${pageContext.request.contextPath}/CategoryServlet",//表示提交的路径 相当于 action属性    
			    onSubmit: function(){    //
			        // do some check      做一些校验
			        // return false to prevent submit; return false 表单将不提交    
			    },    
			    success:function(data){  // 回调函数  调用服务器成功时 的响应  一般需要做判断
			        if(data =="true"){
			        	//1.dialog 关闭
			        	$("#editCategoryDialog").dialog("close");
			        	//2.右下角提示框
			        	$.messager.show({
							title:'分类消息',
							msg:'分类修改成功。',
							timeout:5000,
							showType:'slide'
						});

			        	//3.表格自动刷新
			        	$("#category_list_table").datagrid("reload");
			        	
			        }
			    }    
			});  
			
		})
	
	
		function submiteditCategoryForm(){
			$('#edit_category_form').form('submit');
		}
		function cleareditCategoryForm(){
			$('#edit_category_form').form('clear');
		}
	</script>
</body>
</html>