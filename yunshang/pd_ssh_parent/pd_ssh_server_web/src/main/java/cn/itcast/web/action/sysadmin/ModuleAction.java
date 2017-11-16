package cn.itcast.web.action.sysadmin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cn.itcast.domain.sysadmin.Module;
import cn.itcast.service.ModuleService;
import cn.itcast.web.action.BaseAction;

@Namespace("/sysadmin")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/sysadmin/module/jModuleList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/sysadmin/module/jModuleView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/module/jModuleCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/module/jModuleUpdate.jsp"),
		@Result(name = "success", type = "redirect", location = "moduleAction_list") })
public class ModuleAction extends BaseAction<Module> {

	@Autowired
	private ModuleService moduleService;

	/**
	 * 模块分页
	 * 
	 * @return
	 */
	@Action("moduleAction_list")
	public String list() throws Exception {
		Page<Module> page2 = moduleService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("moduleAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 查看模块详情
	 * 
	 * @return
	 */
	@Action("moduleAction_toview")
	public String toview() throws Exception {
		Module module = moduleService.get(model.getId());
		super.push(module);
		return "toview";
	}

	/**
	 * 跳转新增页面
	 * 
	 * @return
	 */
	@Action("moduleAction_tocreate")
	public String tocreate() throws Exception {
		return "tocreate";
	}

	/**
	 * 新增模块
	 * 
	 * @return
	 */
	@Action("moduleAction_insert")
	public String insert() throws Exception {
		moduleService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新页面
	 * 
	 * @return
	 */
	@Action("moduleAction_toupdate")
	public String toupdate() throws Exception {
		Module module = moduleService.get(model.getId());
		super.push(module);
		return "toupdate";
	}

	/**
	 * 更新模块
	 * 
	 * @return
	 */
	@Action("moduleAction_update")
	public String update() throws Exception {
		Module module = moduleService.get(model.getId());
		module.setName(model.getName());
		module.setLayerNum(model.getLayerNum());
		module.setCpermission(model.getCpermission());
		module.setCurl(model.getCurl());
		module.setCtype(model.getCtype());
		module.setState(model.getState());
		module.setBelong(model.getBelong());
		module.setCwhich(model.getCwhich());
		module.setRemark(model.getRemark());
		module.setOrderNo(model.getOrderNo());
		moduleService.saveOrUpdate(module);
		return SUCCESS;
	}

	/**
	 * 删除模块
	 * 
	 * @return
	 */
	@Action("moduleAction_delete")
	public String delete() throws Exception {
		moduleService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	@Override
	public Module setModel() {
		return new Module();
	}

}
