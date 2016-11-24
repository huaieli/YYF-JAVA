package com.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.service.JedisService;

@Controller
@RequestMapping(value = "/jedis")
public class JedisController {
	@Autowired
	private JedisService jedisService;
	@RequestMapping(value = "/test")
	public @ResponseBody String bean(){
		jedisService.test();
		return "xxx";
	}
}
