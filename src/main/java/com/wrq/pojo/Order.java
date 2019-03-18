package com.wrq.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderNo;

    private Integer shopId;

    private Integer buyerId;

    private String buyerName;

    private String buyerPhone;

    private String buyerEmail;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    public Order(String orderNo, Integer shopId, Integer buyerId, String buyerName, String buyerPhone, String buyerEmail, BigDecimal payment, Integer paymentType, Integer orderStatus, Integer payStatus, Date createTime, Date updateTime) {
        this.orderNo = orderNo;
        this.shopId = shopId;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerEmail = buyerEmail;
        this.payment = payment;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
        super();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}