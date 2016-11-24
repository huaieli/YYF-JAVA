package com.redis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.bean.User;
import com.redis.core.RedisTemplateCache;

@Service
public class RedisTemplateService {
	@Autowired
	private RedisTemplateCache<User> cache;
	public void saveUser(){
		User user = new User("yyf",22);
		cache.setCacheObject("user",user);
	}
	public String getUser(){
		User user =cache.getCacheObject("user");
		return user.toString();
	}
	public void saveString() {
		cache.setCacheString("string","123");
	}
	public String getString() {
		return cache.getCacheString("string");
	}
	public void saveList() {
		List<String> list =new ArrayList<String>(Arrays.asList("yyf1","yyf2","yyf3"));
		cache.setCacheList("list", list);
	}
	public String getList() {
		return cache.getCacheList("list").toString();
	}
	@SuppressWarnings("serial")
	public void saveSet() {
		Set<String> set = new HashSet<String>() {{this.add("yyf1");this.add("yyf2");}};
		cache.setCacheSet("set", set);
	}
	public String getSet() {
		return cache.getCacheSet("set").toString();
	}
	@SuppressWarnings("serial")
	public void saveMap() {
		Map<String, String> map = new HashMap<String, String>(){{put("name1", "yyf1"); put("name2", "yyf2");}};
		cache.setCacheMap("map",map);
	}
	public String getMap() {
		return cache.getCacheMap("map").toString();
	}
	


}
