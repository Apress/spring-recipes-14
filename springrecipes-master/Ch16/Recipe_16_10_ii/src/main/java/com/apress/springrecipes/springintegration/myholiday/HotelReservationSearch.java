package com.apress.springrecipes.springintegration.myholiday;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


public class HotelReservationSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    private int roomsDesired;
    private Date start;
    private Date stop;
    private float maximumPrice;

    public HotelReservationSearch(float maximumPrice, int roomsDesired, Date start, Date stop) {
        super();
        this.maximumPrice = maximumPrice;
        this.roomsDesired = roomsDesired;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return SerializationUtils.clone(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return start;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public Date getStop() {
        return stop;
    }

    public void setMaximumPrice(float maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public float getMaximumPrice() {
        return maximumPrice;
    }

    public void setRoomsDesired(int roomsDesired) {
        this.roomsDesired = roomsDesired;
    }

    public int getRoomsDesired() {
        return roomsDesired;
    }
}
