package com.residencia.dell.entities;

import javax.persistence.Id;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "reorder")
public class Reorder {
    private Calendar dateLow;
    private Integer quanLow;
    private Calendar dateReordered;
    private Integer quanReordered;
    private Calendar dateExpected;

    @Id
    @Column(name = "prod_id")
    private Integer prodId;
    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    @Column(name = "date_low")
    public Calendar getDateLow() {
        return dateLow;
    }

    public void setDateLow(Calendar dateLow) {
        this.dateLow = dateLow;
    }

    @Column(name = "quan_low")
    public Integer getQuanLow() {
        return quanLow;
    }

    public void setQuanLow(Integer quanLow) {
        this.quanLow = quanLow;
    }

    @Column(name = "date_reordered")
    public Calendar getDateReordered() {
        return dateReordered;
    }

    public void setDateReordered(Calendar dateReordered) {
        this.dateReordered = dateReordered;
    }

    @Column(name = "quan_reordered")
    public Integer getQuanReordered() {
        return quanReordered;
    }

    public void setQuanReordered(Integer quanReordered) {
        this.quanReordered = quanReordered;
    }

    @Column(name = "date_expected")
    public Calendar getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(Calendar dateExpected) {
        this.dateExpected = dateExpected;
    }
}
