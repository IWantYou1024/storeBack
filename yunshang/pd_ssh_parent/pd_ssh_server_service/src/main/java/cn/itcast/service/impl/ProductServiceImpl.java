package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ProductDao;
import cn.itcast.domain.cargo.Product;
import cn.itcast.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	private ProductDao productDao;
	
	@Autowired
	public void init(ProductDao productDao) {
		super.baseDao = productDao;
		this.baseDao = productDao;
	}
	
}
