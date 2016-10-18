package com.apress.springrecipes.replicator.config;

import com.apress.springrecipes.replicator.EmailErrorNotifier;
import com.apress.springrecipes.replicator.ErrorNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class MailConfiguration {

    @Bean
    public ErrorNotifier errorNotifier() {
        EmailErrorNotifier errorNotifier = new EmailErrorNotifier();
        errorNotifier.setMailSender(mailSender());
        return errorNotifier;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(25);
        mailSender.setUsername("system");
        mailSender.setPassword("12345");
        return mailSender;
    }
}
