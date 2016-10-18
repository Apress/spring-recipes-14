package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.BannerLoader;

import org.springframework.core.io.Resource;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {
         private @Value("${specialcustomer.discount:0}") double specialCustomerDiscountField;
         private @Value("${summer.discount:0}") double specialSummerDiscountField;
	 private @Value("${endofyear.discount:0}") double specialEndofyearDiscountField;

         @Value("classpath:banner.txt")
	 private Resource banner;

        @Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}

        @Bean 
	public BannerLoader bannerLoader() { 
	    BannerLoader bl = new BannerLoader();
	    bl.setBanner(banner);
	    return bl;
	}


        @Bean           
        public Product aaa() {
           Battery p1 = new Battery();
           p1.setName("AAA");
           p1.setPrice(2.5);
           p1.setRechargeable(true);
	   p1.setDiscount(specialCustomerDiscountField);
           return p1;
        }
        @Bean
        public Product cdrw() { 
	   Disc p2 = new Disc("CD-RW",1.5,specialSummerDiscountField);
           p2.setCapacity(700);
           return p2;  
           }
        @Bean
        public Product dvdrw() { 
	   Disc p2 = new Disc("DVD-RW",3.0,specialEndofyearDiscountField);
           p2.setCapacity(700);
           return p2;
           }
}
