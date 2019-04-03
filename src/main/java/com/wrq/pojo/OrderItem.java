package com.wrq.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private Integer fileId;

    private Integer fileQuantity;

    private BigDecimal totalPrice;

    private String sizeInfoId;

    private String userDes;

    private Date createTime;

    private Date updateTime;

    private Integer colorInfoId;

    private Integer pageInfoId;


    public OrderItem(Integer id, String orderNo, Integer userId, Integer fileId, Integer fileQuantity, BigDecimal totalPrice, String sizeInfoId, String userDes, Date createTime, Date updateTime, Integer colorInfoId, Integer pageInfoId) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.fileId = fileId;
        this.fileQuantity = fileQuantity;
        this.totalPrice = totalPrice;
        this.sizeInfoId = sizeInfoId;
        this.userDes = userDes;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.colorInfoId = colorInfoId;
        this.pageInfoId = pageInfoId;
    }

    public OrderItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileQuantity() {
        return fileQuantity;
    }

    public void setFileQuantity(Integer fileQuantity) {
        this.fileQuantity = fileQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSizeInfoId() {
        return sizeInfoId;
    }

    public void setSizeInfoId(String sizeInfoId) {
        this.sizeInfoId = sizeInfoId == null ? null : sizeInfoId.trim();
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes == null ? null : userDes.trim();
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

    public Integer getColorInfoId() {
        return colorInfoId;
    }

    public void setColorInfoId(Integer colorInfoId) {
        this.colorInfoId = colorInfoId;
    }

    public Integer getPageInfoId() {
        return pageInfoId;
    }

    public void setPageInfoId(Integer pageInfoId) {
        this.pageInfoId = pageInfoId;
    }

}