package cn.itcast.client.action;

import java.io.OutputStream;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import cn.itcast.client.domain.UserClient;
import cn.itcast.client.service.UserClientService;
import cn.itcast.utils.ImageUtil;

@Namespace("/")
public class UserAction extends BaseAction<UserClient> {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private UserClientService userClientService;

	private String vercode; // 随机验证码
	private String phoneVercode; // 手机验证码

	/**
	 * 生成图片验证码
	 */
	@Action("userAction_genActiveCode")
	public String genActiveCode() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = response.getOutputStream();

		String str = ImageUtil.getRundomStr();
		session.put("vcode", str);

		ImageUtil.getImage(str, out);
		return NONE;
	}

	/**
	 * 手机验证码发送到消息系统
	 */
	@Action("userAction_sendVerCode")
	public String sendVerCode() throws Exception {
		System.out.println(model.getTelephone());

		jmsTemplate.send("client_sms", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("telephone", model.getTelephone());
				return message;
			}
		});
		return NONE;
	}

	/**
	 * 用户注册
	 */
	@Action("userAction_register")
	public String register() throws Exception {
		String result = "-1";
		// --------------------验证随机码
		String vcode = (String) session.get("vcode");
		if (!vcode.equals(vercode)) {
			result = "0";
			return NONE;
		}
		// --------------------验证手机验证码
		String telVercode = redisTemplate.opsForValue().get(model.getTelephone());
		if (!telVercode.equals(phoneVercode)) {
			result = "1";
			return NONE;
		}

		// 添加用户注册的信息
		userClientService.saveOrUpdate(model);
		// 发送激活邮件
		jmsTemplate.send("client_sms", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("mail", model.getEmail());
				return message;
			}
		});

		result = "2"; // 成功
		// 清除验证码
		session.remove("vcode");
		redisTemplate.delete(model.getTelephone());

		// 返回响应的结果
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text.xml;charset=utf-8");
		response.getWriter().print(result);

		return NONE;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getPhoneVercode() {
		return phoneVercode;
	}

	public void setPhoneVercode(String phoneVercode) {
		this.phoneVercode = phoneVercode;
	}

	@Override
	public UserClient setModel() {
		return new UserClient();
	}

}
