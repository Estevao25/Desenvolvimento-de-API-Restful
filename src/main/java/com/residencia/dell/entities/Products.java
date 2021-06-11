package com.residencia.dell.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Products {
    private Integer prodId;
    private Integer category;
    private String title;
    private String actor;
    private BigDecimal price;
    private Integer special;
    private Integer commonProdId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    @Column(name = "category")
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "actor")
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "special")
    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }

    @Column(name = "common_prod_id")
    public Integer getCommonProdId() {
        return commonProdId;
    }

    public void setCommonProdId(Integer commonProdId) {
        this.commonProdId = commonProdId;
    }
}
