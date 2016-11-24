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
		String ping = jedisCache.ping();// 测试是否连接成功,连接成功输出PONG
		System.out.println("连接成功 ,返回值:" + ping);
		long rSize = jedisCache.dbSize();// 查看redis存储数据
		System.out.println("Redis当前存储的数据量:" + rSize);
		jedisCache.set("key1", "value1");// 存储(默认存活30min)
		System.out.println("存储 键值为 key1,value1");
		String key1 = jedisCache.get("key1");// 取值
		System.out.println("从Redis缓存中取到 key1:" + key1);
		System.out.println("存储 键值为 key2,value2 存在时间为2s");
		jedisCache.set("key2", "value2", 2);// 设值,并且设置数据的存活时间(s)
		String key2 = jedisCache.get("key2");
		System.out.println("从Redis缓存中取到 key2:" + key2);
		try {
			Thread.sleep(3000);// 通过睡眠来让key2的时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String key2Then = jedisCache.get("key2");
		System.out.println("3s后从Redis缓存中取到 key2:" + key2Then);// 输出null
		boolean bool = jedisCache.exists("key2");
		System.out.println("key2的状态" + bool);
		Set<String> keys = jedisCache.keys("*");// 这里查看所有的keys
		System.out.println("Rdeis 中所有的key:" + keys);
		jedisCache.flushDB(); // 清空
		
	}

}
