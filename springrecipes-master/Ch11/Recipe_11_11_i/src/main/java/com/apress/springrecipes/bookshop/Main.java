package com.apress.springrecipes.bookshop;

import com.apress.springrecipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 *
 * TO RUN:
 *
 * You need the Spring AspectJ load time weaver, spring-instrument-4.0.x.jar. If you built the source tree with Gradle,
 * there will be a build/lib/ folder in your 'transactions' folder.
 *
 * Thus, you can add this to your invocation of the java command:
 *
 * -javaagent:build/lib/spring-instrument-4.0.x.jar
 *
 */
public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("bookstore-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);


        Cashier cashier = context.getBean(BookShopCashier.class);
        List<String> isbnList = Arrays.asList(new String[]{"0001", "0002"});
        cashier.checkout(isbnList, "user1");

    }
}
