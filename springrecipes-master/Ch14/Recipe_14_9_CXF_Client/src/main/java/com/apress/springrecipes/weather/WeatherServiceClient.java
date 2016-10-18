package com.apress.springrecipes.weather;

import java.util.List;
import java.util.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;


public class WeatherServiceClient {

    @Autowired
    private WeatherService weatherService;

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public TemperatureInfo getTodayTemperature(String city) {
        List<Date> dates = Arrays.asList(new Date[] { new Date() });
        List<TemperatureInfo> temperatures =
            weatherService.getTemperatures(city, dates);
        return temperatures.get(0);
    }
}
