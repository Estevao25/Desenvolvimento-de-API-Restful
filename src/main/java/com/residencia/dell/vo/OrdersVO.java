package com.residencia.dell.vo;

import com.residencia.dell.entities.Customers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class OrdersVO {
    private Integer orderId;
    private Calendar orderDate;
    private BigDecimal netAmount;
    private BigDecimal tax;
    private BigDecimal totalAmount;
    private Integer customerId;
    private List<OrderlinesVO> listOrderLinesVO;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderlinesVO> getListOrderLinesVO() {
        return listOrderLinesVO;
    }

    public void setListOrderLinesVO(List<OrderlinesVO> listOrderLinesVO) {
        this.listOrderLinesVO = listOrderLinesVO;
    }
}
