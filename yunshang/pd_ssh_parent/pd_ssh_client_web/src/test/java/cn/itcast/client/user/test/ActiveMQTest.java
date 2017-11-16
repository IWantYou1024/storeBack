package cn.itcast.client.user.test;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.client.domain.UserClient;
import cn.itcast.client.service.UserClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ActiveMQTest {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	@Autowired
	private UserClientService userClientService;

	@Test
	public void test01() {
		jmsTemplate.send("client_sms", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("telephone", "123456789");
				return message;
			}
		});
	}

	@Test
	public void test02() {
		UserClient userClient = userClientService.get("1");
		System.out.println(userClient.getUserName());
	}

}
