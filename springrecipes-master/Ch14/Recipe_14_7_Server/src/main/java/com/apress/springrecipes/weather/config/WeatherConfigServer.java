package com.apress.springrecipes.weather.config;

import com.apress.springrecipes.weather.WeatherService;
import com.apress.springrecipes.weather.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class WeatherConfigServer {

    @Bean
    public WeatherService weatherService() {
        WeatherService wService = new WeatherServiceImpl();
        return wService;
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("WeatherService");
        rmiService.setServiceInterface(com.apress.springrecipes.weather.WeatherService.class);
        rmiService.setService(weatherService());
        return rmiService;
    }

}
