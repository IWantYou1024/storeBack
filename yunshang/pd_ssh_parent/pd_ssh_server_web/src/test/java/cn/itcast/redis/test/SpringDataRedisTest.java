package cn.itcast.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-cache.xml")
public class SpringDataRedisTest {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void testSave() {
		redisTemplate.opsForValue().set("username", "jack");
		System.out.println(redisTemplate.opsForValue().get("username"));
	}

	@Test
	public void testDelete() {
		redisTemplate.delete("username");
		System.out.println(redisTemplate.opsForValue().get("username"));
	}

}
