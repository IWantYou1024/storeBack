package cn.itcast.jms.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component("topicConsumer")
public class TopicConsumer implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("消费者测试TopicConsumer" + ((TextMessage) message).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
