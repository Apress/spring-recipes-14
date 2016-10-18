package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;


public class MessagePayloadAggregator {

    @Aggregator
    public Message<?> joinMessages(List<Message<Customer>> customers) {
        if (customers.size() > 0) {
            List<Customer> payload = new ArrayList<>(customers.size());
            for(Message<Customer> customer : customers) {
                payload.add(customer.getPayload());
            }
            return MessageBuilder.withPayload(payload).copyHeadersIfAbsent(customers.get(0).getHeaders()).build();
        }

        return null;
    }
}
