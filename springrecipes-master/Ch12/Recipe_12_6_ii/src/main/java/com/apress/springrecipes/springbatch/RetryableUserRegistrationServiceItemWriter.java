package com.apress.springrecipes.springbatch;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * This class writes the user registration by calling an RPC service (whose client interface is wired in using Spring
 */
public class RetryableUserRegistrationServiceItemWriter implements ItemWriter<UserRegistration> {
    private static final Logger logger = LoggerFactory.getLogger(RetryableUserRegistrationServiceItemWriter.class);

    // this is the client interface to an HTTP Invoker service.
    @Autowired
    private UserRegistrationService userRegistrationService;
    
    @Autowired
    private RetryTemplate retryTemplate;

    /**
     * takes aggregated input from the reader and 'writes' them using a custom implementation.
     */
    public void write(List<?extends UserRegistration> items)
        throws Exception {
        for (final UserRegistration userRegistration : items) {
            UserRegistration registeredUserRegistration = retryTemplate.execute(new RetryCallback<UserRegistration, Exception>() {
                        public UserRegistration doWithRetry(RetryContext context)
                            throws Exception {
                            return userRegistrationService.registerUser(userRegistration);
                        }
                    });

            logger.debug("Registered: {}", ToStringBuilder.reflectionToString(registeredUserRegistration));
        }
    }
}
