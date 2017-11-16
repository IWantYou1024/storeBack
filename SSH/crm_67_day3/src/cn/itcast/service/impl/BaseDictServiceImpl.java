package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.entity.BaseDict;
import cn.itcast.service.BaseDictService;

@Service("baseDictService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictDao baseDictDao;
	
	@Override
	/**
	 * 查询所有的客户来源
	 * 	字典表中 ： 002
	 * 	
	 */
	public List<BaseDict> getAllCustomerSource() {
		return baseDictDao.getBaseDictsByType("002");
	}

	/**
	 * 查询所有的客户级别
	 * 	字典表中 ：006
	 */
	public List<BaseDict> getAllCustomerLevel() {
		return baseDictDao.getBaseDictsByType("006");
	}

}
