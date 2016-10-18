package com.apress.springrecipes.bank.config;

import com.apress.springrecipes.bank.AccountDao;
import com.apress.springrecipes.bank.AccountService;
import com.apress.springrecipes.bank.AccountServiceImpl;
import com.apress.springrecipes.bank.JdbcAccountDao;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by marten on 16-06-14.
 */
@Configuration
public class BankConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ClientDriver.class.getName());
        dataSource.setUrl("jdbc:derby://localhost:1527/bank;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public AccountDao accountDao() {
        JdbcAccountDao accountDao = new JdbcAccountDao();
        accountDao.setDataSource(dataSource());
        return accountDao;
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(accountDao());
    }
}
