package com.apress.springrecipes.post;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.core.support.JmsGatewaySupport;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

    @Transactional
    public Mail receiveMail() {
	return (Mail) getJmsTemplate().receiveAndConvert();
    }
}
