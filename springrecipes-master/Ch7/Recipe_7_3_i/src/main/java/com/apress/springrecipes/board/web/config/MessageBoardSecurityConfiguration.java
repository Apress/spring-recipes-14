package com.apress.springrecipes.board.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by marten on 06-06-14.
 */
@Configuration
@EnableWebSecurity
public class MessageBoardSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/messageList*").hasAnyRole("USER", "ANONYMOUS")
                .antMatchers("/messagePost*").hasRole("USER")
                .antMatchers("/messageDelete*").hasRole("ADMIM")
            .and()
                .formLogin()
                    .loginPage("/login.jsp")
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
                .rememberMe();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("secret").authorities("ROLE_ADMIN","ROLE_USER").and()
                .withUser("user1").password("1111").authorities("ROLE_USER").and()
                .withUser("user2").password("2222").disabled(true).authorities("ROLE_USER");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, 'true' as enabled FROM member WHERE username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT member.username, member_role.role as authorities " +
                        "FROM member, member_role " +
                        "WHERE  member.username = ? AND member.id = member_role.member_id")
        .passwordEncoder(new Md5PasswordEncoder());

    }


}
