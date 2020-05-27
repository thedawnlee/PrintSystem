package com.dawn.dao;

import com.dawn.pojo.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface OrderMasterMapper {


    @Update("UPDATE order_master SET order_status=20 where order_no=#{orderNo}")
    int updateStatus(String orderNo);

    int deleteByPrimaryKey(String orderNo);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);

    OrderMaster selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") String orderNo);

    List<OrderMaster> selectOrderListByUserId(Integer userId);

    List<OrderMaster> selectOrderListByShopId(Integer shopId);

    OrderMaster checkByOrderNoAndShopId(@Param("shopId") Integer shopId, @Param("orderNo") String orderNo);

    int updateOrderStatusAndGetKeyByPrimaryKey(@Param("getKey") String getKey ,@Param("orderStatus") Integer order_status, @Param("orderNo") String orderNo);

    int updateOrderStatusAndReasonByPrimaryKey(@Param("reason") String reason ,@Param("orderStatus") Integer order_status, @Param("orderNo") String orderNo);

    int updateOrderStatusByOrderNo( @Param("orderStatus") Integer order_status, @Param("orderNo") String orderNo );
}