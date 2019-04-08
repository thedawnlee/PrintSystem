package com.wrq.service;

import com.wrq.commons.ServerResponse;
import com.wrq.form.CreateOrderForm;
import com.wrq.pojo.User;


/**
 * Created by wangqian on 2019/4/4.
 */
public interface IOrderService {

    ServerResponse create(CreateOrderForm form, User user);

    ServerResponse getOrderBeforePay(String orderNo );

}
