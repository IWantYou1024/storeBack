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

import cn.itcast.domain.cargo.ExtCproduct;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.exception.SysException;
import cn.itcast.service.ExtCproductService;
import cn.itcast.service.FactoryService;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "tocreate", location = "/WEB-INF/pages/cargo/contract/jExtCproductCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/contract/jExtCproductUpdate.jsp"),
		@Result(name = "success", type = "redirect", location = "extCproductAction_tocreate", params = {
				"contractProduct.contract.id", "${contractProduct.contract.id}", "contractProduct.id",
				"${contractProduct.id}" }), })
public class ExtCproductAction extends BaseAction<ExtCproduct> {

	@Autowired
	private ExtCproductService extCproductService;
	@Autowired
	private FactoryService factoryService;

	/**
	 * 跳转附件新增页面
	 */
	@Action("extCproductAction_tocreate")
	public String tocreate() throws Exception {
		// 查询所有附件厂家
		Specification<Factory> spec1 = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("ctype").as(String.class), "附件");
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
				return cb.and(p1, p2);
			}
		};
		List<Factory> factoryList = factoryService.find(spec1);
		super.put("factoryList", factoryList);

		// 查询所有同一货物的附件
		Specification<ExtCproduct> spec2 = new Specification<ExtCproduct>() {
			public Predicate toPredicate(Root<ExtCproduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("contractProduct").get("id").as(String.class),
						model.getContractProduct().getId());
			}
		};
		Page<ExtCproduct> page2 = extCproductService.findPage(spec2,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("extCproductAction_tocreate");
		super.push(page);

		return "tocreate";
	}

	/**
	 * 新增附件
	 */
	@Action("extCproductAction_insert")
	public String insert() throws Exception {
		extCproductService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新附件
	 */
	@Action("extCproductAction_toupdate")
	public String toupdate() throws Exception {
		// 查询并圧栈
		ExtCproduct extCproduct = extCproductService.get(model.getId());
		super.push(extCproduct);

		// 查询所有附件厂家
		Specification<Factory> spec = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("ctype").as(String.class), "附件");
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
				return cb.and(p1, p2);
			}
		};
		List<Factory> factoryList = factoryService.find(spec);
		super.put("factoryList", factoryList);
		return "toupdate";
	}

	/**
	 * 更新附件
	 */
	@Action("extCproductAction_update")
	public String update() throws Exception {
		ExtCproduct extCproduct = extCproductService.get(model.getId());
		if (extCproduct.getContractProduct().getContract().getId()
				.equals(model.getContractProduct().getContract().getId())) {
			extCproduct.setFactory(model.getFactory());
			extCproduct.setFactoryName(model.getFactoryName());
			extCproduct.setProductNo(model.getProductNo());
			extCproduct.setProductImage(model.getProductImage());
			extCproduct.setCnumber(model.getCnumber());
			extCproduct.setPackingUnit(model.getPackingUnit());
			extCproduct.setPrice(model.getPrice());
			extCproduct.setOrderNo(model.getOrderNo());
			extCproduct.setProductDesc(model.getProductDesc());
			extCproduct.setProductRequest(model.getProductRequest());
			extCproductService.saveOrUpdate(extCproduct);
		} else {
			throw new SysException("该附件数据有误");
		}
		return SUCCESS;
	}

	/**
	 * 删除附件
	 */
	@Action("extCproductAction_delete")
	public String delete() throws Exception {
		extCproductService.delete(model);
		return SUCCESS;
	}

	@Override
	public ExtCproduct setModel() {
		return new ExtCproduct();
	}

}
