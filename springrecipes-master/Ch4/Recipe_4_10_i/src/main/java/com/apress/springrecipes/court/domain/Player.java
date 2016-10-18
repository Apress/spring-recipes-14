package com.apress.springrecipes.court.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Player {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 9, max = 14)
    private String phone;

    public Player() {}

    public Player(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
