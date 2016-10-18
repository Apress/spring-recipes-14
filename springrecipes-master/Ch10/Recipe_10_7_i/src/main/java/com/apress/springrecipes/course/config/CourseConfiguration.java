package com.apress.springrecipes.course.config;

import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.HibernateCourseDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

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
        sessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        return sessionFactoryBean;
    }
}
