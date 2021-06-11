package com.residencia.dell.vo;

import com.residencia.dell.entities.Customers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class NotaFiscalVO {
    private String firstName;
    private String lastName;
    private BigDecimal netAmount;
    private Calendar orderDate;
    private Integer orderId;
    private List<ItemOrderlinesVO> listOrderlinesVO;
    private BigDecimal totalAmount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ItemOrderlinesVO> getListOrderlinesVO() {
        return listOrderlinesVO;
    }

    public void setListOrderlinesVO(List<ItemOrderlinesVO> listOrderlinesVO) {
        this.listOrderlinesVO = listOrderlinesVO;
    }
}
