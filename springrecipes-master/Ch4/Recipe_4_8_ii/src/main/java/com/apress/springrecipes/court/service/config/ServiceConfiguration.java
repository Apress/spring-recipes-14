package com.apress.springrecipes.court.service.config;

import com.apress.springrecipes.court.domain.ReservationValidator;
import com.apress.springrecipes.court.service.ReservationService;
import com.apress.springrecipes.court.service.ReservationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

/**
 * Created by marten on 28-04-14.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl();
    }

    @Bean
    public Validator reservationValidator() {
        return new ReservationValidator();
    }
}
