package cn.itcast.web.action.sysadmin;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.sysadmin.Dept;
import cn.itcast.domain.sysadmin.LoginStat;
import cn.itcast.domain.sysadmin.Role;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.domain.sysadmin.Userinfo;
import cn.itcast.service.DeptService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.Encrypt;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;
import cn.itcast.web.shiro.CustomCredentialsMatcher;

@Namespace("/sysadmin")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/sysadmin/user/jUserList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/sysadmin/user/jUserView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/user/jUserCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/user/jUserUpdate.jsp"),
		@Result(name = "torole", location = "/WEB-INF/pages/sysadmin/user/jUserRole.jsp"),
		@Result(name = "success", type = "redirect", location = "userAction_list"),
		@Result(name = "saveselfInfo", type = "redirect", location = "userAction_toselfInfo"),
		@Result(name="userInfoUpdate",location="/WEB-INF/pages/sysadmin/user/jSelfInfoUpdate.jsp"),
		@Result(name="userInfo",location="/WEB-INF/pages/sysadmin/user/jSelfList.jsp"),
		@Result(name="loginpwdpdate",location="/WEB-INF/pages/sysadmin/user/jPasswordUpdate.jsp"),
		@Result(name="saveAlterPwd",location="/WEB-INF/pages/sysadmin/user/saveSucess.jsp")})
public class UserAction extends BaseAction<User> {

	// 角色
	private String[] roleIds;

	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleService roleService;
	
	private String[] newPwds;

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	@Action("userAction_list")
	public String list() throws Exception {
		PageRequest pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
		Page<User> page2 = userService.findPage(null, pageable);
		super.parsePage(page, page2);
		page.setUrl("userAction_list");
		push(page);
		return "list";
	}

	/**
	 * 查看用户详情
	 * 
	 * @return
	 */
	@Action("userAction_toview")
	public String toview() throws Exception {
		model = userService.get(model.getId());
		super.push(model);
		return "toview";
	}

	/**
	 * 跳转用户新增页面
	 * 
	 * @return
	 */
	@Action("userAction_tocreate")
	public String tocreate() throws Exception {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<User> userList = userService.find(spec);
		super.put("userList", userList);

		Specification<Dept> spec2 = new Specification<Dept>() {
			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<Dept> deptList = deptService.find(spec2);
		super.put("deptList", deptList);

		return "tocreate";
	}

	/**
	 * 新增用户
	 * 
	 * @return
	 */
	@Action("userAction_insert")
	public String insert() throws Exception {
		userService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转用户修改页面
	 * 
	 * @return
	 */
	@Action("userAction_toupdate")
	public String toupdate() throws Exception {
		User user = userService.get(model.getId());
		super.push(user);

		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<User> userList = userService.find(spec);
		userList.remove(user);
		super.put("userList", userList);

		Specification<Dept> spec2 = new Specification<Dept>() {
			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		List<Dept> deptList = deptService.find(spec2);
		super.put("deptList", deptList);

		return "toupdate";
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	@Action("userAction_update")
	public String update() throws Exception {
		User user = userService.get(model.getId());
		user.setDept(model.getDept());
		user.getUserinfo().setName(model.getUserinfo().getName());
		user.setState(model.getState());
		userService.saveOrUpdate(user);
		return SUCCESS;
	}

	/**
	 * 用户删除
	 * 
	 * @return
	 */
	@Action("userAction_delete")
	public String delete() throws Exception {
		userService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	/**
	 * 跳转用户角色信息
	 * 
	 * @return
	 */
	@Action("userAction_torole")
	public String torole() throws Exception {
		// 加载当前用户对象
		User user = userService.get(model.getId());
		super.push(user);

		// 加载当前用户的角色列表
		StringBuilder sb = new StringBuilder();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		super.put("roleStr", sb.toString());

		// 加载所有角色列表
		List<Role> roleList = roleService.find(null);
		super.put("roleList", roleList);

		return "torole";
	}

	/**
	 * 修改用户角色
	 * 
	 * @return
	 */
	@Action("userAction_role")
	public String role() throws Exception {
		// 得到当前用户对象
		User user = userService.get(model.getId());

		// 将用户选择的角色加载
		Set<Role> roleSet = new HashSet<>();
		for (String id : roleIds) {
			Role role = roleService.get(id);
			roleSet.add(role);
		}

		// 设置关系
		user.setRoles(roleSet);

		// 更新用户的角色列表
		userService.saveOrUpdate(user);
		return SUCCESS;
	}
	
	/*系统首页个人信息列表*/
	@Action(value="userAction_toselfInfo")
	public String toselfInfo(){
		User sessionUser = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//获取当前用户
		User user = userService.get(sessionUser.getId());
		super.push(user);
		
		return "userInfo";
	}
	/*保存信息修改*/
	@Action(value="userAction_saveselfInfo")
	public String saveselfInfo(){
		User sessionUser = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//获取当前用户
		User user = userService.get(sessionUser.getId());
		System.out.println(model.toString()+"model信息资");
		user.getUserinfo().setName(model.getUserinfo().getName());
		user.getUserinfo().setGender(model.getUserinfo().getGender());
		user.getUserinfo().setBirthday(model.getUserinfo().getBirthday());
		user.getUserinfo().setTelephone(model.getUserinfo().getTelephone());
		user.getUserinfo().setEmail(model.getUserinfo().getEmail());
		user.getUserinfo().setRemark(model.getUserinfo().getRemark());
		userService.saveOrUpdate(user);
		return "saveselfInfo";	
	}
	/*个人信息修改*/
	@Action(value="userAction_toselfupdate")
	public String toselfupdate(){
		User sessionUser = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//获取当前用户
		User user = userService.get(sessionUser.getId());
		super.push(user);
		return "userInfoUpdate";
	}
	
	@Action(value="userAction_loginpwdpdate")
	public String loginpwdpdate(){
		User sessionUser = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//获取当前用户
		User user = userService.get(sessionUser.getId());
		super.push(user);
		return "loginpwdpdate";
	}
	
	@Action(value="userAction_saveAlterPwd")
	public String saveAlterPwd(){
		User sessionUser = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//获取当前用户
		User user = userService.get(sessionUser.getId());
		super.push(user);
		String oldPassword = user.getPassword();
		System.out.println(oldPassword);
		String inputold = Encrypt.md5(model.getPassword(), user.getUserName());
		System.out.println(model.getId());
		System.out.println("inputold"+inputold);
		try {
			if(UtilFuns.isEmpty(oldPassword)){
				super.put("emptyerror1", "密码不能为空");
				return "loginpwdpdate";
			}
			if(oldPassword.equals(inputold)){
				if(UtilFuns.isEmpty(newPwds[0])||UtilFuns.isEmpty(newPwds[1])){
					super.put("emptyerror2", "密码不能为空");
					return "loginpwdpdate";
				}
				if(newPwds[0].equals(newPwds[1])){
					String Newmd5 = Encrypt.md5(newPwds[0],user.getUserName());
					user.setPassword(Newmd5);
				}else{
					super.put("newPwderror", "确认密码和新密码不一致");
					return "loginpwdpdate";
				}
			}else{
				super.put("olderror","输入的原密码不正确");
				return "loginpwdpdate";
			}
		} catch (Exception e) {
			System.out.println("出现异常");
			return "loginpwdpdate";
		}
		
		System.out.println("save开始");
		userService.saveOrUpdate(user);
		System.out.println("save结束");
		return "saveAlterPwd";
	}
	
	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public User setModel() {
		return new User();
	}

	public String[] getNewPwds() {
		return newPwds;
	}

	public void setNewPwds(String[] newPwds) {
		this.newPwds = newPwds;
	}
	 
	
}
