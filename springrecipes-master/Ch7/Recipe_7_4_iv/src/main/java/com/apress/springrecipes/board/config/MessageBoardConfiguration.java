package com.apress.springrecipes.board.config;

import com.apress.springrecipes.board.service.MessageBoardService;
import com.apress.springrecipes.board.service.MessageBoardServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 06-06-14.
 */
@Configuration
public class MessageBoardConfiguration {

    @Bean
    public MessageBoardService messageBoardService() {
        return new MessageBoardServiceImpl();
    }
}
