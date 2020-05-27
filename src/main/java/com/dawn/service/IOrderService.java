package com.dawn.service;

import com.dawn.commons.ServerResponse;
import com.dawn.form.CreateOrderForm;
import com.dawn.pojo.User;



public interface IOrderService {

    ServerResponse create(CreateOrderForm form, User user);

    ServerResponse getOrderBeforePay(User user, String orderNo );

    ServerResponse getOrderList(Integer userId,Integer pageNum, Integer pageSize);

    ServerResponse getOrderListForShop(Integer userId, Integer pageNum, Integer pageSize);

    ServerResponse getOrderDetailForBackend( Integer userId, String orderNo );

    ServerResponse checkOrderHasFileForBackend( Integer userId, String fileNewName );

    ServerResponse noticePackingForBackend( User user,  String getKey ,String orderNo );

    ServerResponse refuseOrderForBackend( User user,  String reason , String fileName,String orderNo );

    ServerResponse getOrderFileKey( String orderNo );

    ServerResponse closeOrder( String orderNo );

    ServerResponse overOrder ( String orderNo );
}
