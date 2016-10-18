package com.apress.springrecipes.weather;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class JaxWsServer {

    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext("com.apress.springrecipes.weather.config");
    }
}
