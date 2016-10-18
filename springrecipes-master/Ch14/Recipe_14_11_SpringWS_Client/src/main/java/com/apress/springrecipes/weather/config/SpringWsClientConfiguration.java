package com.apress.springrecipes.weather.config;

import com.apress.springrecipes.weather.WeatherServiceClient;
import com.apress.springrecipes.weather.WeatherServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class SpringWsClientConfiguration {


    @Bean
    public WeatherServiceClient weatherServiceClient() throws Exception {
        WeatherServiceClient weatherServiceClient = new WeatherServiceClient();
        weatherServiceClient.setWeatherService(weatherServiceProxy());
        return weatherServiceClient;
    }

    @Bean
    public WeatherServiceProxy weatherServiceProxy() throws Exception {
        WeatherServiceProxy weatherServiceProxy = new WeatherServiceProxy();
        weatherServiceProxy.setWebServiceTemplate(webServiceTemplate());
        return weatherServiceProxy;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/springws/services");
        return webServiceTemplate;
    }
}
