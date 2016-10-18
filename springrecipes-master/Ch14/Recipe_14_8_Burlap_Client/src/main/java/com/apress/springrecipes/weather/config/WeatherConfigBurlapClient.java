package com.apress.springrecipes.weather.config;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;

@Configuration
public class WeatherConfigBurlapClient {

    @Bean
    public BurlapProxyFactoryBean weatherService() {
        BurlapProxyFactoryBean factory = new BurlapProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8080/burlap/weather");
        factory.setServiceInterface(WeatherService.class);
        return factory;
    }

    @Bean
    public WeatherServiceClient weatherClient() {
        WeatherServiceClient wServiceClient = new WeatherServiceClient();
        return wServiceClient;
    }

}
