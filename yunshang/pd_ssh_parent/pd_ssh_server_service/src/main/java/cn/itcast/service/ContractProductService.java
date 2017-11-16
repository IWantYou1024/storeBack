package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.cargo.ContractProduct;

public interface ContractProductService extends BaseService<ContractProduct> {

	/**
	 * 根据时间查询货物列表
	 * 
	 * @param shipTime类型为String
	 *            格式为"yyyy-MM"
	 */
	List<ContractProduct> findByShipTime(String shipTime);

	void delete(ContractProduct entity);

	// <E> E get(Class<E> clazz, String id);

}
