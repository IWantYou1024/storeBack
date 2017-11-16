package com.itheima.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public int findTotal() throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select count(*) from product ";
		
		Object [] params = {};
		
		Object query = queryRunner.query(sql, new ScalarHandler(), params);
		return 	Integer.valueOf(query.toString());
	}

	@Override
	public List<Product> findRows(int startIndex, int pageSize) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = " select * from product p, category c  where p.cid = c.cid order by pdate desc limit ?,? ";
		
		Object [] params = { startIndex , pageSize};
		
		//只是商品的数据
		//List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), params);
		//策略设计模式  // 查询出来的包含了 商品和分类的数据
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), params);
		
		//创建一个返回的对象 List集合
		List<Product>  retList = new ArrayList<Product>();
		
		for (Map<String, Object> map : list) {
			//1.创建product
			Product product = new Product();
			//2.创建category对象
			Category category = new Category();
			
			BeanUtils.populate(product, map);
			BeanUtils.populate(category, map);
			
			//3.将category封装到product中
			product.setCategory(category);
			
			//4.将product封装到list中
			retList.add(product);
		}
		
		
		return 	retList;
	}

	@Override
	public boolean deleteByPid(String pid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid = ? ";
		Object [] params = {pid};
		
		
		return queryRunner.update(sql, params) > 0;
	}

	@Override
	public boolean save(Product product) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = " insert into product values(?,?,?,?,?,?,?,?,?,?) ";
		Object [] params = {
				product.getPid() , product.getPname() ,product.getMarket_price(),
				product.getShop_price() , product.getPimage() , product.getPdate(),
				product.getIs_hot() , product.getPdesc() , product.getPflag() , 
				product.getCategory().getCid()
		};
		
		
		return queryRunner.update(sql, params) > 0;
	}

}
