package com.mq.base;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * BaseService
 * JMS ��������
 * @author yyf
 *
 */
public class BaseService {
	@Resource(name="jmsTemplateQueues")
    private JmsTemplate jmsTemplateQueues;
	
	
	@Resource(name="jmsTemplateTopic")
    private JmsTemplate jmsTemplateTopic;
    
    /**
     * ����ģʽ (Queues)jmstemplateģ�巢������
     * 
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message) {
    	jmsTemplateQueues.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });    
    } 
    
    
    /**
     * ����ģʽ (Topic)jmstemplateģ�巢������
     * 
     * @param destination
     * @param message
     */
    public void sendTopic(Destination destination, final String message) {
    	jmsTemplateTopic.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });    
    } 
    
    
}
