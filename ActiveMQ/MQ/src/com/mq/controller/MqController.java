package com.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mq.service.MqService;

/**
 * MQController
 * 控制层 用来调用业务来发送消息给MQ
 * @author yyf
 *
 */
@Controller
@RequestMapping("activeMQ")
public class MqController {
	@Autowired
	private MqService mqService;

	/**
	 * 测试向MQ发送消息(Queus模式)
	 * 地址：localhost:8080/MQ/activeMQ/queues.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queues")
	public @ResponseBody String testSendQueues() throws Exception {
		mqService.testSendQueues();
		return "jms exute complete"; 
	}
	
	
	/**
	 * 测试向MQ发送消息(Topic模式)
	 * 地址：localhost:8080/MQ/activeMQ/topic.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topic")
	public @ResponseBody String testSendTopic() throws Exception {
		mqService.testSendTopic();
		return "jms exute complete";
	}
}
