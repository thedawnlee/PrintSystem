package com.wrq.dao;

import com.wrq.pojo.OrderMaster;

public interface OrderMasterMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);
}