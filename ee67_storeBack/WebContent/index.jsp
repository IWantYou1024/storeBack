<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>

	<script>
		function addTabs(targetName, urlName){
			//点击添加选项卡
			//判断是否有选项卡的存在
			var flag = $("#tt").tabs("exists",targetName);
			if(flag){
				//1.如果有选中
				$("#tt").tabs("select",targetName);
				return;
			}
			//2.如果没有添加
			$('#tt').tabs('add',{
				title: targetName,
				selected: true,
				closable:true,
				href:urlName  //将其他页面的内容引入
			});

		}
			
	</script>
</head>
<body>
	<%-- 布局容器 layout start --%>
	<div id="cc" class="easyui-layout" data-options="fit:true">   
	    <div data-options="region:'north',title:'North Title',split:true" style="height:150px;" align="center">
	    	<h1>网站的后台管理系统</h1>
	    </div> 
	      
	    <div data-options="region:'west',title:'管理系统',split:true" style="width:200px;">
	    	<%-- 手风琴 start --%>
	    	<div id="aa" class="easyui-accordion" data-options="fit:true,border:0">   
			    <div title="Title1" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">   
			    	<%-- 树结构 tree start --%>
			    	<ul id="ttr" class="easyui-tree">   
					    <li>   
					        <span>菜单树</span>   
					        <ul>   
					            <li>   
					                <span>分类管理</span>   
					                <ul>   
					                    <li>   
					                        <span><a href="javascript:void(0)" onclick="addTabs('分类信息','category_list.jsp')">分类信息</a></span>   
					                    </li>   
					                </ul>   
					            </li>   
					            <li>   
					                <span>商品管理</span> 
					                 <ul>   
					                    <li>   
					                        <span><a href="javascript:void(0)" onclick="addTabs('商品信息','product_list.jsp')">商品信息</a></span>   
					                    </li>   
					                </ul>     
					            </li>   
					            <li>   
					                <span>File 3</span>   
					            </li>   
					        </ul>   
					    </li>   
					    <li>   
					        <span>File21</span>   
					    </li>   
					</ul>  
			    	
			    	<%-- 树结构 tree end --%>
			    </div>   
			    <div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;">   
			    </div>   
			    <div title="Title3">   
			    </div>   
			</div> 
	    	<%-- 手风琴 end --%>
	    </div>   
	    <div data-options="region:'center'" >
	    
	    	<%-- 选项卡start --%>
	    	<div id="tt" class="easyui-tabs" data-options="fit:true,border:0">   
			    <div title="主页" >   
			       	 欢迎光临
			    </div>   
			</div> 
	    	<%-- 选项卡end --%>
	    
	    </div>   
	</div>  
	<%-- 布局容器 layout end --%>
</body>
</html>