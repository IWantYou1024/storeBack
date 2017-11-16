package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.FactoryDao;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.service.FactoryService;

@Service("factoryService")
public class FactoryServiceImpl extends BaseServiceImpl<Factory> implements FactoryService {

	@SuppressWarnings("unused")
	private FactoryDao factoryDao;

	@Autowired
	public void init(FactoryDao factoryDao) {
		super.baseDao = factoryDao;
		this.factoryDao = factoryDao;
	}

}
