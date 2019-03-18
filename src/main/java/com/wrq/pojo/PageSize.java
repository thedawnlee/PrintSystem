package com.wrq.pojo;

public class PageSize {
    private Integer id;

    private String type;

    private Integer shopId;

    private String index;

    public PageSize(Integer id, String type, Integer shopId, String index) {
        this.id = id;
        this.type = type;
        this.shopId = shopId;
        this.index = index;
    }

    public PageSize() {
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index == null ? null : index.trim();
    }
}