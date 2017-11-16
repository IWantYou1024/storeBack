package cn.itcast.web.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.sysadmin.Module;
import cn.itcast.domain.sysadmin.Role;
import cn.itcast.service.ModuleService;
import cn.itcast.service.RoleService;
import cn.itcast.web.action.BaseAction;

@Namespace("/sysadmin")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/sysadmin/role/jRoleList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/sysadmin/role/jRoleView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/role/jRoleCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/role/jRoleUpdate.jsp"),
		@Result(name = "tomodule", location = "/WEB-INF/pages/sysadmin/role/jRoleModule.jsp"),
		@Result(name = "success", type = "redirect", location = "roleAction_list") })
public class RoleAction extends BaseAction<Role> {

	private String moduleIds;

	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 分页查询
	 * 
	 */
	@Action("roleAction_list")
	public String list() throws Exception {
		Page<Role> page2 = roleService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("roleAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 查看角色详情
	 * 
	 */
	@Action("roleAction_toview")
	public String toview() throws Exception {
		Role role = roleService.get(model.getId());
		super.push(role);
		return "toview";
	}

	/**
	 * 跳转新增页面
	 * 
	 */
	@Action("roleAction_tocreate")
	public String tocreate() throws Exception {
		return "tocreate";
	}

	/**
	 * 新增角色
	 * 
	 */
	@Action("roleAction_insert")
	public String insert() throws Exception {
		roleService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转修改页面
	 * 
	 */
	@Action("roleAction_toupdate")
	public String toupdate() throws Exception {
		Role role = roleService.get(model.getId());
		super.push(role);
		return "toupdate";
	}

	/**
	 * 修改角色信息
	 * 
	 */
	@Action("roleAction_update")
	public String update() throws Exception {
		Role role = roleService.get(model.getId());
		role.setName(model.getName());
		role.setRemark(model.getRemark());

		roleService.saveOrUpdate(role);
		return SUCCESS;
	}

	/**
	 * 删除角色
	 * 
	 */
	@Action("roleAction_delete")
	public String delete() throws Exception {
		roleService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	/**
	 * 跳转角色模块显示
	 * 
	 */
	@Action("roleAction_tomodule")
	public String tomodule() throws Exception {
		Role role = roleService.get(model.getId());
		super.push(role);
		return "tomodule";
	}

	/**
	 * 实现Ajax请求的处理 [{"id":"","pId":"","name":"","checked":""}]
	 * 
	 */
	@Action("roleAction_genzTreeNodes")
	public String genzTreeNodes() throws Exception {
		// 获得角色对象
		Role role = roleService.get(model.getId());
		// 加载当前role的module集合
		Set<Module> moduleSet = role.getModules();

		// 加载所有可用的模块列表
		Specification<Module> spec = new Specification<Module>() {
			public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<Module> moduleList = moduleService.find(spec);

		// 将结果转化为zTree要求的json字符串
		StringBuilder sb = new StringBuilder();
		int size = moduleList.size();
		sb.append("[");
		for (Module module : moduleList) {
			size--;
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			sb.append("\",\"name\":\"").append(module.getName());
			sb.append("\",\"checked\":\"");
			if (moduleSet.contains(module)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
			sb.append("\"}");
			if (size > 0) {
				sb.append(",");
			}
		}
		sb.append("]");

		System.out.println(sb.toString());

		// 处理json的响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		response.getWriter().write(sb.toString());

		return NONE;
	}

	/**
	 * 实现模块分配
	 * 
	 */
	@Action("roleAction_module")
	public String module() throws Exception {
		Role role = roleService.get(model.getId());

		Set<Module> moduleSet = new HashSet<>();
		String[] ids = moduleIds.split(",");
		for (String id : ids) {
			moduleSet.add(moduleService.get(id));
		}

		role.setModules(moduleSet);

		roleService.saveOrUpdate(role);
		return SUCCESS;
	}

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	@Override
	public Role setModel() {
		return new Role();
	}

}
