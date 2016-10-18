package com.apress.springrecipes.springintegration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;


public class ServerMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("integration-context.xml");

    }
}
