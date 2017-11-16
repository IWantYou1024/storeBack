package com.itheima.web.base.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/BaseServlet"})
public class BaseServlet extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		/**
			服务器每一个servlet做了一个功能
			为了解决上面的问题: 在jsp页面调用服务器后台代码时 传递了一个参数 method   
			http://localhost:8080/项目名/Servlet名称?method=findAll 
			在Servlet中获得到每一个请求传过来的method方法 判断 执行具体的if条件的方法  可以认为每一个if就是一个Servlet
			以后如果请求的方法过多 if条件写起来麻烦 所以进一步优化
			整段代码目的: 
				1.获得页面请求的方法名     ==>> 所有需要调用的方法名已知
				2.匹配具体需要执行的方法 ==>> 通过反射获得当前的方法对象  tomcat 创建实例对象
				3.执行方法				反射执行方法即可
				
		*/
			//request.getMethod();  获得请求方式
			//1.获得需要 执行的方法名称
			String method = request.getParameter("method");
			
			//以后方法有可能为null 没有传入method   问题需要解决
			if(method == null || "".equals(method)){
				method = "index";
			}
			
			
			
			//2.通过方法名 获得需要执行的方法对象
			//2.1 拿到Class对象   如果有实例对象 就使用实例对象
			Class clazz = this.getClass();
			//2.2 根据class对象获得方法
			Method invokeMethod = clazz.getMethod(method, HttpServletRequest.class ,HttpServletResponse.class );
		
			//3.执行方法
			String path = (String) invokeMethod.invoke(this, request, response);
			//String path = this.findAll(request, response);
			if(path != null){//path不为null 一定是请求转发  因为 重定向 让每一个方法自己玩
				request.getRequestDispatcher(path).forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
