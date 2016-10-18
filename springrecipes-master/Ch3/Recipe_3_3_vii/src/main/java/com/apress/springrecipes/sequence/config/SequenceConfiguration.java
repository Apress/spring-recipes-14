package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.apress.springrecipes.sequence.SequenceGenerator;
import com.apress.springrecipes.sequence.PrefixGenerator;


@Configuration
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {
        @Value("#{datePrefixGenerator}")
        private PrefixGenerator prefixGenerator;

        @Bean
        public SequenceGenerator sequenceGenerator() { 
           SequenceGenerator sequence= new SequenceGenerator();
           sequence.setInitial(100000);
           sequence.setSuffix("A");
           sequence.setPrefixGenerator(prefixGenerator);
           return sequence;  
           }
}
