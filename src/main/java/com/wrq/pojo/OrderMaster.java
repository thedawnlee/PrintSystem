package com.wrq.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderMaster {
    private String orderNo;

    private Integer shopId;

    private Integer buyerId;

    private String buyerName;

    private String buyerPhone;

    private String buyerEmail;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private String refuseReason;

    private String getKey;
}