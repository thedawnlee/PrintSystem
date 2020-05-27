package com.dawn.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderVoList {

    private List<OrderVo> orderVoList;

    private BigDecimal totalPrice;

    private String orderNo;

    private String phone;

    private String orderStatus;

}
