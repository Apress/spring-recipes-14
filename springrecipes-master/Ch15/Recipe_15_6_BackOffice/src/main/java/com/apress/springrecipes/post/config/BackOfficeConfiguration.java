package com.apress.springrecipes.post.config;

import com.apress.springrecipes.post.MailListener;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue mailQueue() {
        return new Queue("mail.queue");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setMessageListener(mailListenerAdapter());
        container.setQueues(mailQueue());
        return container;
    }

    @Bean
    public MessageListenerAdapter mailListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDelegate(mailListener());
        adapter.setDefaultListenerMethod("displayMail");
        adapter.setMessageConverter(new Jackson2JsonMessageConverter());
        return adapter;
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

}
