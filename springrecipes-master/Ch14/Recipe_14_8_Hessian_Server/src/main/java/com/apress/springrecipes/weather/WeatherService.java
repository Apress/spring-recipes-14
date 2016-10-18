package com.apress.springrecipes.weather;

import java.util.List;
import java.util.Date; 

public interface WeatherService {

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates);
}
