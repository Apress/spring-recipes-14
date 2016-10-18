package com.apress.springrecipes.court.service.config;

import com.apress.springrecipes.court.service.MemberService;
import com.apress.springrecipes.court.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 28-04-14.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }


}
