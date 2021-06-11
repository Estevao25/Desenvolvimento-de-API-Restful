package com.residencia.dell.entities;

import javax.persistence.Id;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "reorder")
public class Reorder {

    @Id
    @Column(name = "prod_id")
    private Integer prodId;

    @Column(name = "date_low")
    private Calendar dateLow;

    @Column(name = "quan_low")
    private Integer quanLow;

    @Column(name = "date_reordered")
    private Calendar dateReordered;

    @Column(name = "quan_reordered")
    private Integer quanReordered;

    @Column(name = "date_expected")
    private Calendar dateExpected;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Calendar getDateLow() {
        return dateLow;
    }

    public void setDateLow(Calendar dateLow) {
        this.dateLow = dateLow;
    }

    public Integer getQuanLow() {
        return quanLow;
    }

    public void setQuanLow(Integer quanLow) {
        this.quanLow = quanLow;
    }

    public Calendar getDateReordered() {
        return dateReordered;
    }

    public void setDateReordered(Calendar dateReordered) {
        this.dateReordered = dateReordered;
    }

    public Integer getQuanReordered() {
        return quanReordered;
    }

    public void setQuanReordered(Integer quanReordered) {
        this.quanReordered = quanReordered;
    }

    public Calendar getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(Calendar dateExpected) {
        this.dateExpected = dateExpected;
    }
}
