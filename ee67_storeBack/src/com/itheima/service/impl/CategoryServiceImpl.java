package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.domain.EasyUIPageBean;
import com.itheima.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public EasyUIPageBean<Category> findAll(int pageNumber, int pageSize) throws Exception {
		//1.创建pageBean
		EasyUIPageBean<Category> easyUIPageBean = new EasyUIPageBean<Category>();
		
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> rows = dao.findAll( (pageNumber - 1) * pageSize , pageSize  );
		
		int total = dao.findTotal();
		//3.封装
		easyUIPageBean.setRows(rows);
		easyUIPageBean.setTotal(total);
		//2.返回
		return easyUIPageBean;
	}

	@Override
	public boolean save(Category category) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.save(category);
	}

	@Override
	public boolean deleteByCid(String cid) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.deleteByCid(cid);
	}

	@Override
	public Category findByCid(String cid) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findByCid(cid);
	}

	@Override
	public boolean update(Category category) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.update(category);
	}

	@Override
	public List<Category> showCategory() throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.showCategory();
	}

}
