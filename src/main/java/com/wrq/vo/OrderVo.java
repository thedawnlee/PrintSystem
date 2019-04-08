package com.wrq.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 前去支付，订单页面展示
 * Created by wangqian on 2019/4/8.
 */
@Data
public class OrderVo {

    private String fileType;

    private String orderNo;

    private String fileName;

    private Integer singleOrDouble;

    private Integer colorOrBlack;

    private Integer pageSize;

    private Integer fileQuantity;

    private BigDecimal orderPrice;

}
