package com.dawn.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人中心 订单列表展示
 */
@Data
public class OrderListVo {

    private Integer shopId;

    private String shopImg;

    private String orderNo;

    private Date updateTime;

    private BigDecimal payment;

    private String orderStatus;

    private String refuseReason;

    private String getKey;

}
