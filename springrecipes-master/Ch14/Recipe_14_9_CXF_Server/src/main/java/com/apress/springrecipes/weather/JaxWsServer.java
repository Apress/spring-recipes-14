package com.apress.springrecipes.weather;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;

public class JaxWsServer {

    public static void main(String[] args) throws IOException {
        new GenericXmlApplicationContext("cxf-context.xml");
    }
}
