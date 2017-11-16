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
	    <form id="product_add_form" method="post" enctype="multipart/form-data">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>商品名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="pname" data-options="required:true"></input></td>
	    			<td>商品分类:</td>
	    			<td>
						<input id="cid" name="cid" value="请选择" style="width:171px;"> 
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>市场价:</td>
	    			<td><input class="easyui-textbox" type="text" name="market_price" data-options="required:true"></input></td>
	    			<td>商城价:</td>
	    			<td><input class="easyui-textbox" type="text" name="shop_price" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>是否热门:</td>
	    			<td>
	    				<select id="cc" class="easyui-combobox" name="is_hot" style="width:171px;">   
						    <option value="1">是</option>   
						    <option value="0">否</option>   
						</select>  
	    			</td>
	    			<td>上传图片:</td>
	    			<td>
	    				<input class="easyui-filebox" name="pimage" style="width:171px" data-options="buttonIcon:'icon-add',buttonText:''">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>商品描述:</td>
	    			<td><input class="easyui-textbox" name="pdesc" data-options="multiline:true,height:100" type="text" name="name" data-options="required:true"></input></td>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	    </div>
	</div>
	<script>
		$(function(){
			//渲染分类的下拉框
			$('#cid').combobox({    
			    url:'${pageContext.request.contextPath}/CategoryServlet?method=showCategory', //加载servlet的数据  响应json 
			    //[{cid:1 , cname:xxx} ,{cid:1 , cname:xxx} ,{cid:1 , cname:xxx} ,{cid:1 , cname:xxx} ,]
			    // <option value="valueField">文本 textField</option>
			    valueField:'cid',  //  valueField 填写的是json响应的key的名称 为cid    
			    textField:'cname',  // textField 填写的是json响应的key的名称 为cname
			    panelHeight:'auto'
			});  
			
			
			//将表单渲染easyui的表单
			$("#product_add_form").form({
				//request 会失效  注意: 提交的servlet需要新创建一个 因为ProductServlert继承了baseServlet
				//BaseServlet request.getParameter("") 获得不了数据
			    url:"${pageContext.request.contextPath}/MyFileUploadServlet",//Servlet    
			    onSubmit: function(){    
			    },    
			    success:function(data){    
			        if(data=="true"){
			        	//1.关闭窗口
			        	$("#addProductDialog").dialog("close")
			        	//2.右下角提示框
			        	$.messager.show({
							title:'商品消息',
							msg:'商品添加成功。',
							timeout:5000,
							showType:'slide'
						});

			        	//3.表格自动刷新
			        	$("#product_list_table").datagrid("reload");
			        }
			    }    
			})
			
		})
	
		
		function submitForm(){
			$('#product_add_form').form('submit');
		}
		function clearForm(){
			$('#product_add_form').form('clear');
		}
	</script>
</body>
</html>