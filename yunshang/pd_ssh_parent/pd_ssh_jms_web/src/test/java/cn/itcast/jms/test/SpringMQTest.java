package cn.itcast.jms.test;

import javax.jms.JMSException;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mq.xml")
public class SpringMQTest {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	@Test
	public void testMq() {
		// 发送队列消息
		jmsQueueTemplate.send("spring_queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("你好, 队列消息");
			}
		});
		// 发送Topic消息
		jmsTopicTemplate.send("spring_topic", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("你好, 订阅消息");
			}
		});
	}

}
