package com.apress.springrecipes.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
            new GenericXmlApplicationContext("appContext.xml");

        CommonData cdata =
            (CommonData) context.getBean("commonData");

        System.out.println(cdata.getCommonProperties());
        System.out.println(cdata.getUserOS());
        System.out.println(cdata.getUserRegion());
        System.out.println(cdata.getRandomNumber());
        System.out.println(cdata.getEmail());

    }
}
