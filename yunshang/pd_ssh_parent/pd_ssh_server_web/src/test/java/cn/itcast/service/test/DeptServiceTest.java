package cn.itcast.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.domain.sysadmin.Dept;
import cn.itcast.service.DeptService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DeptServiceTest {

	@Autowired
	private DeptService deptService;

	@Test
	public void findTest() {
		List<Dept> list = deptService.find(null);
		for (Dept dept : list) {
			System.out.println(dept);
		}

	}

}
