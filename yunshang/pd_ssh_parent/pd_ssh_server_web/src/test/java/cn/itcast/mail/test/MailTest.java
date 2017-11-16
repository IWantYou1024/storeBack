package cn.itcast.mail.test;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.utils.Encrypt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-mail.xml")
public class MailTest {

	@Autowired
	private SimpleMailMessage mailMessage;

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void testMail() {
		mailMessage.setTo("1043006260@qq.com");
		mailMessage.setSubject("测试邮件");
		mailMessage.setText("测试spring与javaMail整合");

		mailSender.send(mailMessage);
	}

	@Test
	public void testMail2() {
		mailMessage.setTo("jack@it.com");
		mailMessage.setSubject("测试邮件");
		mailMessage.setText("测试spring与javaMail整合");

		mailSender.send(mailMessage);
	}

	/**
	 * 发送带有图片的邮件, 以嵌入HTML的方式
	 * 
	 * @throws MessagingException
	 */
	@Test
	public void testMail03() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom("root@it.com");
		messageHelper.setTo("jack@it.com");

		messageHelper.setSubject("测试邮件中嵌套图片");
		// true表示启动HTML格式的邮件
		messageHelper.setText("<html><head></head><body><h1>hello! spring image html mail</h1>"
				+ "<a href='http://localhost:8080/pd_ssh_server_web'>云商系统</a>"
				+ "<img alt='图裂了' src='cid:image'></body></html>", true);
		FileSystemResource img = new FileSystemResource(
				new File("D:/workspace_1.7/PD_SSH/pd_ssh_parent/pd_ssh_server_web/src/main/webapp/images/girl.jpg"));
		messageHelper.addInline("image", img); // 和cid一致
		mailSender.send(mimeMessage);
		System.out.println("邮件发送成功");
	}

	/**
	 * 测试发送带附件的邮件
	 * 
	 * @throws MessagingException
	 */
	@Test
	public void testMail04() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom("root@it.com");
		messageHelper.setTo("jack@it.com");

		messageHelper.setSubject("测试邮件中嵌套图片");
		// true表示启动HTML格式的邮件
//		messageHelper.setText("<html><head></head><body><h1>hello! spring image html mail</h1>"
//				+ "<a href='http://localhost:8080/pd_ssh_server_web'>云商系统</a>"
//				+ "<img alt='图裂了' src='cid:image'></body></html>", true);
		messageHelper.setText("aha");
		File file = new File("D:/workspace_1.7/PD_SSH/pd_ssh_parent/pd_ssh_server_web/src/main/webapp/images/girl.jpg");
		File file2 = new File("E:/wgb/bizhi/风景/逸夫楼操场.jpg");
		messageHelper.addAttachment("girl.jpg", file);
		mailSender.send(mimeMessage);
		System.out.println("邮件发送成功");
	}
	
	@Test
	public void test() {
		String md5 = Encrypt.md5("123456", "cgx");
		System.out.println(md5);
	}

}
