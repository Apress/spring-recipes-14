package com.apress.springrecipes.course.config;

import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.HibernateCourseDao;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Created by marten on 28-05-14.
 */
@Configuration
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao() {
        return new HibernateCourseDao(sessionfactory().getObject());
    }

    @Bean
    public LocalSessionFactoryBean sessionfactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        return sessionFactoryBean;
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
}
