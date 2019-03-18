package com.wrq.pojo;

import java.util.Date;

public class Score {
    private Integer id;

    private Integer userId;

    private Integer shareId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer lookOver;

    public Score(Integer id, Integer userId, Integer shareId, Integer status, Date createTime, Date updateTime, Integer lookOver) {
        this.id = id;
        this.userId = userId;
        this.shareId = shareId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lookOver = lookOver;
    }

    public Score() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getLookOver() {
        return lookOver;
    }

    public void setLookOver(Integer lookOver) {
        this.lookOver = lookOver;
    }
}