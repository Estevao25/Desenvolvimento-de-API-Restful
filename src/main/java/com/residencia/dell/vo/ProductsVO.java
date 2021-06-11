package com.residencia.dell.vo;

import java.math.BigDecimal;

public class ProductsVO {
    private Integer prodId;
    private Integer category;
    private String title;
    private String actor;
    private BigDecimal price;
    private Integer special;
    private Integer commonProdId;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }

    public Integer getCommonProdId() {
        return commonProdId;
    }

    public void setCommonProdId(Integer commonProdId) {
        this.commonProdId = commonProdId;
    }
}
