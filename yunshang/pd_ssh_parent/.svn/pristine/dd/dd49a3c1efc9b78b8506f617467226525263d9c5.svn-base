package cn.itcast.web.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import cn.itcast.domain.home.Memo;
import cn.itcast.service.MemoService;

public class MemoAttentionJob {
	
	@Autowired
	private MemoService memoService;
	@Autowired
	private SimpleMailMessage mailMessage;
	@Autowired
	private JavaMailSender mailSender;

	public void execute() throws Exception {
		String deadLine = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<Memo> list = memoService.findByDeadLine(deadLine);
		if (list.size() > 0 && list != null) {
			for (final Memo memo : list) {
				Thread.sleep(3000); // 让当前线程休眠3s
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							mailMessage.setTo(memo.getUser().getUserinfo().getEmail());
							mailMessage.setSubject("提醒: 备忘录最后期限到了");
							mailMessage.setText("您好, 您有一个备忘录的截止日期于"
									+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(memo.getDeadLine())
									+ "到期");
							mailSender.send(mailMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				thread.start();
			}
		} else {
			System.out.println("今天没有备忘录截止日期到期");
		}

	}

}
