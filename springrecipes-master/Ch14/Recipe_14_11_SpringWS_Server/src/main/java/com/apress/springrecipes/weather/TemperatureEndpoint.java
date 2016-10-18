package com.apress.springrecipes.weather;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.dom4j.DocumentHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;
 
@Endpoint
public class TemperatureEndpoint {

    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";
    private XPath cityPath;
    private XPath datePath;
    private DateFormat dateFormat;
    @Autowired
    private WeatherService weatherService;


    public TemperatureEndpoint() { 
        // Create the XPath objects, including the namespace
        Map<String, String> namespaceUris = new HashMap<String, String>();
        namespaceUris.put("weather", namespaceUri);
        cityPath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:city");
        cityPath.setNamespaceURIs(namespaceUris);
        datePath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:date");
        datePath.setNamespaceURIs(namespaceUris);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PayloadRoot(localPart="GetTemperaturesRequest",namespace=namespaceUri)
    @ResponsePayload
    public Element getTemperature(@RequestPayload Element requestElement) throws Exception {
        // Extract the service parameters from the request message
        String city = cityPath.valueOf(requestElement);
        List<Date> dates = new ArrayList<Date>();
        for (Object node : datePath.selectNodes(requestElement)) {
            Element element = (Element) node;
            dates.add(dateFormat.parse(element.getText()));
        }

        // Invoke the back-end service to handle the request
        List<TemperatureInfo> temperatures =
            weatherService.getTemperatures(city, dates);

        // Build the response message from the result of back-end service
	Document responseDocument = DocumentHelper.createDocument();
        Element responseElement = responseDocument.addElement(
                "GetTemperaturesResponse", namespaceUri);
        for (TemperatureInfo temperature : temperatures) {
            Element temperatureElement = responseElement.addElement(
                    "TemperatureInfo");
	    temperatureElement.addAttribute("city", temperature.getCity());
            temperatureElement.addAttribute(
                    "date", dateFormat.format(temperature.getDate()));
            temperatureElement.addElement("min").setText(
                    Double.toString(temperature.getMin()));
            temperatureElement.addElement("max").setText(
                    Double.toString(temperature.getMax()));
            temperatureElement.addElement("average").setText(
                    Double.toString(temperature.getAverage()));
        }
        return responseElement;	
    }

}
