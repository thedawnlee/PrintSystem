package com.wrq.dao;

import com.wrq.pojo.Shop;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    /* 根据创建时间查询店铺 */
    List<Shop> selectShopListByCreateTimeSort();

    /* 根据信用积分查询店铺 */
    List<Shop> selectShopListByCreditSort();

    /* 根据交易数查询店铺 */
    List<Shop> selectShopListByDealNumSort();

    /* 查询所有店铺 */
    List<Shop> selectAllShopByCredit();

    /* 查询除了此ID外的其他店铺 */
    List<Shop> selectOtherShopSortByCredit(Integer shopId);
}