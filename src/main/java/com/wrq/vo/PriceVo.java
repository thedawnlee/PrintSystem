package com.wrq.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangqian on 2019/4/1.
 */
public class PriceVo {

    private Integer shopId;

    /* 优惠力度阈值 */
    private String bonusThreshold;

    /* 优惠力度 */
    private String bonusValue;

    /* 优惠描述*/
    private String bonusDescription;

    /* A0-A1等尺寸价格 */
    private List otherSizePrice;

    /* 彩色的比例系数 */
    private String colorfulVariable;

    /* 双页的比例系数 */
    private String doubleVariable;

    /* 双页彩色比例系数 */
    private String doubleColorfulVariable;

    /* 单页黑白A4尺寸 */
    private BigDecimal normalSingle;

    /* 单页彩色A4尺寸 */
    private BigDecimal colorfulSingle;

    /* 双页黑白A4尺寸 */
    private BigDecimal normalDouble;

    /* 双页彩色A4尺寸 */
    private BigDecimal colorfulDouble;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getBonusThreshold() {
        return bonusThreshold;
    }

    public void setBonusThreshold(String bonusThreshold) {
        this.bonusThreshold = bonusThreshold;
    }

    public String getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(String bonusValue) {
        this.bonusValue = bonusValue;
    }

    public String getBonusDescription() {
        return bonusDescription;
    }

    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }

    public List getOtherSizePrice() {
        return otherSizePrice;
    }

    public void setOtherSizePrice(List otherSizePrice) {
        this.otherSizePrice = otherSizePrice;
    }

    public String getColorfulVariable() {
        return colorfulVariable;
    }

    public void setColorfulVariable(String colorfulVariable) {
        this.colorfulVariable = colorfulVariable;
    }

    public String getDoubleVariable() {
        return doubleVariable;
    }

    public void setDoubleVariable(String doubleVariable) {
        this.doubleVariable = doubleVariable;
    }

    public String getDoubleColorfulVariable() {
        return doubleColorfulVariable;
    }

    public void setDoubleColorfulVariable(String doubleColorfulVariable) {
        this.doubleColorfulVariable = doubleColorfulVariable;
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
