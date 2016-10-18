package com.apress.springrecipes.bookshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ClassPathXmlApplicationContext("bookstore-context.xml");

        BookShop bookShop = context.getBean(BookShop.class);
        bookShop.purchase("0001", "user1");

    }
}
