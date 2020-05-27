package com.dawn.vo.backend;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class BackendOrderListVo {

    private String orderNo;

    private String buyerPhone;

    private String buyerEmail;

    private BigDecimal payment;

    private Date createTime;

    private String orderStatus;

    private Integer shopId;

}
