package com.apress.springrecipes.city;

import java.util.Map;


/**
 * A bean designed only to note inbound request signiatures
 */
public class CityServiceRequestAuditor {
    public void log(Map<String, String> attributes) {
        for (String k : attributes.keySet()) {
            System.out.println(String.format("%s=%s", k, attributes.get(k)));
        }
    }
}
