package com.apress.springrecipes.weather;

import java.util.List;
import java.util.Date; 

import javax.jws.WebService;


@WebService
public interface WeatherService {

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates);
}
