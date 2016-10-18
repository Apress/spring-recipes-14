package com.apress.springrecipes.weather.config;

import com.apress.springrecipes.weather.WeatherServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:appContext.xml")
public class WeatherConfigCxfClient {

    @Bean
    public WeatherServiceClient weatherClient() {
        WeatherServiceClient wServiceClient = new WeatherServiceClient();
        return wServiceClient;
    }

}
