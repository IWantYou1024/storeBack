package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.cargo.Contract;

public interface ContractService extends BaseService<Contract> {

	/**
	 * 修改合同状态
	 */
	void updateState(String[] ids, int state);

	/**
	 * 根据交期查询合同
	 */
	List<Contract> findByDelivery(String delivery);

}
