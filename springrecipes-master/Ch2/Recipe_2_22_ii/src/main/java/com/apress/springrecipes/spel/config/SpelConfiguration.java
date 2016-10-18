package com.apress.springrecipes.spel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.apress.springrecipes.spel.EmailUtilities;

@Configuration
public class SpelConfiguration {
        @Bean           
        public EmailUtilities emailUtilities() {
	   EmailUtilities eutil = new EmailUtilities();
           eutil.setEmail("webmaster@acme.org");
	   eutil.setPassword("springrecipes");
	   eutil.setHost("localhost:25");
           return eutil;
        }	
}
