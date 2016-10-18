package com.apress.springrecipes.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
            new GenericXmlApplicationContext("beans.xml");

        SequenceGenerator generator =
            (SequenceGenerator) context.getBean("sequenceGenerator");

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());

        SequenceGenerator generator1 =
            (SequenceGenerator) context.getBean("sequenceGenerator1");

        System.out.println(generator1.getSequence());
        System.out.println(generator1.getSequence());

        SequenceGenerator generator2 =
            (SequenceGenerator) context.getBean("sequenceGenerator2");

        System.out.println(generator2.getSequence());
        System.out.println(generator2.getSequence());

        ReverseGenerator reversegenerator =
            (ReverseGenerator) context.getBean("reverseGenerator");
        System.out.println("Reverse generator: " + reversegenerator.getInitial());
    }
}
