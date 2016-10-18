package com.apress.springrecipes.springintegration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;


public class ClientMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("client-context.xml");

        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        jmsTemplate.convertAndSend("solution015", customer);

        Map<String, Object> customer2 = new HashMap<String, Object>();
        customer2.put("id", 666L);
        customer2.put("firstName", "Foo");
        customer2.put("lastName", "Bar");

        jmsTemplate.convertAndSend("solution015", customer2);

    }
}
