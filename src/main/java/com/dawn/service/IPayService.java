package com.dawn.service;

import com.dawn.commons.ServerResponse;

import java.util.Map;


public interface IPayService {

    ServerResponse pay(String orderNo, Integer userId, String path);

    ServerResponse aliCallback( Map<String,String> params );

    ServerResponse queryOrderPayStatus(Integer userId,String orderNo );

    ServerResponse trade_refund(String orderNo, Integer userId);

}
