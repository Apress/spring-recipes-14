package com.apress.springrecipes.weather;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class WeatherServiceClient {
    private WeatherService weatherService;

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public TemperatureInfo getTodayTemperature(String city) {
        List<Date> dates = Arrays.asList(new Date[] { new Date() });
        List<TemperatureInfo> temperatures = weatherService.getTemperatures(city, dates);

        return temperatures.get(0);
    }
}
