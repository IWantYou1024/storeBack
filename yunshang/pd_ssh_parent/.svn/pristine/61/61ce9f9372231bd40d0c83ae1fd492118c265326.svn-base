package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.sysadmin.LoginStatDao;
import cn.itcast.domain.sysadmin.LoginStat;
import cn.itcast.service.LoginStatService;

@Service("loginStatService")
public class LoginStatServiceImpl extends BaseServiceImpl<LoginStat> implements LoginStatService {

	private LoginStatDao loginStatDao;

	@Autowired
	public void init(LoginStatDao loginStatDao) {
		super.baseDao = loginStatDao;
		this.loginStatDao = loginStatDao;
	}

}
