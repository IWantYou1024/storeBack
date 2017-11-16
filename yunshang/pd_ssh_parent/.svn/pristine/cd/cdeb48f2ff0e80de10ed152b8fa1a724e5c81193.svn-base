package cn.itcast.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-redis.xml")
public class SpringRedisTest {

	private Jedis jedis;

	@Autowired
	public void init(JedisPool jedisPool) {
		jedis = jedisPool.getResource();
	}

	@Test
	public void test01() {
		jedis.set("username", "rose");
		System.out.println(jedis.get("username"));
	}

	@Test
	public void test02() {
		Long del = jedis.del("username");
		System.out.println(del);
	}

	@Test
	public void test3() {
		String all = jedis.flushAll();
		System.out.println(all);
	}

}
