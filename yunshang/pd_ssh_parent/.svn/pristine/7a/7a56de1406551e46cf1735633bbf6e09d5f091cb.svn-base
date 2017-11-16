package cn.itcast.cxf.test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Override
	public String sayHi(String name) {
		System.out.println("hi, " + name);
		return "hi, " + name;
	}

	@Override
	public List<Car> findCarsByUser(User user) {
		if ("宋江".equals(user.getUsername())) {
			List<Car> cars = new ArrayList<>();
			Car car1 = new Car();
			car1.setId(1);
			car1.setCarName("BMW");
			car1.setPrice(111111111111d);
			cars.add(car1);

			Car car2 = new Car();
			car2.setId(2);
			car2.setCarName("别摸我");
			car2.setPrice(2222222222222d);
			cars.add(car2);
			return cars;
		}
		return null;
	}

}
