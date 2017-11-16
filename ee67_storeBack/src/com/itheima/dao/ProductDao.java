package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Product;

public interface ProductDao {

	int findTotal() throws Exception;

	List<Product> findRows(int startIndex, int pageSize) throws Exception;

	boolean deleteByPid(String pid) throws Exception;

	boolean save(Product product)  throws Exception;

}
