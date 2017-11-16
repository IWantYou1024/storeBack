package cn.itcast.web.action.cargo;

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

import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.ContractService;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/cargo/contract/jContractList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/cargo/contract/jContractView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/cargo/contract/jContractCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/contract/jContractUpdate.jsp"),
		@Result(name = "contractList", location = "/WEB-INF/pages/cargo/export/jContractList.jsp"),
		@Result(name = "success", type = "redirect", location = "contractAction_list") })
public class ContractAction extends BaseAction<Contract> {

	@Autowired
	private ContractService contractService;

	/**
	 * 跳转购销合同列表
	 */
	@Action("contractAction_list")
	public String list() throws Exception {
		//权限
		final User user = super.getCurUser();
		final Integer degree = user.getUserinfo().getDegree();

		Specification<Contract> spec = new Specification<Contract>() {
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = null;
				if (degree == 4) {
					// 员工
					p = cb.equal(root.get("createBy").as(String.class), user.getId());
				} else if (degree == 3) {
					// 部门经理
					p = cb.equal(root.get("createDept").as(String.class), user.getDept().getId());
				} else if (degree == 2) {
					// 管理本部门及下属部门
				} else if (degree == 1) {
					// 管理跨部门跨人员
				} else if (degree == 1) {
					// 总经理
					// 什么都不写
				}
				return p;
			}
		};

		Page<Contract> page2 = contractService.findPage(spec,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("contractAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 跳转查看合同
	 */
	@Action("contractAction_toview")
	public String toview() throws Exception {
		Contract contract = contractService.get(model.getId());
		super.push(contract);
		return "toview";
	}

	/**
	 * 跳转新增合同
	 */
	@Action("contractAction_tocreate")
	public String tocreate() throws Exception {
		return "tocreate";
	}

	/**
	 * 新增合同
	 */
	@Action("contractAction_insert")
	public String insert() throws Exception {
		// 保存创建信息
		model.setCreateBy(getCurUser().getId());
		model.setCreateDept(getCurUser().getDept().getId());
		contractService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新合同
	 */
	@Action("contractAction_toupdate")
	public String toupdate() throws Exception {
		Contract contract = contractService.get(model.getId());
		super.push(contract);
		return "toupdate";
	}

	/**
	 * 更新合同
	 */
	@Action("contractAction_update")
	public String update() throws Exception {
		Contract contract = contractService.get(model.getId());

		contract.setCustomName(model.getCustomName());
		contract.setPrintStyle(model.getPrintStyle());
		contract.setContractNo(model.getContractNo());
		contract.setOfferor(model.getOfferor());
		contract.setInputBy(model.getInputBy());
		contract.setCheckBy(model.getCheckBy());
		contract.setInspector(model.getInspector());
		contract.setSigningDate(model.getSigningDate());
		contract.setImportNum(model.getImportNum());
		contract.setShipTime(model.getShipTime());
		contract.setTradeTerms(model.getTradeTerms());
		contract.setDeliveryPeriod(model.getDeliveryPeriod());
		contract.setCrequest(model.getCrequest());
		contract.setRemark(model.getRemark());

		// 保存更新信息
		contract.setUpdateBy(getCurUser().getId());

		contractService.saveOrUpdate(contract);
		return SUCCESS;
	}

	/**
	 * 删除合同
	 */
	@Action("contractAction_delete")
	public String delete() throws Exception {
		contractService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	/**
	 * 提交合同, 修改状态为1
	 */
	@Action("contractAction_submit")
	public String submit() throws Exception {
		contractService.updateState(model.getId().split(", "), 1);
		return SUCCESS;
	}

	/**
	 * 取消合同, 修改状态为0
	 */
	@Action("contractAction_cancel")
	public String cancel() throws Exception {
		contractService.updateState(model.getId().split(", "), 0);
		return SUCCESS;
	}

	/**
	 * 出口报运合同查询 仅查询状态为1的合同
	 */
	@Action("exportAction_contractList")
	public String contractList() throws Exception {
		Specification<Contract> spec = new Specification<Contract>() {
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		Page<Contract> page2 = contractService.findPage(spec,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("exportAction_contractList");
		super.push(page);
		return "contractList";
	}

	@Override
	public Contract setModel() {
		return new Contract();
	}

}
