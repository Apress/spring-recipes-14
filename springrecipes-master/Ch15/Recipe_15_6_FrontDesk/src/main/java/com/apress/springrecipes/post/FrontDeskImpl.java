package com.apress.springrecipes.post;

import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;

public class FrontDeskImpl extends RabbitGatewaySupport implements FrontDesk {

    public void sendMail(final Mail mail) {
        getRabbitTemplate().convertAndSend(mail);
    }
}
