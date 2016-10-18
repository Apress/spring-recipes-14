package com.apress.springrecipes.city.jsf;

import com.apress.springrecipes.city.CityService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DistanceBean {

    private String srcCity;
    private String destCity;
    private double distance;

    @ManagedProperty("#{cityService}")
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
