package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = 
            new GenericXmlApplicationContext("appContext.xml");
        String alert = context.getMessage("alert.checkout", null, Locale.US);
	String alert_inventory = context.getMessage("alert.inventory.checkout", new Object[] { "[DVD-RW 3.0]", new Date() }, Locale.US);

        System.out.println("The I18N message for alert.checkout is: " + alert);
        System.out.println("The I18N message for alert.inventory.checkout is: " + alert_inventory);
    }
}
