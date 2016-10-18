package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationListener;

import java.util.Date;


public class CheckoutListener implements ApplicationListener<CheckoutEvent> {

    public void onApplicationEvent(CheckoutEvent event) {
        Date time = event.getTime();

        // Do anything you like with the checkout amount and time
        System.out.println("Checkout event [" + time + "]");
    }
}
