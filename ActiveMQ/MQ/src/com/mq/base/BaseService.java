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
 * @author yyf
 *
 */
public class BaseService {
	@Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
	
	
	@Resource(name="jmsTemplateTopic")
    private JmsTemplate jmsTemplateTopic;
    
    /**
     * jmsTemplate 发送消息
     * 
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message) {
    	jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });    
    } 
    
    
    /**
     * jmsTemplate 发送消息
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
