package com.apress.springrecipes.city.jsf;

import com.apress.springrecipes.city.CityService;


public class DistanceBean {

    private String srcCity;
    private String destCity;
    private double distance;

    private CityService cityService;

    public String getSrcCity() {
        return srcCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public double getDistance() {
        return distance;
    }

    public void setSrcCity(String srcCity) {
        this.srcCity = srcCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void find() {
        distance = cityService.findDistance(srcCity, destCity);
    }
}
