package com.apress.springrecipes.bookshop;

import com.apress.springrecipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);

        BookShop bookShop = context.getBean(BookShop.class);
        bookShop.purchase("0001", "user1");

    }
}
