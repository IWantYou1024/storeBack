package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.home.CustomerMenuDao;
import cn.itcast.domain.home.CustomerMenu;
import cn.itcast.service.CustomerMenuService;

@Service("customerMenuService")
public class CustomerMenuServiceImpl extends BaseServiceImpl<CustomerMenu> implements CustomerMenuService {

	private CustomerMenuDao customerMenuDao;

	@Autowired
	public void init(CustomerMenuDao customerMenuDao) {
		super.baseDao = customerMenuDao;
		this.customerMenuDao = customerMenuDao;
	}

}
