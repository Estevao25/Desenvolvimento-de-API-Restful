package com.residencia.dell.entities;

import javax.persistence.*;

@Entity
@Table(name = "cust_hist")
public class CustHist {

    @Id
    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "prod_id")
    private Integer prodId;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
