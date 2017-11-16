package com.itheima.web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request =(HttpServletRequest)req ; 
		HttpServletResponse response =(HttpServletResponse)resp ; 
		try {
			
			//所有Servlet都配置了 dopost设置utf-8 
			request.setCharacterEncoding("utf-8");
			//resonpse 同时设置
			response.setHeader("content-type", "text/html;charset=utf-8");
			
			HttpServletRequest myRequest = (HttpServletRequest)Proxy.newProxyInstance(
					EncodingFilter.class.getClassLoader(), 
					request.getClass().getInterfaces(), 
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							
							//可以进行对getParameter方法进行增强
							if("getParameter".equalsIgnoreCase(method.getName())){
								//判断如果是get方式 执行处理逻辑
								//获得请求方式
								String submitMethod = request.getMethod();
								if("GET".equalsIgnoreCase(submitMethod)){
									
									//处理乱码
									String value = request.getParameter(args[0].toString());
									
									if(value==null){
										return null; 
									}
									return new String(value.getBytes("iso-8859-1"),"utf-8");
									
								}
							}
							//不需要增强的直接放行
							return method.invoke(request, args);
						}
					});
			
			
			
			//放行
			chain.doFilter(myRequest, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
