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
	 * ��������Ķ���
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
	 * ��û���Ļ�������
	 * 
	 * @param key
	 * @return �����ֵ��Ӧ������
	 */
	@SuppressWarnings("unchecked")
	public T getCacheObject(String key) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * ���� String
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
	 * ��û��� String
	 * 
	 * @param key
	 * @return �����ֵ��Ӧ������
	 */
	@SuppressWarnings("unchecked")
	public String getCacheString(String key) {
		ValueOperations<String, String> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * ����List����
	 * 
	 * @param key
	 * @param dataList
	 * @return ����Ķ���
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
	 * ��û����list����
	 * 
	 * @param key
	 * @return �����ֵ��Ӧ������
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
	 * ����Set
	 * 
	 * @param key
	 * @param dataSet
	 * @return �������ݵĶ���
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
	 * ��û����set
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
	 * ����Map
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
	 * ��û����Map
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
