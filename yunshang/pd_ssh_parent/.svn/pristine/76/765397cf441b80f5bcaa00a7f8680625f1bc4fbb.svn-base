package cn.itcast.jms.consumer;

import java.util.concurrent.TimeUnit;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.itcast.utils.RandomCode;
import cn.itcast.utils.SmsUtils;

@Component("queueConsumer")
public class QueueConsumer implements MessageListener {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void onMessage(Message message) {
		// try {
		// System.out.println("消费者QueueConsumer" + ((TextMessage)
		// message).getText());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			MapMessage msg = (MapMessage) message;
			String telephone = msg.getString("telephone");
			String mail = msg.getString("mail");
			// 如果传递的消息中有手机号, 发短信
			if (telephone != null) {
				String mobile_code = RandomCode.genCode();
				System.out.println("手机号: " + telephone + "验证码: " + mobile_code);
				// 发送短信
				SmsUtils.sendSms(telephone, mobile_code);

				if (true) {
					// 发送成功则将验证码保存到redis中, 用户看到的是1分钟, 实际存储时间为3分钟
					redisTemplate.opsForValue().set(telephone, mobile_code, 3, TimeUnit.MINUTES);
				}
			} else if (mail != null) {
				// 发送邮箱验证码
				String mail_code = RandomCode.genCode();

				// 将用户的mail_code保存至redis中, 三天
				redisTemplate.opsForValue().set(mail, mail_code, 3, TimeUnit.DAYS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
