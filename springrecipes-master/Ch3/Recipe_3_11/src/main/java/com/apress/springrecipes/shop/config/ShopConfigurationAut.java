package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Battery;

@Configuration
@Profile("autumn")
public class ShopConfigurationAut {
        @Bean           
        public Product aaa() {
           Battery p1 = new Battery();
           p1.setName("AAA");
           p1.setPrice(2.5);
           p1.setRechargeable(true);
           return p1;
        }
        @Bean
        public Product cdrw() { 
           Disc p2 = new Disc("CD-RW",1.5);
           p2.setCapacity(700);
           return p2;  
           }
        @Bean
        public Product dvdrw() { 
           Disc p2 = new Disc("DVD-RW",3.0);
           p2.setCapacity(700);
           return p2;
           }
        
}
