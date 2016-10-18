package com.apress.springrecipes.bookshop.config;

import com.apress.springrecipes.bookshop.BookShop;
import com.apress.springrecipes.bookshop.BookShopCashier;
import com.apress.springrecipes.bookshop.Cashier;
import com.apress.springrecipes.bookshop.JdbcBookShop;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement()
public class BookstoreConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ClientDriver.class.getName());
        dataSource.setUrl("jdbc:derby://localhost:1527/bookstore;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public BookShop bookShop() {
        JdbcBookShop bookShop = new JdbcBookShop();
        bookShop.setDataSource(dataSource());
        return bookShop;
    }

    @Bean
    public Cashier cashier() {
        BookShopCashier cashier = new BookShopCashier();
        cashier.setBookShop(bookShop());
        return cashier;
    }
}
