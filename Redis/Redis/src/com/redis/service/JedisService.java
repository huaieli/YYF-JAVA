package com.redis.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.core.JedisCache;

@Service
public class JedisService {
	@Autowired 
	private JedisCache jedisCache;
	public void test() {
		String ping = jedisCache.ping();// �����Ƿ����ӳɹ�,���ӳɹ����PONG
		System.out.println("���ӳɹ� ,����ֵ:" + ping);
		long rSize = jedisCache.dbSize();// �鿴redis�洢����
		System.out.println("Redis��ǰ�洢��������:" + rSize);
		jedisCache.set("key1", "value1");// �洢(Ĭ�ϴ��30min)
		System.out.println("�洢 ��ֵΪ key1,value1");
		String key1 = jedisCache.get("key1");// ȡֵ
		System.out.println("��Redis������ȡ�� key1:" + key1);
		System.out.println("�洢 ��ֵΪ key2,value2 ����ʱ��Ϊ2s");
		jedisCache.set("key2", "value2", 2);// ��ֵ,�����������ݵĴ��ʱ��(s)
		String key2 = jedisCache.get("key2");
		System.out.println("��Redis������ȡ�� key2:" + key2);
		try {
			Thread.sleep(3000);// ͨ��˯������key2��ʱ��
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String key2Then = jedisCache.get("key2");
		System.out.println("3s���Redis������ȡ�� key2:" + key2Then);// ���null
		boolean bool = jedisCache.exists("key2");
		System.out.println("key2��״̬" + bool);
		Set<String> keys = jedisCache.keys("*");// ����鿴���е�keys
		System.out.println("Rdeis �����е�key:" + keys);
		jedisCache.flushDB(); // ���
		
	}

}
