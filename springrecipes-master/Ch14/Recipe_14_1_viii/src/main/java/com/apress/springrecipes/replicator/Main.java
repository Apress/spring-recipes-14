package com.apress.springrecipes.replicator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");
        System.in.read();

    }
}
