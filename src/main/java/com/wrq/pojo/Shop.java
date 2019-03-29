package com.wrq.pojo;

import java.util.Date;

public class Shop {
    private Integer id;

    private Integer ownerId;

    private String shopName;

    private String shopAddress;

    private String shopDescription;

    private String content;

    private String credit;

    private Integer status;

    private Date workTime;

    private Date closeTime;

    private Integer isReceiveOrder;

    private Date createTime;

    private Date updateTime;

    private Integer creditPeopleNum;

    private String creditTotal;

    private Integer dealNum;

    public Shop(Integer id, Integer ownerId, String shopName, String shopAddress, String shopDescription, String content, String credit, Integer status, Date workTime, Date closeTime, Integer isReceiveOrder, Date createTime, Date updateTime, Integer creditPeopleNum, String creditTotal, Integer dealNum) {
        this.id = id;
        this.ownerId = ownerId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopDescription = shopDescription;
        this.content = content;
        this.credit = credit;
        this.status = status;
        this.workTime = workTime;
        this.closeTime = closeTime;
        this.isReceiveOrder = isReceiveOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creditPeopleNum = creditPeopleNum;
        this.creditTotal = creditTotal;
        this.dealNum = dealNum;
    }

    public Shop() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription == null ? null : shopDescription.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit == null ? null : credit.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getIsReceiveOrder() {
        return isReceiveOrder;
    }

    public void setIsReceiveOrder(Integer isReceiveOrder) {
        this.isReceiveOrder = isReceiveOrder;
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

    public Integer getCreditPeopleNum() {
        return creditPeopleNum;
    }

    public void setCreditPeopleNum(Integer creditPeopleNum) {
        this.creditPeopleNum = creditPeopleNum;
    }

    public String getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(String creditTotal) {
        this.creditTotal = creditTotal == null ? null : creditTotal.trim();
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }
}