package com.wrq.pojo;

import java.math.BigDecimal;

public class Color {
    private Integer id;

    private String type;

    private String shopId;

    private BigDecimal index;

    public Color(Integer id, String type, String shopId, BigDecimal index) {
        this.id = id;
        this.type = type;
        this.shopId = shopId;
        this.index = index;
    }

    public Color() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public BigDecimal getIndex() {
        return index;
    }

    public void setIndex(BigDecimal index) {
        this.index = index;
    }
}