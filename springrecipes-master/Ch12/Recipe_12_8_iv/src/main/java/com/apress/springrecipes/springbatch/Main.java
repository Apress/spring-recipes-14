package com.apress.springrecipes.springbatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("batch.xml", "user-job.xml");

    }
}
