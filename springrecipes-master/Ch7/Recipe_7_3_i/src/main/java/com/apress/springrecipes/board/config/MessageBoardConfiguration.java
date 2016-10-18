package com.apress.springrecipes.board.config;

import com.apress.springrecipes.board.service.MessageBoardService;
import com.apress.springrecipes.board.service.MessageBoardServiceImpl;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by marten on 06-06-14.
 */
@Configuration
public class MessageBoardConfiguration {

    @Bean
    public MessageBoardService messageBoardService() {
        return new MessageBoardServiceImpl();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ClientDriver.class.getName());
        dataSource.setUrl("jdbc:derby://localhost:1527/board;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        return dataSource;
    }
}
