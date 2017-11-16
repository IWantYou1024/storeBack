package cn.itcast.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.client.dao.UserClientDao;
import cn.itcast.client.domain.UserClient;
import cn.itcast.client.service.UserClientService;

@Service("userClientService")
public class UserClientServiceImpl extends BaseServiceImpl<UserClient> implements UserClientService {

	private UserClientDao userClientDao;

	@Autowired
	public void init(UserClientDao userClientDao) {
		super.baseDao = userClientDao;
		this.userClientDao = userClientDao;
	}

	@Override
	public UserClient findByUserNameAndPassword(String userName, String password) {
		return userClientDao.findByUserNameAndPassword(userName, password);
	}

	@Override
	public UserClient findByEmail(String email) {
		return userClientDao.findByEmail(email);
	}
}
