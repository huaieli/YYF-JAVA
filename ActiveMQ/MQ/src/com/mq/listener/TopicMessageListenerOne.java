package com.mq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.mq.service.MqService;

public class TopicMessageListenerOne implements MessageListener {
	@Autowired
	private MqService mqService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		try {
			mqService.doTopicOneLister(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
