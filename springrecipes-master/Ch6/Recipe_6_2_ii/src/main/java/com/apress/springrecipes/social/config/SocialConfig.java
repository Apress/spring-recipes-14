package com.apress.springrecipes.social.config;

import com.apress.springrecipes.social.StaticUserIdSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

/**
 * Created by marten on 14-07-14.
 */
@Configuration
@EnableSocial
@PropertySource("classpath:/application.properties")
public class SocialConfig extends SocialConfigurerAdapter {

    @Override
    public StaticUserIdSource getUserIdSource() {
        return new StaticUserIdSource();
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment env) {

        connectionFactoryConfigurer.addConnectionFactory(
                new TwitterConnectionFactory(
                        env.getRequiredProperty("twitter.appId"),
                        env.getRequiredProperty("twitter.appSecret")));
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Twitter twitterTemplate(ConnectionRepository connectionRepository) {
        Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
        return connection != null ? connection.getApi() : null;
    }
}
