package cn.itcast.web.action.cargo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.exception.SysException;
import cn.itcast.service.ContractProductService;
import cn.itcast.service.FactoryService;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "tocreate", location = "/WEB-INF/pages/cargo/contract/jContractProductCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/contract/jContractProductUpdate.jsp"),
		@Result(name = "success", type = "redirect", location = "contractProductAction_tocreate", params = {
				"contract.id", "${contract.id}" }) })
public class ContractProductAction extends BaseAction<ContractProduct> {

	@Autowired
	private ContractProductService contractProductService;
	@Autowired
	private FactoryService factoryService;

	/**
	 * 跳转新增货物页面
	 */
	@Action("contractProductAction_tocreate")
	public String tocreate() throws Exception {
		// 查询所有厂家
		Specification<Factory> spec1 = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("ctype").as(String.class), "货物");
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
				return cb.and(p1, p2);
			}
		};
		List<Factory> factoryList = factoryService.find(spec1);
		super.put("factoryList", factoryList);

		// 查询同一合同的所有货物
		Specification<ContractProduct> spec2 = new Specification<ContractProduct>() {
			public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("contract").get("id").as(String.class), model.getContract().getId());
			}
		};
		Page<ContractProduct> page2 = contractProductService.findPage(spec2,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("contractProductAction_tocreate");
		super.push(page);

		return "tocreate";
	}

	/**
	 * 新增货物
	 */
	@Action("contractProductAction_insert")
	public String insert() throws Exception {
		contractProductService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新货物
	 */
	@Action("contractProductAction_toupdate")
	public String toupdate() throws Exception {
		// 查询当前货物并圧栈
		ContractProduct contractProduct = contractProductService.get(model.getId());
		super.push(contractProduct);

		// 查询所有厂家信息
		Specification<Factory> spec = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("ctype").as(String.class), "货物");
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
				return cb.and(p1, p2);
			}
		};
		List<Factory> factoryList = factoryService.find(spec);
		super.put("factoryList", factoryList);
		return "toupdate";
	}

	/**
	 * 更新货物数据
	 */
	@Action("contractProductAction_update")
	public String update() throws Exception {
		ContractProduct contractProduct = contractProductService.get(model.getId());
		if (contractProduct.getContract().getId().equals(model.getContract().getId())) {
			contractProduct.setFactory(model.getFactory());
			contractProduct.setFactoryName(model.getFactoryName());
			contractProduct.setProductNo(model.getProductNo());
			contractProduct.setProductImage(model.getProductImage());
			contractProduct.setCnumber(model.getCnumber());
			contractProduct.setPackingUnit(model.getPackingUnit());
			contractProduct.setLoadingRate(model.getLoadingRate());
			contractProduct.setBoxNum(model.getBoxNum());
			contractProduct.setPrice(model.getPrice());
			contractProduct.setOrderNo(model.getOrderNo());
			contractProduct.setProductDesc(model.getProductDesc());
			contractProduct.setProductRequest(model.getProductRequest());
			contractProductService.saveOrUpdate(contractProduct);
		} else {
			throw new SysException("该货物数据有误");
		}
		return SUCCESS;
	}

	/**
	 * 删除货物
	 */
	@Action("contractProductAction_delete")
	public String delete() throws Exception {
		contractProductService.delete(model);
		return SUCCESS;
	}

	@Override
	public ContractProduct setModel() {
		return new ContractProduct();
	}

}
