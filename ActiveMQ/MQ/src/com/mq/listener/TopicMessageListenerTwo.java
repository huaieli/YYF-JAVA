package com.mq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.mq.service.MqService;

/**
 * JMS 订阅模式监听(Topic)
 * 
 * 由于订阅模式是可以多个消费 在此demo中设置了两个监听 此为第一个
 * 
 * @author yyf
 * 
 */
public class TopicMessageListenerTwo implements MessageListener {
	@Autowired
	private MqService mqService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		try {
			mqService.doTopicTwoLister(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
