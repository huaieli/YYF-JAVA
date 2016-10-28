package com.mq.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mq.base.BaseService;

/**
 * MQ service
 * 
 * 发送接收等业务理论是要分开放置,在此demo为了方便就写在一处
 * 
 * @author yyf
 * 
 */
@Service
public class MqService extends BaseService {

	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	@Autowired
	@Qualifier("topicDestination")
	private Destination topicDestination;

	/**
	 * 模拟发送消MQ队列消息
	 */
	public void testSendQueues() {
		for (int i = 1; i <= 20; i++) {
			String mss = "发送信息:" + i;
			System.out.println(mss);
			try {
				Thread.sleep(1000); // 模拟延时
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendMessage(destination, mss);
		}
	}

	/**
	 * 模拟发送MQ订阅消息
	 */
	public void testSendTopic() {
		for (int i = 1; i < 5; i++) {
			String mss = "发送信息:" + i;
			System.out.println(mss);
			try {
				Thread.sleep(1000); // 模拟延时
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendTopic(topicDestination, mss);
		}

	}

	/**
	 * 处理监听器接受的队列消息 模拟队列消费者
	 * 
	 * @param message
	 */
	public void doQueuesLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			Thread.sleep(5000); // 模拟业务场景造成的延时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("接收到消息：" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理监听器接受的订阅消息 模拟订阅消费者一
	 * 
	 * @param message
	 */
	public void doTopicOneLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("接收到消息 TopicOne：" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理监听器接受的订阅消息 模拟订阅消费者二
	 * 
	 * @param message
	 */
	public void doTopicTwoLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("接收到消息 TopicTwo：" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
