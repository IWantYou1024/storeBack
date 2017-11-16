package com.itheima.service;

import java.util.List;

import com.itheima.domain.Category;
import com.itheima.domain.EasyUIPageBean;

public interface CategoryService {

	EasyUIPageBean<Category> findAll(int pageNumber, int pageSize) throws Exception;

	boolean save(Category category)  throws Exception;

	boolean deleteByCid(String cid)  throws Exception;

	Category findByCid(String cid)   throws Exception;

	boolean update(Category category) throws Exception;

	List<Category> showCategory() throws Exception;

}
