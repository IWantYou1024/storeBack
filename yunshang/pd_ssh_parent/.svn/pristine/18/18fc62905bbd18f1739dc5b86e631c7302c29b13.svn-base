package cn.itcast.web.action;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.domain.sysadmin.LoginStat;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.LoginStatService;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;

/**
 * @Description: 登录和退出类
 * @Author: 传智播客 java学院 传智.宋江
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月31日
 * 
 *              继承BaseAction的作用 1.可以与struts2的API解藕合 2.还可以在BaseAction中提供公有的通用方法
 */
@Namespace("/")
@Results({ @Result(name = "login", location = "/WEB-INF/pages/sysadmin/login/login.jsp"),
		@Result(name = "success", location = "/WEB-INF/pages/home/fmain.jsp"),
		@Result(name = "logout", location = "/index.jsp") })
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@Autowired
	private LoginStatService loginStatService;

	/**
	 * Shiro登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("loginAction_login")
	public String login() throws Exception {
		if (UtilFuns.isEmpty(username)) {
			return LOGIN;
		}
		try {
			// 得到Subject对象
			Subject subject = SecurityUtils.getSubject();
			// 封装用户数据
			UsernamePasswordToken uptoken = new UsernamePasswordToken(username, password);
			// 实现登录操作
			subject.login(uptoken); // 立即调用AuthRealm域中的认证方法
			// 登录成功后, 从Shiro中取出用户对象
			User user = (User) subject.getPrincipal();

			// 将用户信息放入session域中
			session.put(SysConstant.CURRENT_USER_INFO, user);

			// 添加登录日志
			LoginStat loginStat = new LoginStat();
			loginStat.setUserName(user.getUserName());
			loginStat.setIp_Address(ServletActionContext.getRequest().getRemoteAddr());
			loginStat.setLoginTime(new Date());
			loginStatService.saveOrUpdate(loginStat);
		} catch (Exception e) {
			e.printStackTrace();
			request.put("errorInfo", "对不起, 用户名或密码错误, 登录失败");
			return LOGIN;
		}
		return SUCCESS;
	}

	/**
	 * 退出登录
	 */
	@Action("loginAction_logout")
	public String logout() {
		session.remove(SysConstant.CURRENT_USER_INFO); // 删除session
		SecurityUtils.getSubject().logout(); // 登出
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
