package cn.itcast.service.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.service.StatChartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StatChartServiceTest {

	@Autowired
	private StatChartService statChartService;

	@Test
	public void test01() {
		// List<Map<String, Object>> list = statChartService.genProductData();
		// System.out.println(list);
	}

	@Test
	public void test02() {
		// List<Map<String, Object>> list = statChartService.genOnlineInfo();
		// System.out.println(list);
	}

}
