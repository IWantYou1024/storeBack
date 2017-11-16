package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.dao.cargo.ExportDao;
import cn.itcast.dao.cargo.ExportProductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.ExportProduct;
import cn.itcast.domain.cargo.ExtCproduct;
import cn.itcast.domain.cargo.ExtEproduct;
import cn.itcast.domain.vo.ExportProductResult;
import cn.itcast.domain.vo.ExportResult;
import cn.itcast.service.ExportService;
import cn.itcast.utils.UtilFuns;

@Service("exportService")
public class ExportServiceImpl extends BaseServiceImpl<Export> implements ExportService {

	private ExportDao exportDao;

	@Autowired
	private ContractProductDao contractProductDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ExportProductDao exportProductDao;

	@Autowired
	public void init(ExportDao exportDao) {
		super.baseDao = exportDao;
		this.exportDao = exportDao;
	}

	@Override
	public void saveOrUpdate(Export entity) {
		Integer boxNums = 0; // 报运总箱数
		Double grossWeights = 0d; // 报运总毛重
		Double measurements = 0d; // 报运总体积
		if (UtilFuns.isEmpty(entity.getId())) {
			final Object[] split = entity.getContractIds().split(", ");
			// 新增
			entity.setState(0); // 状态为草稿
			entity.setInputDate(new Date());

			// 获取所有要报运的合同 并修改状态
			StringBuilder sb = new StringBuilder();
			for (Object id : split) {
				Contract contract = contractDao.getOne(id.toString());
				// 拼接购销合同号
				sb.append(contract.getContractNo()).append(" ");
				// 修改状态为2
				contract.setState(2);
				contractDao.save(contract);
			}

			// 设置合同号
			entity.setCustomerContract(sb.toString());

			// 查询所有货物
			Specification<ContractProduct> spec = new Specification<ContractProduct>() {
				public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Expression<String> as = root.join("contract").get("id").as(String.class);
					return as.in(split);
				}
			};
			List<ContractProduct> cpList = contractProductDao.findAll(spec);// 合同货物集合
			Set<ExportProduct> epSet = entity.getExportProducts(); // 报运单货物集合

			// 遍历合同货物, 将其转存为报运单货物
			for (ContractProduct cp : cpList) {
				ExportProduct ep = new ExportProduct(); // 报运单货物
				Set<ExtEproduct> extEpSet = ep.getExtEproducts(); // 报运单货物的附件集合
				// 遍历合同货物附件, 将其转存为报运单货物附件
				for (ExtCproduct extCp : cp.getExtCproducts()) {
					ExtEproduct extEp = new ExtEproduct(); // 报运单货物

					// 使用BeanUtils拷贝数据
					BeanUtils.copyProperties(extCp, extEp);
					// 将id置为null
					extEp.setId(null);
					extEp.setExportProduct(ep);
					/*
					 * extEp.setFactory(cp.getFactory());// 工厂
					 * extEp.setExportProduct(ep);
					 * extEp.setProductNo(extCp.getProductNo());
					 * extEp.setProductImage(extCp.getProductImage());
					 * extEp.setProductDesc(extCp.getProductDesc());
					 * extEp.setCnumber(extCp.getCnumber());
					 * extEp.setPackingUnit(extCp.getPackingUnit());
					 * extEp.setPrice(extCp.getPrice());
					 * extEp.setAmount(extCp.getAmount());
					 * extEp.setProductRequest(extCp.getProductRequest());
					 * extEp.setOrderNo(extCp.getOrderNo());
					 */

					// 将报运单货物下的附件加入到集合中
					extEpSet.add(extEp);
				}
				// 为报运单货物下的附件集合赋值
				ep.setExtEproducts(extEpSet);

				// 为报运单货物下的其他值赋值
				ep.setExport(entity);
				ep.setFactory(cp.getFactory());
				ep.setProductNo(cp.getProductNo());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setCnumber(cp.getCnumber());
				ep.setBoxNum(cp.getBoxNum());
				ep.setPrice(cp.getPrice());
				ep.setOrderNo(cp.getOrderNo());

				/**
				 * 没有的
				 */
				ep.setGrossWeight(10d); // 毛重
				ep.setNetWeight(10d); // 净重
				ep.setSizeLength(10d); // 尺寸长
				ep.setSizeWidth(10d); // 尺寸宽
				ep.setSizeHeight(10d); // 尺寸高
				ep.setExPrice(10d); // 出口单价
				ep.setTax(10d);

				// 将报运单货物加到集合中
				epSet.add(ep);

				/**
				 * 几个数的累加
				 */
				boxNums += ep.getBoxNum();
				grossWeights += ep.getGrossWeight();
				measurements += ep.getSizeLength() * ep.getSizeWidth() * ep.getSizeHeight();
			}
			// 为报运单的货物集合赋值
			entity.setExportProducts(epSet);

			// entity.setBoxNums(boxNums);
			// entity.setGrossWeights(grossWeights);
			// entity.setMeasurements(measurements);
		} else {
			// 更新
			// 修改冗余字段
			for (ExportProduct ep : entity.getExportProducts()) {
				boxNums += ep.getBoxNum();
				grossWeights += ep.getGrossWeight();
				measurements += ep.getSizeLength() * ep.getSizeWidth() * ep.getSizeHeight();
			}
			entity.setBoxNums(boxNums);
			entity.setGrossWeights(grossWeights);
			entity.setMeasurements(measurements);
		}
		exportDao.save(entity);
	}

	@Override
	public void updateState(String[] ids, int state) {
		for (String id : ids) {
			Export export = exportDao.getOne(id);
			export.setState(state);
			exportDao.save(export);
		}
	}

	@Override
	public void updateExportE(ExportResult result) {
		Export export = exportDao.getOne(result.getExportId());
		export.setState(result.getState());
		export.setRemark(result.getRemark());
		exportDao.save(export);

		Set<ExportProductResult> products = result.getProducts();
		for (ExportProductResult exportProductResult : products) {
			ExportProduct exportProduct = exportProductDao.getOne(exportProductResult.getExportProductId());
			exportProduct.setTax(exportProductResult.getTax());
			exportProductDao.save(exportProduct);
		}
	}

}
