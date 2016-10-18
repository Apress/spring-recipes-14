package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Configuration;
import com.apress.springrecipes.sequence.DatePrefixGenerator;
import com.apress.springrecipes.sequence.NumberPrefixGenerator;
import com.apress.springrecipes.sequence.SequenceGenerator;


@Configuration
public class SequenceConfiguration {
	@Bean
	@DependsOn("datePrefixGenerator")
        public SequenceGenerator sequenceGenerator() { 
           SequenceGenerator sequence= new SequenceGenerator();
           sequence.setInitial(100000);
           sequence.setSuffix("A");
           return sequence;  
           }
}
