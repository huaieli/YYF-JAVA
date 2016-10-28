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
 * ���ͽ��յ�ҵ��������Ҫ�ֿ�����,�ڴ�demoΪ�˷����д��һ��
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
	 * ģ�ⷢ����MQ������Ϣ
	 */
	public void testSendQueues() {
		for (int i = 1; i <= 20; i++) {
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

	/**
	 * ģ�ⷢ��MQ������Ϣ
	 */
	public void testSendTopic() {
		for (int i = 1; i < 5; i++) {
			String mss = "������Ϣ:" + i;
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
	 * ������������ܵĶ�����Ϣ ģ�����������
	 * 
	 * @param message
	 */
	public void doQueuesLister(Message message) {
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

	/**
	 * ������������ܵĶ�����Ϣ ģ�ⶩ��������һ
	 * 
	 * @param message
	 */
	public void doTopicOneLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("���յ���Ϣ TopicOne��" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������������ܵĶ�����Ϣ ģ�ⶩ�������߶�
	 * 
	 * @param message
	 */
	public void doTopicTwoLister(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("���յ���Ϣ TopicTwo��" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
