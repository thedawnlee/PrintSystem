package com.wrq.service;

import com.wrq.commons.ServerResponse;
import com.wrq.form.CreateOrderForm;
import com.wrq.pojo.User;


/**
 * Created by wangqian on 2019/4/4.
 */
public interface IOrderService {

    ServerResponse create(CreateOrderForm form, User user);

    ServerResponse getOrderBeforePay(User user, String orderNo );

    ServerResponse getOrderList(Integer userId,Integer pageNum, Integer pageSize);

    ServerResponse getOrderListForShop(Integer userId, Integer pageNum, Integer pageSize);

    ServerResponse getOrderDetailForBackend( Integer userId, String orderNo );

    ServerResponse checkOrderHasFile( Integer userId, String fileNewName );
}
