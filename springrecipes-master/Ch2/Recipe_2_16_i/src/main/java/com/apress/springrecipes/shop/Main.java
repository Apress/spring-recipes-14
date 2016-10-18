package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
            new GenericXmlApplicationContext("beans.xml");

        Product bestSeller = (Product) context.getBean("bestSeller");
        System.out.println("Best seller is: " + bestSeller);

        ProductRanking productRanking =
            (ProductRanking) context.getBean("productRanking");
        System.out.println("Product ranking from " + productRanking.getFromDate() + " to " + productRanking.getToDate());


    }
}
