package com.apress.springrecipes.city.config;

import com.apress.springrecipes.city.CityService;
import com.apress.springrecipes.city.CityServiceImpl;
import com.apress.springrecipes.city.servlet.DistanceHttpRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marten on 09-07-14.
 */
@Configuration
public class DistanceConfiguration {

    @Bean
    public CityService cityService() {
        CityServiceImpl cityService = new CityServiceImpl();

        Map<String, Map<String, Double>> distances = new HashMap<>();
        Map<String, Double> newYorkDistances = new HashMap<>();
        newYorkDistances.put("London", 5574.0);
        newYorkDistances.put("Beijing", 10976.0);
        distances.put("New York", newYorkDistances);
        cityService.setDistanceMap(distances);
        return cityService;
    }

    @Bean
    public DistanceHttpRequestHandler distance() {
        return new DistanceHttpRequestHandler(cityService());
    }

}
