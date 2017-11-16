package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.dao.BaseDao;
import cn.itcast.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	protected BaseDao<T> baseDao;
	
	

	@Override
	public List<T> find(Specification<T> spec) {
		return baseDao.findAll(spec);
	}

	@Override
	public T get(String id) {
		return baseDao.findOne(id);
	}

	@Override
	public Page<T> findPage(Specification<T> spec, Pageable pageable) {
		return baseDao.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		baseDao.save(entities);
	}

	@Override
	public void deleteById(String id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			deleteById(id);
		}
	}

}
