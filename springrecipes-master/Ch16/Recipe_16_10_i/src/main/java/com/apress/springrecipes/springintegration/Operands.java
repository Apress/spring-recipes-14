package com.apress.springrecipes.springintegration;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class Operands {
    private Number a;
    private Number b;

    public Operands(Number a, Number b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public Number getA() {
        return a;
    }

    public void setA(Number a) {
        this.a = a;
    }

    public Number getB() {
        return b;
    }

    public void setB(Number b) {
        this.b = b;
    }
}
