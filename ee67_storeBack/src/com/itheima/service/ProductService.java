package com.itheima.service;

import com.itheima.domain.EasyUIPageBean;
import com.itheima.domain.Product;

public interface ProductService {

	EasyUIPageBean<Product> findAll(int pageNumber, int pageSize) throws Exception;

	boolean deleteByPid(String pid) throws Exception;

	boolean save(Product product) throws Exception;

}
