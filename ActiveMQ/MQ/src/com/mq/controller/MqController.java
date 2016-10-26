package com.mq.controller;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mq.service.MqService;

@Controller
@RequestMapping("jms")
public class MqController {

	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	
	@Autowired
	private MqService mqService;
	@RequestMapping("/mq2")
	public void a() {
		System.out.println("come");
	}

	@RequestMapping("/mq")
	public @ResponseBody String testSend() throws Exception {
		String mss ="ÖÐÎÄ";
		System.out.println(mss);
		mqService.sendMessage(destination,mss);
		return "jms exute complete";
	}
}
