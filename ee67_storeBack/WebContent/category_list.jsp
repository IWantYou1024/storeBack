<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="toolbar">
		<a id="btn" href="javascript:void(0)" onclick="addCategory()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"></a> 
	</div>
	<%-- 需要表格 需不需要导入包  --%>
	<%-- 需要表格 --%>
	<table id="category_list_table"></table>  
	<div id="addCategoryDialog"></div>
	<div id="editCategoryDialog"></div>
	
	<script>
		//将表格渲染成easyui的表格
		$(function(){
			$('#category_list_table').datagrid({    
				//url可以加载远程的数据 数据就是json字符串
			    url:'${pageContext.request.contextPath}/CategoryServlet?method=findAll',    
			    columns:[[    
			        {field:'cid',title:'cid',width:100},    
			        {field:'cname',title:'cname',width:100},    
			        {field:'options',title:'操作',width:100,
			        	//用于格式化
			        	//参数1:value : 原来的数据 json的value值  但如果没有对应的json 是""
			        	//参数2:row  : 表示当前行对象   可以获得其他列的数据
			        	//参数3:index :表示当前索引(没用)
			        	formatter:function(value , row , index){
			        		var str ='&nbsp;&nbsp;<a href="javascript:void(0)" onclick="editByCid(\''+ row.cid +'\')">编辑</a>';
			        		//str +="&nbsp;&nbsp;<a href='javascript:void(0)' onclick='deleteByCid("+row.cid+")'>删除</a>";
			        		str +='&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deleteByCid(\''+ row.cid +'\')">删除</a>';
			        		return str;
			        	}
			        }    
			    ]],
			    
			    fitColumns:true , //列自动填充
			    autoRowHeight:true, //行自动填充
			    fit:true,//表格自动填充
			    singleSelect:true,
			    //分页属性
			    pagination:true,
			    pageNumber:1,
			   	pageSize:5,
			   	pageList:[5,10,15,20],
			   	toolbar:"#toolbar"  // <a href="#..."> href="" 1.热点链接  2.锚点 标记(同一个页面使用 指向一个位置的id)
			});  
		})
		
		
		
		
		//弹出添加的对话框
		function addCategory(){
			//js渲染一个对话框  <div id="addCategoryDialog"></div>
			$("#addCategoryDialog").dialog({
				width:300,
				height:150,
				modal:true,//是否模态
				title:'添加分类',
				href:"category_add.jsp" //页面的内容 也是通过引入
			});
		}
		
		//根据id删除分类
		function deleteByCid(cid){
			//弹出框 是否确定删除数据
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			      //真正开始删除  
			      //发送请求删除数据
			      //location 会修改地址栏
			      //使用ajax删除
			      $.post("${pageContext.request.contextPath}/CategoryServlet",{"method":"deleteByCid","cid":cid} , function(data){
			    	  //获得data判断是否删除成功
			    	  if(data=="true"){
				    	  //成功 提示用户删除数据 1.右下角提示框 2.刷新表格
				    	  //1.右下角提示框
			    		  $.messager.show({
								title:'分类消息',
								msg:'分类删除成功。',
								timeout:5000,
								showType:'slide'
							});

				        	//2.表格自动刷新
				        	$("#category_list_table").datagrid("reload");
			    		  
			    	  }
			      } ,"text");
			      
			      
			      
			    }    
			});  
		}
		
		
		//定义成员变量
		var tempCid;
		//弹出框 里面应该有分类的数据
		function editByCid(cid){
			tempCid = cid;
			$("#editCategoryDialog").dialog({
				width:300,
				height:150,
				modal:true,//是否模态
				title:'编辑分类',
				href:"category_edit.jsp" //页面的内容 也是通过引入
			});
		}
		
	</script>
</body>
</html>