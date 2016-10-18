package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.io.File;


public class InboundHelloWorldFileMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundHelloWorldFileMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingFileMessage(Message<File> inboundJmsMessage)
        throws Throwable {
        File filePayload = inboundJmsMessage.getPayload();
        logger.debug("absolute path: {}, size: {}", filePayload.getAbsolutePath(), filePayload.length());
    }
}
