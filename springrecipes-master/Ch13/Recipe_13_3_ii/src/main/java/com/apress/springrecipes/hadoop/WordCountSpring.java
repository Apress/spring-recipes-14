package com.apress.springrecipes.hadoop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by marten on 15-10-14.
 */
public class WordCountSpring {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("wordcount-context.xml");
        System.out.println("Spring Hadoop WordCount Recipe Running.");
        context.registerShutdownHook();
    }


}
