package com.redis.test;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

/**
 * Redis test
 * 
 * @author yyf
 */
public class test {
	/**
	 * 测试连接Redis
	 */
	@Test
	public void test1() {
		Jedis jedis = new Jedis("localhost"); // 连接Redis服务器
		jedis.set("yyf", "Hello,world!"); // 缓存到Redis服务器
		String yyf = jedis.get("yyf"); // 取出Redis缓存的value
		System.out.println("从Redis缓存中取到 yyf:" + yyf); // 打印
	}

	/**
	 * 测试连接Redis
	 * springframework.data.redis 
	 * @throws InterruptedException
	 */
	@Test
	public void test2() throws InterruptedException {
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"classpath:spring-mvc.xml");
		RedisService redisService = (RedisService) app.getBean("redisService");
		String ping = redisService.ping();// 测试是否连接成功,连接成功输出PONG
		System.out.println("连接成功 ,返回值:" + ping);
		long rSize = redisService.dbSize();// 查看redis存储数据
		System.out.println("Redis当前存储的数据量:" + rSize);
		redisService.set("key1", "value1");// 存储(默认存活30min)
		System.out.println("存储 键值为 key1,value1");
		String key1 = redisService.get("key1");// 取值
		System.out.println("从Redis缓存中取到 key1:" + key1);
		System.out.println("存储 键值为 key2,value2 存在时间为2s");
		redisService.set("key2", "value2", 2);// 设值,并且设置数据的存活时间(s)
		String key2 = redisService.get("key2");
		System.out.println("从Redis缓存中取到 key2:" + key2);
		Thread.sleep(3000);// 通过睡眠来让key2的时间
		String key2Then = redisService.get("key2");
		System.out.println("3s后从Redis缓存中取到 key2:" + key2Then);// 输出null
		boolean bool = redisService.exists("key2");
		System.out.println("key2的状态" + bool);
		Set<String> keys = redisService.keys("*");// 这里查看所有的keys
		System.out.println("Rdeis 中所有的key:" + keys);
		redisService.flushDB(); // qing
	}
}
