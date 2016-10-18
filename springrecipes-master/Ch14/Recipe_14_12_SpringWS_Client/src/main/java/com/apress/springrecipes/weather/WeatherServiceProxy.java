package com.apress.springrecipes.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

import org.springframework.ws.client.core.WebServiceTemplate;


public class WeatherServiceProxy implements WeatherService {

    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";

    private DateFormat dateFormat;
    private WebServiceTemplate webServiceTemplate;

    public WeatherServiceProxy() throws Exception {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public WebServiceTemplate getWebServiceTemplate() { 
	return webServiceTemplate;
    }

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates) {

	GetTemperaturesRequest request = new GetTemperaturesRequest(city, dates);
        GetTemperaturesResponse response = (GetTemperaturesResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getTemperatures();
    }
}
