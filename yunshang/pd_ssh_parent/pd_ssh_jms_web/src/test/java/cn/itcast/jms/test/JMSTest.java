package cn.itcast.jms.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

public class JMSTest {

	/**
	 * 向消息中间件写入消息
	 */
	@Test
	public void test01() {
		try {
			// 建立连接工厂
			ConnectionFactory factory = new ActiveMQConnectionFactory();
			// 创建连接
			Connection connection = factory.createConnection();
			connection.start();// 开启连接
			// 建立会话, 第一个参数 代表是否使用事务, 若启用需要用session.commit()提交
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 创建Queue对象
			Queue queue = session.createQueue("helloworld");

			// 创建一个生产者
			MessageProducer producer = session.createProducer(queue);

			// 消息发送
			producer.send(session.createTextMessage("Hello, I am your father"));

			// 提交
			session.commit();
			session.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试JMS消息的消费者
	 */
	@Test
	public void testConsumer() {
		try {
			// 建立连接工厂
			ConnectionFactory factory = new ActiveMQConnectionFactory();
			// 创建连接
			Connection connection = factory.createConnection();
			connection.start();// 开启连接
			// 建立会话, 第一个参数 代表是否使用事务, 若启用需要用session.commit()提交
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建对象
			Queue queue = session.createQueue("helloworld");

			// 创建一个消费者
			MessageConsumer consumer = session.createConsumer(queue);
			// 消费
			while (true) {
				TextMessage message = (TextMessage) consumer.receive(1000);
				if (message != null) {
					System.out.println(message.getText());
				} else {
					break;
				}
			}
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用监听器实现消费
	 */
	@Test
	public void testConsumer02() {
		try {

			ConnectionFactory factory = new ActiveMQConnectionFactory();
			Connection connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue queue = session.createQueue("helloworld");
			MessageConsumer consumer = session.createConsumer(queue);

			consumer.setMessageListener(new MessageListener() {
				// 每次接收消息, 自动调用onMessage方法
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println(msg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
