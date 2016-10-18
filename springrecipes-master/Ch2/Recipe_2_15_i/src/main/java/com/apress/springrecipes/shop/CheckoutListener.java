package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Date;

public class CheckoutListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof CheckoutEvent) {
            Date time = ((CheckoutEvent) event).getTime();

            // Do anything you like with the checkout time
            System.out.println("Checkout event [" + time + "]");
        }
    }
}
