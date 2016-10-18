package com.apress.springrecipes.springbatch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;


public class UserRegistrationItemReader implements ItemReader<UserRegistration> {

    @Autowired
    private UserRegistrationService userRegistrationService;

    public UserRegistration read() throws Exception, UnexpectedInputException, ParseException {
        Date today = new Date();
        Collection<UserRegistration> registrations = userRegistrationService.getOutstandingUserRegistrationBatchForDate(1, today);

        if ((registrations != null) && (registrations.size() >= 1)) {
            return registrations.iterator().next();
        }

        return null;
    }
}
