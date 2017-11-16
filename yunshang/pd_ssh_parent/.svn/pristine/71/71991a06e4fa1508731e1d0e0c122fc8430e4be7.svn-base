package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.service.ContractService;
import cn.itcast.utils.UtilFuns;

@Service("contractService")
public class ContractServiceImpl extends BaseServiceImpl<Contract> implements ContractService {

	private ContractDao contractDao;

	@Autowired
	public void init(ContractDao contractDao) {
		super.baseDao = contractDao;
		this.contractDao = contractDao;
	}

	@Override
	public void saveOrUpdate(Contract entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			entity.setState(0);
			// 设置总金额, 默认为null
			entity.setTotalAmount(0d);
		}
		contractDao.save(entity);
	}

	@Override
	public void updateState(String[] ids, int state) {
		for (String id : ids) {
			Contract contract = contractDao.getOne(id);
			contract.setState(state);
			contractDao.save(contract);
		}
	}

	@Override
	public List<Contract> findByDelivery(String delivery) {
		return contractDao.findByDelivery(delivery);
	}

}
