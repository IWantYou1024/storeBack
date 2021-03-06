package cn.itcast.web.action.home;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import cn.itcast.domain.home.CustomerMenu;
import cn.itcast.domain.sysadmin.Module;
import cn.itcast.domain.sysadmin.Role;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.CustomerMenuService;
import cn.itcast.service.ModuleService;
import cn.itcast.service.StatChartService;
import cn.itcast.service.UserService;
import cn.itcast.utils.FastJsonUtils;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/home")
@Results({ @Result(name = "fastMenuClear", location = "/WEB-INF/pages/home/left.jsp"),
		@Result(name = "customerMenuAdd", location = "/WEB-INF/pages/home/menu/customerMenu.jsp"),
		@Result(name = "query", type = "redirect", location = "memoAction_query"), })
public class MenuAction extends BaseAction<CustomerMenu> {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerMenuService customerMenuService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private StatChartService statChartService;

	/**
	 * ajax显示快捷菜单
	 */
	@Action("menuAction_fastMenu")
	public String fastMenu() throws Exception {

		User user = super.getCurUser();
		if (user != null) {
			String data = redisTemplate.opsForValue().get(user.getId());
			FastJsonUtils.writeJson(ServletActionContext.getResponse(), data);
		}

		return NONE;
	}

	/**
	 * 清空快捷菜单
	 */
	@Action("menuAction_fastMenuClear")
	public String fastMenuClear() throws Exception {
		User user = super.getCurUser();
		if (user != null) {
			redisTemplate.delete(user.getId());
		}
		return "fastMenuClear";
	}

	/**
	 * 跳转自定义菜单添加页面
	 */
	@Action("menuAction_customerMenuAdd")
	public String customerMenuAdd() throws Exception {
		super.push(super.getCurUser());
		return "customerMenuAdd";
	}

	/**
	 * ajax加载用户的可用菜单
	 */
	@Action("menuAction_genzTreeNodes")
	public String genzTreeNodes() throws Exception {

		// 获取当前用户的自定义菜单
		CustomerMenu customerMenu = customerMenuService.get(model.getId());
		if (customerMenu == null) {
			customerMenu = new CustomerMenu();
		}

		Set<Module> moduleSet = new HashSet<>();
		if (UtilFuns.isNotEmpty(customerMenu.getModuleIds())) {
			String[] mids = customerMenu.getModuleIds().split(",");
			for (String mid : mids) {
				Module module = moduleService.get(mid);
				moduleSet.add(module);
			}
		}

		// 加载当前用户可用菜单
		User user = userService.get(super.getCurUser().getId());
		Set<Module> moduleList = new HashSet<>();
		for (Role role : user.getRoles()) {
			Set<Module> modules = role.getModules();
			moduleList.addAll(modules);
		}

		// [{"id":"","pId":"","nocheck":true,"name":""}]

		// 将结果转化为zTree要求的json字符串
		StringBuilder sb = new StringBuilder();
		int size = moduleList.size();
		sb.append("[");
		for (Module module : moduleList) {
			size--;
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			if (UtilFuns.isEmpty(module.getCurl()) || !module.getLayerNum().equals("2")) {
				// 将数据库中链接为空以及非2级模块的模块设置为没有选择框
				sb.append("\",\"nocheck\":true,\"name\":\"");
			} else {
				// 有链接的2级模块直接拼接
				sb.append("\",\"name\":\"");
			}
			sb.append(module.getName());
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
		// 处理json的响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		response.getWriter().write(sb.toString());

		return NONE;
	}

	/**
	 * 更新自定义菜单
	 */
	@Action("menuAction_updateMenu")
	public String updateMenu() throws Exception {
		customerMenuService.saveOrUpdate(model);
		return "query";
	}

	/**
	 * ajax获取自定义菜单列表
	 */
	@Action("menuAction_customerMenu")
	public String customerMenu() throws Exception {
		// 获取和用户绑定的自定义菜单
		CustomerMenu customerMenu = customerMenuService.get(super.getCurUser().getId());
		if (customerMenu != null && UtilFuns.isNotEmpty(customerMenu.getModuleIds())) {
			// 拼接返回用于in查询的串  'x','y'
			String joinInStr = UtilFuns.joinInStr(customerMenu.getModuleIds().split(","));
			// 查询用户自定义的模块
			String sql = "select t.cpermission, t.curl from module_p t where t.layer_num = '2' and t.module_id in ("
					+ joinInStr + ")";
			List<Map<String, Object>> data = statChartService.getData(sql);
			FastJsonUtils.writeJson(ServletActionContext.getResponse(), data);
		}
		return NONE;
	}

	@Override
	public CustomerMenu setModel() {
		return new CustomerMenu();
	}

}
