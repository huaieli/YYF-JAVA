package com.redis.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisTemplateCache<T> {
	private static int CACHE_TIME = 24 * 30;

	@SuppressWarnings("unchecked")
	@Autowired
	@Qualifier("jedisTemplate")
	public RedisTemplate redisTemplate;
	

	/**
	 * 缓存基本的对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ValueOperations<String, T> setCacheObject(String key, T value) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		operation.set(key, value);
		redisTemplate.expire(key, CACHE_TIME, TimeUnit.HOURS);
		return operation;
	}

	/**
	 * 获得缓存的基本对象。
	 * 
	 * @param key
	 * @return 缓存键值对应的数据
	 */
	@SuppressWarnings("unchecked")
	public T getCacheObject(String key) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 缓存 String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ValueOperations<String, String> setCacheString(String key,
			String value) {
		ValueOperations<String, String> operation = redisTemplate.opsForValue();
		operation.set(key, value);
		redisTemplate.expire(key, 3, TimeUnit.SECONDS);
		redisTemplate.expire(key, CACHE_TIME, TimeUnit.HOURS);
		return operation;
	}

	/**
	 * 获得缓存 String
	 * 
	 * @param key
	 * @return 缓存键值对应的数据
	 */
	@SuppressWarnings("unchecked")
	public String getCacheString(String key) {
		ValueOperations<String, String> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 缓存List数据
	 * 
	 * @param key
	 * @param dataList
	 * @return 缓存的对象
	 */
	@SuppressWarnings("unchecked")
	public ListOperations<String, T> setCacheList(String key, List dataList) {
		ListOperations listOperation = redisTemplate.opsForList();
		if (null != dataList) {
			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				listOperation.rightPush(key, dataList.get(i));
			}
		}
		return listOperation;
	}

	/**
	 * 获得缓存的list对象
	 * 
	 * @param key
	 * @return 缓存键值对应的数据
	 */
	@SuppressWarnings("unchecked")
	public List getCacheList(String key) {
		List dataList = new ArrayList();
		ListOperations listOperation = redisTemplate.opsForList();
		Long size = listOperation.size(key);
		for (int i = 0; i < size; i++) {
			dataList.add(listOperation.leftPop(key));
		}
		return dataList;
	}

	/**
	 * 缓存Set
	 * 
	 * @param key
	 * @param dataSet
	 * @return 缓存数据的对象
	 */
	@SuppressWarnings("unchecked")
	public BoundSetOperations setCacheSet(String key, Set dataSet) {
		BoundSetOperations setOperation = redisTemplate.boundSetOps(key);
		Iterator it = dataSet.iterator();
		while (it.hasNext()) {
			setOperation.add(it.next());
		}
		return setOperation;
	}

	/**
	 * 获得缓存的set
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set getCacheSet(String key) {
		Set dataSet = new HashSet();
		BoundSetOperations operation = redisTemplate.boundSetOps(key);
		Long size = operation.size();
		for (int i = 0; i < size; i++) {
			dataSet.add(operation.pop());
		}
		return dataSet;
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashOperations setCacheMap(String key,Map<?,?> dataMap) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry entry : dataMap.entrySet()) {
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getCacheMap(String key) {
		Map map = redisTemplate.opsForHash().entries(key);
		return map;
	}
}
