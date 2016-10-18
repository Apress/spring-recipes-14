package com.apress.springrecipes.caching;

import java.io.Serializable;

/**
 * Created by marten on 18-08-14.
 */
public class Customer implements Serializable {

    private final long id;
    private String name;


    public Customer(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
