package com.itheima.web.servlet;

import com.google.gson.Gson;
import com.itheima.domain.EasyUIPageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.web.base.servlet.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends BaseServlet {

	/**
	 * 分页查询商品数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获得
			//获得分页数据
			String pageNumberStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("rows");
			
			int pageNumber = Integer.valueOf(pageNumberStr) ;
			int pageSize = Integer.valueOf(pageSizeStr);
			//2.处理
			ProductService service = new ProductServiceImpl();
			EasyUIPageBean<Product> easyUIPageBean  = service.findAll( pageNumber , pageSize);
			
			//3.响应
			//将对象转换成json
			Gson gson = new Gson();
			String json = gson.toJson(easyUIPageBean);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据pid删除数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteByPid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.获得
			String pid = request.getParameter("pid");
			//2.处理
			ProductService service = new ProductServiceImpl();
			boolean flag = service.deleteByPid(pid);
			//3.响应
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
