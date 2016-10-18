package com.apress.springrecipes.course;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "BEGIN_DATE")
    private Date beginDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "FEE")
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
