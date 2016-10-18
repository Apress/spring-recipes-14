package com.apress.springrecipes.shop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.apress.springrecipes.shop.config.ShopConfigurationGlobal;
import com.apress.springrecipes.shop.config.ShopConfigurationSpr;
import com.apress.springrecipes.shop.config.ShopConfigurationSumWin;
import com.apress.springrecipes.shop.config.ShopConfigurationAut;


public class Main {

    public static void main(String[] args) throws Exception {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	context.getEnvironment().setActiveProfiles("global","winter");
	//context.register(ShopConfigurationGlobal.class, ShopConfigurationSpr.class, ShopConfigurationSumWin.class, ShopConfigurationAut.class);
	context.scan("com.apress.springrecipes.shop"); 

	context.refresh();

        Product aaa = (Product) context.getBean("aaa");
        Product cdrw = (Product) context.getBean("cdrw");
        Product dvdrw = (Product) context.getBean("dvdrw");

        ShoppingCart cart1 = (ShoppingCart) context.getBean("shoppingCart");
        cart1.addItem(aaa);
        cart1.addItem(cdrw);
        System.out.println("Shopping cart 1 contains " + cart1.getItems());

        ShoppingCart cart2 = (ShoppingCart) context.getBean("shoppingCart");
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

	Cashier cashier = (Cashier) context.getBean("cashier");
	cashier.checkout(cart1);
	cashier.checkout(cart2);
    }
}
