package com.wrq.vo;

/**
 * Created by wangqian on 2019/4/2.
 */
public class OtherShopVo {

    private Integer shopId;

    private String shopName;

    private String shopAddress;

    private Integer dealNum;

    private String credit;

    private String miniAddress;

    public String getMiniAddress() {
        return miniAddress;
    }

    public void setMiniAddress(String miniAddress) {
        this.miniAddress = miniAddress;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
