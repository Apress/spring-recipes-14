package com.apress.springrecipes.caching;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by marten on 28-08-14.
 */
public class MainServer {

    public static void main(String[] args) throws Exception {
        new ClassPathXmlApplicationContext("cache-server.xml");

        System.out.println("Press <Enter> to terminate the server");
        System.in.read();
        System.exit(0);
    }
}
