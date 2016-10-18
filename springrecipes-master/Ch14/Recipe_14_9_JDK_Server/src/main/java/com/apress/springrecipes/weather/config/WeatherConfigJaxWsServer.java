package com.apress.springrecipes.weather.config;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("")
public class WeatherConfigJaxWsServer {

    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }
}
