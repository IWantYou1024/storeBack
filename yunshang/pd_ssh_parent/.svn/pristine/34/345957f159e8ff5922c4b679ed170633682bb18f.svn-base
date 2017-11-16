package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ExtCproductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ExtCproduct;
import cn.itcast.service.ExtCproductService;
import cn.itcast.utils.UtilFuns;

@Service("extCproductService")
public class ExtCproductServiceImpl extends BaseServiceImpl<ExtCproduct> implements ExtCproductService {

	@Autowired
	private ContractDao contractDao;

	private ExtCproductDao extCproductDao;

	@Autowired
	public void init(ExtCproductDao extCproductDao) {
		super.baseDao = extCproductDao;
		this.extCproductDao = extCproductDao;
	}

	@Override
	public void saveOrUpdate(ExtCproduct entity) {
		double totalAmount;
		Contract contract = contractDao.getOne(entity.getContractProduct().getContract().getId());
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			entity.setAmount(entity.getPrice() * entity.getCnumber());
			totalAmount = contract.getTotalAmount() + entity.getAmount();
		} else {
			// 修改
			totalAmount = contract.getTotalAmount() - entity.getAmount();
			entity.setAmount(entity.getPrice() * entity.getCnumber());
			totalAmount = totalAmount + entity.getAmount();
		}
		contract.setTotalAmount(totalAmount);
		contractDao.save(contract);

		extCproductDao.save(entity);

	}

	@Override
	public void delete(ExtCproduct entity) {
		ExtCproduct extCproduct = extCproductDao.getOne(entity.getId());
		// 修改合同
		Contract contract = contractDao.getOne(entity.getContractProduct().getContract().getId());
		contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
		contractDao.save(contract);

		// 删除附件
		extCproductDao.delete(extCproduct);
	}

}
