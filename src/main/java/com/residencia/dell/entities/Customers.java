package com.residencia.dell.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customers")
public class Customers {
    private Integer customerid;
    private String firstname;
    private String lastname;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private Integer zip;
    private String country;
    private Integer region;
    private String email;
    private String phone;
    private String creditcardtype;
    private String creditcard;
    private String creditcardexpiration;
    private String username;
    private String password;
    private Integer age;
    private Integer income;
    private Character gender;
    private Collection<CustHist> custHistsByCustomerid;
    private Collection<Orders> ordersByCustomerid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }


    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Column(name = "address1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    @Column(name = "address2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Column(name = "zip")
    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }


    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Column(name = "region")
    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Column(name = "creditcardtype")
    public String getCreditcardtype() {
        return creditcardtype;
    }

    public void setCreditcardtype(String creditcardtype) {
        this.creditcardtype = creditcardtype;
    }


    @Column(name = "creditcard")
    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }


    @Column(name = "creditcardexpiration")
    public String getCreditcardexpiration() {
        return creditcardexpiration;
    }

    public void setCreditcardexpiration(String creditcardexpiration) {
        this.creditcardexpiration = creditcardexpiration;
    }


    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Column(name = "income")
    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }


    @Column(name = "gender")
    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "customer")
    public Collection<Orders> getOrdersByCustomerid() {
        return ordersByCustomerid;
    }

    public void setOrdersByCustomerid(Collection<Orders> ordersByCustomerid) {
        this.ordersByCustomerid = ordersByCustomerid;
    }
}
