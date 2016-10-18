package com.apress.springrecipes.weather;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RmiServer {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("com.apress.springrecipes.weather.config");
    }
}
