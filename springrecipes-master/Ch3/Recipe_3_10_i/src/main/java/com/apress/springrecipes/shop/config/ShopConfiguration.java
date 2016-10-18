package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ProductCreator;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Battery;

@Configuration
public class ShopConfiguration {
        @Bean           
        public Product aaa() {
	    return ProductCreator.createProduct("aaa");
        }
        @Bean
        public Product cdrw() { 
	    return ProductCreator.createProduct("cdrw");
           }
        @Bean
        public Product dvdrw() { 
	    return ProductCreator.createProduct("dvdrw");
           }
        
}
