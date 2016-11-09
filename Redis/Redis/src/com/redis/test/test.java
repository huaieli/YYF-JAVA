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
	 * ��������Redis
	 */
	@Test
	public void test1() {
		Jedis jedis = new Jedis("localhost"); // ����Redis������
		jedis.set("yyf", "Hello,world!"); // ���浽Redis������
		String yyf = jedis.get("yyf"); // ȡ��Redis�����value
		System.out.println("��Redis������ȡ�� yyf:" + yyf); // ��ӡ
	}

	/**
	 * ��������Redis
	 * springframework.data.redis 
	 * @throws InterruptedException
	 */
	@Test
	public void test2() throws InterruptedException {
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"classpath:spring-mvc.xml");
		RedisService redisService = (RedisService) app.getBean("redisService");
		String ping = redisService.ping();// �����Ƿ����ӳɹ�,���ӳɹ����PONG
		System.out.println("���ӳɹ� ,����ֵ:" + ping);
		long rSize = redisService.dbSize();// �鿴redis�洢����
		System.out.println("Redis��ǰ�洢��������:" + rSize);
		redisService.set("key1", "value1");// �洢(Ĭ�ϴ��30min)
		System.out.println("�洢 ��ֵΪ key1,value1");
		String key1 = redisService.get("key1");// ȡֵ
		System.out.println("��Redis������ȡ�� key1:" + key1);
		System.out.println("�洢 ��ֵΪ key2,value2 ����ʱ��Ϊ2s");
		redisService.set("key2", "value2", 2);// ��ֵ,�����������ݵĴ��ʱ��(s)
		String key2 = redisService.get("key2");
		System.out.println("��Redis������ȡ�� key2:" + key2);
		Thread.sleep(3000);// ͨ��˯������key2��ʱ��
		String key2Then = redisService.get("key2");
		System.out.println("3s���Redis������ȡ�� key2:" + key2Then);// ���null
		boolean bool = redisService.exists("key2");
		System.out.println("key2��״̬" + bool);
		Set<String> keys = redisService.keys("*");// ����鿴���е�keys
		System.out.println("Rdeis �����е�key:" + keys);
		redisService.flushDB(); // qing
	}
}
