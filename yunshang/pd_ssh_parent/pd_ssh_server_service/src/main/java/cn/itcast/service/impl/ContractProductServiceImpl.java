package cn.itcast.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.ExtCproduct;
import cn.itcast.service.ContractProductService;
import cn.itcast.utils.UtilFuns;

@Service("contractProductService")
public class ContractProductServiceImpl extends BaseServiceImpl<ContractProduct> implements ContractProductService {
	@Autowired
	private ContractDao contractDao;

	private ContractProductDao contractProductDao;

	@Autowired
	public void init(ContractProductDao contractProductDao) {
		super.baseDao = contractProductDao;
		this.contractProductDao = contractProductDao;
	}

	@Override
	public void saveOrUpdate(ContractProduct entity) {
		double totalAmount;
		Contract contract = contractDao.getOne(entity.getContract().getId());
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// 货物表
			entity.setOutNumber(0); // 出货量
			entity.setFinished(0); // 是否完成
			entity.setAmount(entity.getPrice() * entity.getCnumber()); // 总金额

			// 购销合同表
			totalAmount = contract.getTotalAmount() + entity.getAmount();
		} else {
			// 修改
			totalAmount = contract.getTotalAmount() - entity.getAmount();
			entity.setAmount(entity.getPrice() * entity.getCnumber());
			totalAmount = totalAmount + entity.getAmount();
		}
		contract.setTotalAmount(totalAmount);
		contractDao.save(contract);

		contractProductDao.save(entity);
	}

	@Override
	public void delete(ContractProduct entity) {
		ContractProduct contractProduct = contractProductDao.getOne(entity.getId());

		// 附件金额
		double extCamount = 0;
		Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
		if (extCproducts.size() > 0) {
			for (ExtCproduct extCproduct : extCproducts) {
				extCamount += extCproduct.getAmount();
			}
		}
		// 修改合同
		Contract contract = contractDao.getOne(entity.getContract().getId());
		contract.setTotalAmount(contract.getTotalAmount() - contractProduct.getAmount() - extCamount);
		contractDao.save(contract);

		// 删除货物
		contractProductDao.delete(contractProduct);
	}

	@Override
	public List<ContractProduct> findByShipTime(String shipTime) {
		return contractProductDao.findByShipTime(shipTime);
	}

}
