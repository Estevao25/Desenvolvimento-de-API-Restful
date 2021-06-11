package com.residencia.dell.entities;

import javax.persistence.*;

@Entity
@Table(name = "cust_hist")
public class CustHist {
    private Integer orderid;
    private Integer prodId;

    @Id
    @Column(name = "customerid")
    private Integer customerid;
    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    @Column(name = "orderid")
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Column(name = "prod_id")
    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
