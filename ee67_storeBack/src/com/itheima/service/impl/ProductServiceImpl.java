package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.EasyUIPageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public EasyUIPageBean<Product> findAll(int pageNumber, int pageSize) throws Exception {
		//1.创建返回对象
		EasyUIPageBean<Product> easyUIPageBean = new EasyUIPageBean<Product>();
		
		ProductDao dao = new ProductDaoImpl();
		//4.查询总记录数
		int total = dao.findTotal();
		
		//5.查询分页数据
		List<Product> rows = dao.findRows( (pageNumber -1) * pageSize , pageSize);
		//3.封装
		easyUIPageBean.setRows(rows);
		easyUIPageBean.setTotal(total);
		
		
		//2.返回
		return easyUIPageBean;
	}

	@Override
	public boolean deleteByPid(String pid) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		return dao.deleteByPid(pid);
	}

	@Override
	public boolean save(Product product) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		return dao.save(product);
	}

}
