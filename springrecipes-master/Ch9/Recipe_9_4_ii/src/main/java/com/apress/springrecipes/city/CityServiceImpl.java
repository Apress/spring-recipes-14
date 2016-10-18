package com.apress.springrecipes.city;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import java.util.Map;


@RemoteProxy
public class CityServiceImpl implements CityService {

    private Map<String, Map<String, Double>> distanceMap;

    public void setDistanceMap(Map<String, Map<String, Double>> distanceMap) {
        this.distanceMap = distanceMap;
    }

    @RemoteMethod
    public double findDistance(String srcCity, String destCity) {
        Map<String, Double> destinationMap = distanceMap.get(srcCity);

        if (destinationMap == null) {
            throw new IllegalArgumentException("Source city not found");
        }

        Double distance = destinationMap.get(destCity);

        if (distance == null) {
            throw new IllegalArgumentException("Destination city not found");
        }
 
        return distance;
    }
}
