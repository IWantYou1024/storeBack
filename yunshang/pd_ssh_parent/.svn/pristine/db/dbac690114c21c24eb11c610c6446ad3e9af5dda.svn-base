package cn.itcast.cxf.test;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class WebServerTest {

	public static void main(String[] args) {
		// 添加JaxWs服务器发布工厂
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		// 设置服务器发布地址
		factory.setAddress("http://localhost:12345/user");
		// 设置服务的实现类对象
		factory.setServiceBean(new UserServiceImpl());
		// 发布服务
		factory.create();
	}

}
