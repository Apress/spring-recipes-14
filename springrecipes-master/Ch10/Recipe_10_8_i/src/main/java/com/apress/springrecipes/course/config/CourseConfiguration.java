package com.apress.springrecipes.course.config;

import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.HibernateCourseDao;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by marten on 28-05-14.
 */
@Configuration
@EnableTransactionManagement
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao() {
        return new HibernateCourseDao(hibernateTemplate());
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionfactory().getObject());
    }

    @Bean
    public LocalSessionFactoryBean sessionfactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.apress.springrecipes.course");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.DerbyTenSevenDialect.class.getName());
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2dll.auto", "update");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(ClientDriver.class);
        dataSource.setUrl("jdbc:derby://localhost:1527/course;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionfactory().getObject());
    }
}
