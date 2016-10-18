package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ShopConfiguration {

    @Bean
	public static ReloadableResourceBundleMessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
	String[] resources = {"classpath:messages"};
	messageSource.setBasenames(resources);
	messageSource.setCacheSeconds(1);
	return messageSource;
    }
}
