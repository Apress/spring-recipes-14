package com.apress.springrecipes.course.config;

import com.apress.springrecipes.course.CourseDao;
import com.apress.springrecipes.course.JpaCourseDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Created by marten on 28-05-14.
 */
@Configuration
public class CourseConfiguration {

    @Bean
    public CourseDao courseDao() {
        return new JpaCourseDao(entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("course");
        return emf;
    }
}
