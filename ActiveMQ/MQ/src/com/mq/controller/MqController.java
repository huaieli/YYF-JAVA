package com.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mq.service.MqService;

/**
 * MQController
 * ���Ʋ� ��������ҵ����������Ϣ��MQ
 * @author yyf
 *
 */
@Controller
@RequestMapping("activeMQ")
public class MqController {
	@Autowired
	private MqService mqService;

	/**
	 * ������MQ������Ϣ(Queusģʽ)
	 * ��ַ��localhost:8080/MQ/activeMQ/queues.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queues")
	public @ResponseBody String testSendQueues() throws Exception {
		mqService.testSendQueues();
		return "jms exute complete"; 
	}
	
	
	/**
	 * ������MQ������Ϣ(Topicģʽ)
	 * ��ַ��localhost:8080/MQ/activeMQ/topic.do
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/topic")
	public @ResponseBody String testSendTopic() throws Exception {
		mqService.testSendTopic();
		return "jms exute complete";
	}
}
