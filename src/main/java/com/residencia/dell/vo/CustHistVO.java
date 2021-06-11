package com.residencia.dell.vo;

public class CustHistVO {
    private CustomersVO customersVO;
    private Integer orderId;
    private Integer prodId;

    public CustomersVO getCustomersVO() {
        return customersVO;
    }

    public void setCustomersVO(CustomersVO customersVO) {
        this.customersVO = customersVO;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
