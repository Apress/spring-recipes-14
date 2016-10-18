package com.apress.springrecipes.board.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by marten on 06-06-14.
 */
@Configuration
@EnableWebSecurity
public class MessageBoardSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/messageList*").hasAnyRole("USER", "ANONYMOUS")
                .antMatchers("/messagePost*").hasRole("USER")
                .antMatchers("/messageDelete*").hasRole("ADMIM")
            .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("secret").authorities("ROLE_ADMIN","ROLE_USER")
                .and().withUser("user1").password("1111").authorities("ROLE_USER");
    }
}
