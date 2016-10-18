package com.apress.springrecipes.springbatch;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * This class writes the user registration by calling an RPC service (whose client interface is wired in using Spring
 */
public class UserRegistrationServiceItemWriter implements ItemWriter<UserRegistration> {
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceItemWriter.class);

    // this is the client interface to an HTTP Invoker service.
    @Autowired
    private UserRegistrationService userRegistrationService;

    /**
     * takes aggregated input from the reader and 'writes' them using a custom implementation.
     */
    public void write(List<?extends UserRegistration> items)
        throws Exception {
        for (final UserRegistration userRegistrationRegistration : items) {
            UserRegistration registeredUserRegistration = userRegistrationService.registerUser(
                    userRegistrationRegistration);
            logger.debug("Registered: {}", ToStringBuilder.reflectionToString(registeredUserRegistration));
        }
    }
}
