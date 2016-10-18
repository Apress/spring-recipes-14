package com.apress.springrecipes.springintegration.myholiday;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.UUID;


public class HotelReservation implements Serializable, Comparable<HotelReservation> {
    private static final long serialVersionUID = 1L;
    private String hotelName;
    private float price;
    private String id;

    public HotelReservation(String hotelName, float price) {
        this.hotelName = hotelName;
        this.price = price;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HotelReservation)) {
            return false;
        }

        HotelReservation other = (HotelReservation) obj;

        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    public int compareTo(HotelReservation o) {
        return this.id.compareTo(o.id);
    }
}
