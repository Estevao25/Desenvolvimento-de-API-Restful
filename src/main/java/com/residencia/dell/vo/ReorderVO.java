package com.residencia.dell.vo;

import java.util.Calendar;

public class ReorderVO {
    private Integer prodId;
    private Calendar dateLow;
    private Integer quanLow;
    private Calendar dateReordered;
    private Integer quanReordered;
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
