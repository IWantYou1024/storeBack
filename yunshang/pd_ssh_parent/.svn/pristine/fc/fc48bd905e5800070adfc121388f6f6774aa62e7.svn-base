package cn.itcast.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cn.itcast.dao.sysadmin.UserDao;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.Encrypt;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserDao userDao;

	@Autowired
	public void init(UserDao userDao) {
		super.baseDao = userDao;
		this.userDao = userDao;
	}

	@Autowired
	private SimpleMailMessage mailMessage;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void saveOrUpdate(final User entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			entity.setState(1); // 1代表启用, 0代表停用
			// 保证用户与用户扩展信息的id值一致
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserinfo().setId(id);

			// 加入Shiro框架后补充代码 为新增用户添加默认密码
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));

			// 新增用户发送邮件
			// 可能需要一些时间, 开启线程进行发送
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						// MailUtil.sendMsg(entity.getUserinfo().getEmail(),
						// "新员工入职账户信息提醒",
						// entity.getUserinfo().getName() + "您好, 欢迎加入本集团, 您在");
						mailMessage.setTo(entity.getUserinfo().getEmail());
						mailMessage.setSubject("新员工入职须知");
						mailMessage.setText("欢迎加入本集团, " + entity.getUserinfo().getName() + ", 您在公司的账号: "
								+ entity.getUserName() + ", 初始密码: " + SysConstant.DEFAULT_PASS);
						mailSender.send(mailMessage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		userDao.save(entity);
	}

}
