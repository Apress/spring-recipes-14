package com.apress.springrecipes.springintegration;

import org.springframework.integration.gateway.MessagingGatewaySupport;

/**
 * Created by marten on 14-04-14.
 */
public class SimpleMessagingGateway extends MessagingGatewaySupport {

    public <T> T convertSendAndReceive(Object payload) {
        return (T) super.sendAndReceive(payload);
    }
}
