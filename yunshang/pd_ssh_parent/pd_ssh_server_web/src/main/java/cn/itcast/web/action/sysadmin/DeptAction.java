package cn.itcast.web.action.sysadmin;

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

import cn.itcast.domain.sysadmin.Dept;
import cn.itcast.service.DeptService;
import cn.itcast.web.action.BaseAction;

@Namespace("/sysadmin")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/sysadmin/dept/jDeptList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/sysadmin/dept/jDeptView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/dept/jDeptCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/dept/jDeptUpdate.jsp"),
		@Result(name = "success", type = "redirect", location = "deptAction_list") })
public class DeptAction extends BaseAction<Dept> {

	@Autowired
	private DeptService deptService;

	/**
	 * 部门分页
	 * 
	 * @return
	 */
	@Action("deptAction_list")
	public String list() throws Exception {
		// 创建分页的对象, 下标默认从0开始
		PageRequest pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
		// 返回一个分页的对象, 该对象是Spring提供的类
		Page<Dept> page2 = deptService.findPage(null, pageable);
		// 将page2中的数据封装到自己的page对象中
		super.parsePage(page, page2);
		page.setUrl("deptAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 查看部门详情
	 * 
	 * @return
	 */
	@Action("deptAction_toview")
	public String toview() throws Exception {
		model = deptService.get(model.getId());
		super.push(model);
		return "toview";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	@Action("deptAction_tocreate")
	public String tocreate() throws Exception {
		// 加载状态为1的部门列表
		Specification<Dept> spec = new Specification<Dept>() {
			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<Dept> deptList = deptService.find(spec);
		super.put("deptList", deptList);
		return "tocreate";
	}

	/**
	 * 新增部门
	 * 
	 * @return
	 */
	@Action("deptAction_insert")
	public String insert() throws Exception {
		model.setState(1);
		deptService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新页面
	 * 
	 * @return
	 */
	@Action("deptAction_toupdate")
	public String toupdate() throws Exception {
		// 加载状态为1的部门
		Specification<Dept> spec = new Specification<Dept>() {
			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<Dept> deptList = deptService.find(spec);

		// 查询要修改的原有部门信息
		Dept dept = deptService.get(model.getId());
		super.push(dept);

		deptList.remove(dept);

		super.put("deptList", deptList);
		return "toupdate";
	}

	/**
	 * 修改部门信息
	 * 
	 * @return
	 */
	@Action("deptAction_update")
	public String update() throws Exception {
		Dept dept = deptService.get(model.getId());
		dept.setParent(model.getParent());
		dept.setDeptName(model.getDeptName());
		deptService.saveOrUpdate(dept);
		return SUCCESS;
	}

	/**
	 * 删除部门, 支持批量删除
	 * 
	 * @return
	 */
	@Action("deptAction_delete")
	public String delete() throws Exception {
		deptService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	@Override
	public Dept setModel() {
		return new Dept();
	}

}
