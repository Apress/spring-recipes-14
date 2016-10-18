package com.apress.springrecipes.springintegration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("integration-context.xml");

        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        jmsTemplate.convertAndSend("solution012", customer);

    }
}
