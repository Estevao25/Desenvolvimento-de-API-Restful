package com.residencia.dell.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private Integer prodId;

    @NotNull(message = "A categoria não pode ser nula.")
    @Range(min = 1, max = 16, message = "A categoria deve ser de 1 à 16.")
    @Column(name = "category")
    private Integer category;

    @NotBlank(message = "Preencha o título corretamente.")
    @Size(min = 1, max = 50, message = "Tamanho mínimo: 1 / Tamanho máximo: 50")
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @NotBlank(message = "Preencha o ator corretamente.")
    @Size(min = 1, max = 50, message = "Tamanho mínimo: 1 / Tamanho máximo: 50")
    @Column(name = "actor", nullable = false, length = 50)
    private String actor;

    @NotNull(message = "O preço não poder ser nulo.")
    @DecimalMin(value = "1", message="O preço não pode ser menor que R${value}.00")
    @DecimalMax(value = "1000", message="O preço não pode ser maior que R${value}.00")
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "special")
    private Integer special;

    @Column(name = "common_prod_id")
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