package cn.itcast.client.service.impl;

import java.io.Serializable;

import cn.itcast.client.dao.BaseDao;
import cn.itcast.client.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	@Override
	public T get(Serializable id) {
		return baseDao.getOne(id);
	}

	@Override
	public void saveOrUpdate(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		baseDao.delete(id);
	}

}
