package cn.itcast.client.service;

import java.io.Serializable;

public interface BaseService<T> {

	public T get(Serializable id);

	public void saveOrUpdate(T entity);

	public void deleteById(Serializable id);

}
