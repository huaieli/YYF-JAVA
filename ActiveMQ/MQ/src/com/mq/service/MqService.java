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
 * MQservice
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
	 * ������Ϣ
	 */
	public void testSend() {
		for (int i = 1; i <= 4; i++) {
			String mss = "������Ϣ:" + i;
			System.out.println(mss);
			try {
				Thread.sleep(1000); // ģ����ʱ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendMessage(destination, mss);
		}
	}


	public void testSendTopic() {
		for (int i = 99; i <101 ; i++) {
			String mss = "������Ϣ:" + i+"00";
			System.out.println(mss);
			try {
				Thread.sleep(1000); // ģ����ʱ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendTopic(topicDestination, mss);
		}
		
	}
	
	
	/**
	 * ������������ܵ���Ϣ
	 * 
	 * @param message
	 */
	public void doLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			Thread.sleep(5000); // ģ��ҵ�񳡾���ɵ���ʱ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("���յ���Ϣ��" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void doTopicOneLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("���յ���Ϣ TopicOne��" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void doTopicTwoLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("���յ���Ϣ TopicTwo��" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


}
