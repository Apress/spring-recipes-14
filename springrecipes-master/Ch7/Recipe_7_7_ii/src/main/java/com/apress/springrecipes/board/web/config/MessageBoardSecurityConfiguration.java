package com.apress.springrecipes.board.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.sql.DataSource;

/**
 * Created by marten on 06-06-14.
 */
@Configuration
@EnableWebSecurity
public class MessageBoardSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CacheManager cacheManager;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .expressionHandler(new DefaultWebSecurityExpressionHandler())
                .antMatchers("/messageList*").hasAnyRole("USER", "GUEST")
                .antMatchers("/messagePost*").hasRole("USER")
                .antMatchers("/messageDelete*").hasRole("ADMIN")
            .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/j_spring_security_check")
                    .defaultSuccessUrl("/messageList")
                    .failureUrl("/login.jsp?error=true")
            .and()
                .logout()
                    .logoutSuccessUrl("")
            .and()
                .anonymous()
                    .principal("guest")
                    .authorities("ROLE_GUEST")
            .and()
                .rememberMe()
            ;

    }

    @Bean
    public SpringCacheBasedUserCache userCache() throws Exception {
        Cache cache = cacheManager.getCache("userCache");
        return new SpringCacheBasedUserCache(cache);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).userCache(userCache());
    }
}
