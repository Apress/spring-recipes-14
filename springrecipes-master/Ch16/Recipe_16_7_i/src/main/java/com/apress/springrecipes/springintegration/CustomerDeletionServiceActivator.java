package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

public class CustomerDeletionServiceActivator {
    static private Logger logger = LoggerFactory.getLogger(CustomerDeletionServiceActivator.class);

    @ServiceActivator
    public void deleteCustomer(Message<String> customerIdPayload) {
        logger.debug(String.format("the id of the customer to delete is %s", customerIdPayload.getPayload()));
    }
}
