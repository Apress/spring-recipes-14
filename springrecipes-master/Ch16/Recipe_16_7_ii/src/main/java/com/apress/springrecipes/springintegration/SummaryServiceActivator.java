package com.apress.springrecipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.Collection;

/**
 * Created by marten on 14-04-14.
 */
public class SummaryServiceActivator {

    private final Logger logger = LoggerFactory.getLogger(SummaryServiceActivator.class);

    @ServiceActivator
    public void summary(Collection<Customer> customers) {
        logger.debug("Removed {} customers.", customers.size());
    }
}
