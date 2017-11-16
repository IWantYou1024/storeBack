package cn.itcast.cxf.test;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

public class ClientTest {

	@Test
	public void testClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:12345/user?wsdl");
		factory.setServiceClass(UserService.class);
		UserService service = (UserService) factory.create();
		User user = new User();
		user.setUsername("宋江");
		List<Car> list = service.findCarsByUser(user);
		System.out.println(list);
	}

}
