package com.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mq.service.MqService;

/**
 * MQController
 * @author yyf
 *
 */
@Controller
@RequestMapping("activeMQ")
public class MqController {
	@Autowired
	private MqService mqService;

	/**
	 * 测试向MQ发送消息
	 * 地址：localhost:8080/jms/ma.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mq")
	public @ResponseBody String testSend() throws Exception {
		mqService.testSend();
		return "jms exute complete";
	}
	
	
	/**
	 * 测试向MQ发送消息
	 * 地址：localhost:8080/jms/ma.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topic")
	public @ResponseBody String testSendTopic() throws Exception {
		mqService.testSendTopic();
		return "jms exute complete";
	}
}
