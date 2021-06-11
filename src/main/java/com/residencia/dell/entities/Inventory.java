package com.residencia.dell.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    private Integer prodId;
    private Integer quanInStock;
    private Integer sales;

    @Id
    @Column(name = "prod_id")
    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }


    @Column(name = "quan_in_stock")
    public Integer getQuanInStock() {
        return quanInStock;
    }

    public void setQuanInStock(Integer quanInStock) {
        this.quanInStock = quanInStock;
    }


    @Column(name = "sales")
    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

}
