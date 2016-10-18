package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.MessageHeaders;

import java.io.File;
import java.util.Map;


public class InboundFileMessageServiceActivator {
    private final Logger logger = LoggerFactory.getLogger(InboundFileMessageServiceActivator.class);

    @ServiceActivator
    public void interrogateMessage(
            @Header(MessageHeaders.ID) Map<String, Object> headers, File file) {
        logger.debug("the id of the message is {}, and name of the file payload is {}",
                headers.get(MessageHeaders.ID), headers.get(FileHeaders.FILENAME));
    }

}
