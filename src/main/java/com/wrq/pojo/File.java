package com.wrq.pojo;

import java.util.Date;

public class File {
    private Integer id;

    private String fileName;

    private Integer userId;

    private String fileSpec;

    private Integer pageNum;

    private Integer private;

    private Integer integral;

    private String description;

    private Date createTime;

    private Date updateTime;

    public File(Integer id, String fileName, Integer userId, String fileSpec, Integer pageNum, Integer private, Integer integral, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.fileName = fileName;
        this.userId = userId;
        this.fileSpec = fileSpec;
        this.pageNum = pageNum;
        this.private = private;
        this.integral = integral;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public File() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileSpec() {
        return fileSpec;
    }

    public void setFileSpec(String fileSpec) {
        this.fileSpec = fileSpec == null ? null : fileSpec.trim();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPrivate() {
        return private;
    }

    public void setPrivate(Integer private) {
        this.private = private;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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