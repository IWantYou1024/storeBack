package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.itheima.domain.Category;
import com.itheima.domain.EasyUIPageBean;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.utils.UUIDUtils;
import com.itheima.web.base.servlet.BaseServlet;

import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	/**
	 * 查询所有(分页)
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获得
			String pageNumberStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("rows");
			int pageNumber = Integer.valueOf(pageNumberStr);
			int pageSize = Integer.valueOf(pageSizeStr);
			
			//2.处理
			CategoryService service = new CategoryServiceImpl();
			EasyUIPageBean<Category> pageBean = service.findAll(pageNumber ,pageSize);
			
			//3.响应
			//响应json字符串
			Gson gson = new Gson();
			String json = gson.toJson(pageBean);
			System.out.println(json);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			//1.获得
			String cname = request.getParameter("cname");
			Category category = new Category();
			category.setCname(cname);
			category.setCid(UUIDUtils.getId());
			
			//2.处理
			CategoryService service = new CategoryServiceImpl();
			//需要返回值  给浏览器做判断使用
			boolean flag = service.save(category);
			
			/*if(flag){
				//清空缓存
				Jedis jedis = new Jedis();
				jedis.del("STORE_CATEGORY_LIST_JSON");
				jedis.close();
			}*/
			
			
			//3.响应
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据id删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.获得
			String cid = request.getParameter("cid");
			//2.处理
			CategoryService service = new CategoryServiceImpl();
			boolean flag = service.deleteByCid(cid);
			//3.响应
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据cid查询出category对象
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//获得
			String cid = request.getParameter("cid");
			//处理
			CategoryService service = new CategoryServiceImpl();
			Category category = service.findByCid(cid);
			//响应
			Gson gson = new Gson();
			String json = gson.toJson(category);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据cid修改cname
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.获得
			Category category = new Category();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(category, map);
			
			//2.处理
			CategoryService service = new CategoryServiceImpl();
			boolean flag = service.update(category);
			//3.响应
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 获得所有的分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.获得
			//2.处理
			CategoryService service = new CategoryServiceImpl();
			List<Category> list = service.showCategory();
			//3.响应
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}
