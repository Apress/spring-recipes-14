package com.apress.springrecipes.springintegration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;


public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("integration-context.xml");
        MessageChannel request = (MessageChannel) ctx.getBean("request");
        MessageChannel response = (MessageChannel) ctx.getBean("response");
        SimpleMessagingGateway msgGateway = new SimpleMessagingGateway();
        msgGateway.setRequestChannel(request);
        msgGateway.setReplyChannel(response);
        msgGateway.afterPropertiesSet();
        msgGateway.start();
        Number result = (Number) msgGateway.convertSendAndReceive(new Operands(22, 4));
        System.out.println("Result: " + result.floatValue());

    }
}
