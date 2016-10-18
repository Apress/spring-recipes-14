package com.apress.springrecipes.court.web.config;

import com.apress.springrecipes.court.service.InMemoryMemberService;
import com.apress.springrecipes.court.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by marten on 16-06-14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.springrecipes.court.web")
public class CourtRestConfiguration {

    @Bean
    public View jsonmembertemplate() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

    @Bean
    public MemberService memberService() {
        return new InMemoryMemberService();
    }

}
