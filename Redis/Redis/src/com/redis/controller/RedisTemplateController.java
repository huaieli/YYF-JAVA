package com.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.service.RedisTemplateService;

@Controller
@RequestMapping(value = "/redis")
public class RedisTemplateController {
	@Autowired
	private RedisTemplateService redisService;
	
	@RequestMapping(value = "/bean")
	public @ResponseBody String bean(){
		redisService.saveUser();
		return 	redisService.getUser();
	}
	@RequestMapping(value = "/String")
	public @ResponseBody String string(){
		redisService.saveString();
		return 	redisService.getString();
	}
	@RequestMapping(value = "/list")
	public @ResponseBody String list(){
		redisService.saveList();
		return 	redisService.getList();
	}
	@RequestMapping(value = "/set")
	public @ResponseBody String set(){
		redisService.saveSet();
		return redisService.getSet();
	}
	@RequestMapping(value = "/map")
	public @ResponseBody String map(){
		redisService.saveMap();
		return redisService.getMap();
	}
	
}
