package com.wrq.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItem {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private Integer fileId;

    private Integer fileQuantity;

    private BigDecimal totalPrice;

    private Integer sizeInfoId;

    private String userDes;

    private Date createTime;

    private Date updateTime;

    private Integer colorInfoId;

    private Integer pageInfoId;
}