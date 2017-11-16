package com.itheima.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll(int startIndex, int pageSize) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select * from category limit ?,? ";
		Object [] params = {
				startIndex , pageSize
		};
		
		return queryRunner.query(sql, new BeanListHandler<Category>(Category.class), params);
	}

	@Override
	public int findTotal() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select count(*) from category ";
		Object [] params = {
		};
		Object query = queryRunner.query(sql, new ScalarHandler(), params);
		return Integer.valueOf(query.toString());
	}

	@Override
	public boolean save(Category category) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " insert into category values(?,?) ";
		Object [] params = {
				category.getCid() , category.getCname()
		};
		return queryRunner.update(sql, params)>0;
	}

	@Override
	public boolean deleteByCid(String cid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " delete from category where cid = ?  ";
		Object [] params = {
				cid
		};
		return queryRunner.update(sql, params)>0;
	}

	@Override
	public Category findByCid(String cid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select * from category where cid = ?   ";
		Object [] params = {
				cid
		};
		return queryRunner.query(sql, new BeanHandler<Category>(Category.class), params);
	}

	@Override
	public boolean update(Category category) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " update category set cname = ? where cid = ?   ";
		Object [] params = {
				category.getCname() , category.getCid()
		};
		return queryRunner.update(sql , params )>0;
	}

	@Override
	public List<Category> showCategory() throws Exception {
QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select * from category ";
		Object [] params = {
				
		};
		
		return queryRunner.query(sql, new BeanListHandler<Category>(Category.class), params);
	}

}
