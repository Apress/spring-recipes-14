package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ProductCreator;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Battery;

import java.util.Map;
import java.util.HashMap;

@Configuration
public class ShopConfiguration {
        @Bean
	public ProductCreator productCreatorFactoryBean() {
	    ProductCreator factory = new ProductCreator();
	    Map<String, Product> products = new HashMap<String, Product>();
	    products.put("aaa", new Battery("AAA", 2.5)); 
	    products.put("cdrw", new Disc("CD-RW", 1.5)); 
	    products.put("dvdrw", new Disc("DVD-RW", 3.0));
	    factory.setProducts(products);
	    return factory;
	}

        @Bean
        public ProductCreator productCreatorFactory() {
	    return productCreatorFactoryBean();
	}
           
        @Bean           
        public Product aaa() {
	    return productCreatorFactory().createProduct("aaa");
        }
        @Bean
        public Product cdrw() { 
	    return productCreatorFactory().createProduct("cdrw");
           }
        @Bean
        public Product dvdrw() { 
	    return productCreatorFactory().createProduct("dvdrw");
           }
        
}
