package com.residencia.dell.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {
    private Integer category;
    private String categoryname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category")
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Column(name = "categoryname")
    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
