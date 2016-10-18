package com.apress.springrecipes.post;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.core.support.JmsGatewaySupport;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

    public Mail receiveMail() {
	return (Mail) getJmsTemplate().receiveAndConvert();
    }
}
