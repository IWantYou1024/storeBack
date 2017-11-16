package cn.itcast.web.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.sysadmin.Module;
import cn.itcast.domain.sysadmin.Role;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.UserService;

public class AuthRealm extends AuthorizingRealm {
	private static Logger log = Logger.getLogger(AuthRealm.class);

	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		log.info("执行授权...");
		// 从shiro中抽取当前登录的用户信息
		User user = (User) pc.fromRealm(this.getName()).iterator().next();

		List<String> moduleList = new ArrayList<>();
		// 加载用户的角色信息
		Set<Role> roleSet = user.getRoles();

		// 遍历每个角色信息
		for (Role role : roleSet) {
			Set<Module> modules = role.getModules();
			for (Module module : modules) {
				moduleList.add(module.getName());
			}
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(moduleList);
		return info;
	}

	/**
	 * 认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("执行认证...");
		// 用户信息向下转型
		final UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 调用业务方法, 实现登录
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("userName").as(String.class), upToken.getUsername());
				return p1;
			}
		};
		List<User> userList = userService.find(spec);

		// 判断用户的用户名是否可用
		if (userList != null && userList.size() > 0) {
			User user = userList.get(0);
			return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		}
		return null;
	}

}