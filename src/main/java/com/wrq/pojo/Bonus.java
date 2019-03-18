package com.wrq.pojo;

public class Bonus {
    private Integer id;

    private Integer shopId;

    private String condition;

    private String bonus;

    private String description;

    public Bonus(Integer id, Integer shopId, String condition, String bonus, String description) {
        this.id = id;
        this.shopId = shopId;
        this.condition = condition;
        this.bonus = bonus;
        this.description = description;
    }

    public Bonus() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus == null ? null : bonus.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}