package com.dawn.vo.backend;

import com.dawn.vo.OrderVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class BackendOrderVo {

    private String orderNo;

    private BigDecimal payment;

    private Date createTime;

    private Date paymentTime;

    private String orderStatus;

    private String refuseReason;

    private String getKey;

    private String username;

    private String userPhone;

    private String userEmail;

    private Integer shopId;

    private List<OrderVo> orderVoList;

}
