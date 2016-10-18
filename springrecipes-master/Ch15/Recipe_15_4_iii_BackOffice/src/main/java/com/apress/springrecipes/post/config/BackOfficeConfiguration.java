package com.apress.springrecipes.post.config;

import com.apress.springrecipes.post.MailListener;
import com.apress.springrecipes.post.MailMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public DefaultMessageListenerContainer  container() {
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(connectionFactory());
        dmlc.setDestinationName("mail.queue");
        dmlc.setMessageListener(mailListenerAdapter());
        dmlc.setSessionTransacted(true);
        return dmlc;
    }

    @Bean
    public MessageConverter mailMessageConverter() {
        return new MailMessageConverter();
    }

    @Bean
    public MessageListenerAdapter mailListenerAdapter() {
        MessageListenerAdapter mailListenerAdapter = new MessageListenerAdapter();
        mailListenerAdapter.setDelegate(mailListener());
        mailListenerAdapter.setDefaultListenerMethod("displayMail");
        mailListenerAdapter.setMessageConverter(mailMessageConverter());
        return mailListenerAdapter;
    }
}
