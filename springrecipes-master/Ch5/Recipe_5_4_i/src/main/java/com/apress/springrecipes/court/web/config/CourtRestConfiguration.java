package com.apress.springrecipes.court.web.config;

import com.apress.springrecipes.court.feeds.AtomFeedView;
import com.apress.springrecipes.court.feeds.RSSFeedView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * Created by marten on 16-06-14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.springrecipes.court.web")
public class CourtRestConfiguration {

    @Bean
    public AtomFeedView atomfeedtemplate() {
        return new AtomFeedView();
    }

    @Bean
    public RSSFeedView rssfeedtemplate() {
        return new RSSFeedView();
    }

    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

}
