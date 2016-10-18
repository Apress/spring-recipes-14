package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationEvent;
import java.util.Date;

public class CheckoutEvent extends ApplicationEvent {

    private Date time;
    
    public CheckoutEvent(Object source, Date time) {
        super(source);
        this.time = time;
    }

    public Date getTime() {
        return time;
    }
}
