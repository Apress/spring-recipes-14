package com.apress.springrecipes.weather;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.beans.factory.annotation.Autowired;
 
@Endpoint
public class TemperatureMarshallingEndpoint {

    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";

    @Autowired
    private WeatherService weatherService;

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PayloadRoot(localPart="GetTemperaturesRequest",namespace=namespaceUri)
    public GetTemperaturesResponse getTemperature(GetTemperaturesRequest request) {
        List<TemperatureInfo> temperatures = weatherService.getTemperatures(request.getCity(), request.getDates());
        return new GetTemperaturesResponse(temperatures);
    }
}