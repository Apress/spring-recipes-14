package com.apress.springrecipes.caching.config;

import com.apress.springrecipes.caching.CustomerRepository;
import com.apress.springrecipes.caching.MapBasedCustomerRepository;
import com.gemstone.gemfire.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.support.GemfireCacheManager;

/**
 * Created by marten on 15-08-14.
 */
@Configuration
@EnableCaching
@ImportResource("classpath:cache-client.xml")
public class CustomerConfiguration {


    @Bean
    GemfireCacheManager cacheManager(final Cache gemfireCache) {
        GemfireCacheManager cacheManager = new GemfireCacheManager();
        cacheManager.setCache(gemfireCache);
        return cacheManager;
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new MapBasedCustomerRepository();
    }

}
