package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

public class CustomerDeletionServiceActivator {
    static private Logger logger = LoggerFactory.getLogger(CustomerDeletionServiceActivator.class);

    @ServiceActivator
    public Customer deleteCustomer(Message<String> customerIdPayload) {
        String id = customerIdPayload.getPayload();
        logger.debug("the id of the customer to delete is {}", id);
        Customer c = new Customer();
        c.setId(Long.valueOf(id));
        return c;
    }
}
