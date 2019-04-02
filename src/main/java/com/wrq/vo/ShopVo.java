package com.wrq.vo;


import java.math.BigDecimal;

/**
 * Created by wangqian on 2019/3/30.
 */
public class ShopVo {

    /* 店铺ID */
    private Integer shopId;

    private String shopName;

    private String shopDescription;

    /* 店铺评分 */
    private String credit;

    /* 交易人数 */
    private Integer dealNum;

    /* 图片服务器域名 */
    private String imgAddress;

    /* 单页黑白A4尺寸 */
    private BigDecimal normalSingle;

    /* 单页彩色A4尺寸 */
    private BigDecimal colorfulSingle;

    /* 双页黑白A4尺寸 */
    private BigDecimal normalDouble;

    /* 双页彩色A4尺寸 */
    private BigDecimal colorfulDouble;

    /* 0：在营业 1：不营业 */
    private Integer Status;

    public Integer getStatus() {
        return Status;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public BigDecimal getNormalSingle() {
        return normalSingle;
    }

    public void setNormalSingle(BigDecimal normalSingle) {
        this.normalSingle = normalSingle;
    }

    public BigDecimal getColorfulSingle() {
        return colorfulSingle;
    }

    public void setColorfulSingle(BigDecimal colorfulSingle) {
        this.colorfulSingle = colorfulSingle;
    }

    public BigDecimal getNormalDouble() {
        return normalDouble;
    }

    public void setNormalDouble(BigDecimal normalDouble) {
        this.normalDouble = normalDouble;
    }

    public BigDecimal getColorfulDouble() {
        return colorfulDouble;
    }

    public void setColorfulDouble(BigDecimal colorfulDouble) {
        this.colorfulDouble = colorfulDouble;
    }

}
