<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 需要表格 需不需要导入包  --%>
	<div id="productToolBar">
		<a id="btn" href="javascript:void(0)" onclick="addProduct()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"></a> 
	</div>
	
	<table id="product_list_table"></table> 
	
	<div id="addProductDialog"></div>
	<script>
	
		$(function(){
			//渲染表格
			$('#product_list_table').datagrid({    
			    url:'${pageContext.request.contextPath}/ProductServlet?method=findAll', //将url修改成Servlet加载数据即可
			    columns:[[    
			        {field:'pid',title:'pid',width:100},    
			        {field:'pname',title:'pname',width:100},    
			        {field:'market_price',title:'market_price',width:100,align:'right'},  
			        {field:'shop_price',title:'shop_price',width:100,align:'right'},  
			        {field:'pdate',title:'pdate',width:100,align:'right'},  
			        
			        //1.数据库语句 case when then end 
			        //2.java代码中修改
			        //3.显示修改 formatter
			        {field:'is_hot',title:'is_hot',width:100,align:'right',
			        	formatter:function(value){
			        		if(value=="1"){
			        			return "热门";
			        		}
			        		return "非热门";
			        	}	
			        },  
			        {field:'pdesc',title:'pdesc',width:100,align:'right'},  
			        {field:'pflag',title:'pflag',width:100,align:'right',
			        	formatter:function(value){
			        		if(value=="1"){
			        			return "上架";
			        		}
			        		return "下架";
			        	}	
			        },  
			        //后台的字段名称叫category 此时显示cid
			        {field:'category',title:'category',width:100,align:'right',
			        	formatter:function(value){
			        		//value 等效category对象
			        		//alert(value);
			        		//排除掉第一行
			        		if(value!=null){//!=null   javascript 会自动类型强转
			        			// undefinded 判断却是null
				        		//javascript 的基本类型  string boolean object undefined number 
			        			return value.cname;
			        		}
			        	}	
			        },  
			        
			        
			        //图片响应回来的是路径  重写路径 响应图片
			        {field:'pimage',title:'pimage',width:100,align:'right',
			        	formatter:function(value , rows , index){
			        		return "<img src='"+value+"' style='width:50px'>";
			        	}	
			        },
			        {field:'options',title:'options',width:100,
			        	formatter:function(value , rows ,index){
			        		var str = "<a href=''>编辑</a>&nbsp;&nbsp";
			        		str += '<a href="javascript:void(0)" onclick="deleteByPid(\''+rows.pid+'\')">删除</a>';
			        		return str;
			        	}	
			        }
			        
			    ]],
			    fitColumns:true,
			    autoRowHeight:true,
			    fit:true, 
			    singleSelect:true,
			    pagination:true,
			    pageNumber:1,
			    pageSize:5,
			    pageList:[5,10,15,20],
			    toolbar:"#productToolBar"
			});  			
		})
		//删除函数
		function deleteByPid(pid){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			       //删除
			       //发送ajax请求
			       $.post("${pageContext.request.contextPath}/ProductServlet",{"method":"deleteByPid","pid":pid} , function(data){
			    	   //删除成功
			    	   if(data=="true"){
			    		   //1.右下角提示框
			    		   $.messager.show({
								title:'商品消息',
								msg:'商品删除成功',
								timeout:5000,
								showType:'slide'
							});

			    		   //2.刷新表格
			    		   $("#product_list_table").datagrid("reload");
			    	   }
			    	   
			       } , "text");
			       
			       
			    }    
			});  
		}
		
		//弹出添加的对话框
		function addProduct(){
			$("#addProductDialog").dialog({
				width:550,
				height:300,
				modal:true,
				title:'商品添加',
				href:"product_add.jsp"
			});
		}
		
	</script>
</body>
</html>