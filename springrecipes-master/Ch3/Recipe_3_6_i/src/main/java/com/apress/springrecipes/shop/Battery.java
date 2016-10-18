package com.apress.springrecipes.shop;

public class Battery extends Product {

    private boolean rechargeable;

    public Battery() {
        super();
    }

    public Battery(String name, double price, double discount) {
        super(name, price, discount);
    }

    // Getters and Setters
    public void setRechargeable(boolean rechargeable) { 
	this.rechargeable = rechargeable;
    } 
     
    public boolean getRechargeable() { 
	return rechargeable;
    }
}
