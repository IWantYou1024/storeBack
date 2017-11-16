package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Category;

public interface CategoryDao {

	List<Category> findAll(int startIndex, int pageSize) throws 	Exception;

	int findTotal() throws Exception;

	boolean save(Category category)  throws Exception;

	boolean deleteByCid(String cid) throws Exception;

	Category findByCid(String cid) throws Exception;

	boolean update(Category category)  throws Exception;

	List<Category> showCategory() throws Exception;

}
