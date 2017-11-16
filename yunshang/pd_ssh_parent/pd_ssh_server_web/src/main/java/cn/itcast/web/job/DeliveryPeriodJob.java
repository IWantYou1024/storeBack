package cn.itcast.web.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import cn.itcast.domain.cargo.Contract;
import cn.itcast.service.ContractService;

public class DeliveryPeriodJob {

	@Autowired
	private ContractService contractService;
	@Autowired
	private SimpleMailMessage mailMessage;
	@Autowired
	private JavaMailSender mailSender;

	public void execute() throws Exception {
		String delivery = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<Contract> list = contractService.findByDelivery(delivery);
		if (list.size() > 0 && list != null) {
			for (final Contract contract : list) {
				Thread.sleep(3000); // 让当前线程休眠3s
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							mailMessage.setTo("jack@it.com");
							mailMessage.setSubject("提醒: 交期到了");
							mailMessage.setText("您好, 您有一个购销合同交货日期于"
									+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(contract.getDeliveryPeriod())
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
			System.out.println("今天没有购销合同交期到期");
		}

	}

}
