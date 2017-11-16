package com.itheima.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtils {

	public static void send(String sendMail,String msg) {
		try {
			//1 获得session
			Properties props = new Properties();
			props.setProperty("mail.host", "127.0.0.1");
			props.setProperty("mail.smtp.auth", "true");
			Authenticator authenticator = new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("admin", "1234");
				}
			};
			Session session = Session.getDefaultInstance(props, authenticator);
			//2 message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@itheima.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(sendMail));
			message.setSubject("用户激活");
			message.setContent(msg, "text/html;charset=UTF-8");
			
			//3发送
			Transport.send(message);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public static void main(String[] args) {
		send("jack@itheima.com", "嫐 嬲  ");
	}
}
