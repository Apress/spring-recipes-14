package com.apress.springrecipes.post;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.Map; 

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

    public Mail receiveMail() {
        Map map = (Map) getJmsTemplate().receiveAndConvert();
        Mail mail = new Mail();
        mail.setMailId((String) map.get("mailId"));
        mail.setCountry((String) map.get("country"));
        mail.setWeight((Double) map.get("weight"));
        return mail;
    }
}
