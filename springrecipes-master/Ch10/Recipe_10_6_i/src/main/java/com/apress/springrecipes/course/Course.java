package com.apress.springrecipes.course;

import java.util.Date;


public class Course {

    private Long id;
    private String title;
    private Date beginDate;
    private Date endDate;

    private int fee;

    public Course() {
    }

    public Course(String title, Date beginDate, Date endDate, int fee) {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.fee = fee;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getFee() {
        return fee;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
