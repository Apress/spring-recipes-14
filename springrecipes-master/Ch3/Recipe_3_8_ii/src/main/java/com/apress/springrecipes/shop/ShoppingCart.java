package com.apress.springrecipes.shop;
import java.util.List; 
import java.util.ArrayList; 

import org.springframework.stereotype.Component; 
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype")
public class ShoppingCart {

    private List<Product> items = new ArrayList<Product>();

    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }
}
