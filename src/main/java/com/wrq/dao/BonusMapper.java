package com.wrq.dao;

import com.wrq.pojo.Bonus;

public interface BonusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bonus record);

    int insertSelective(Bonus record);

    Bonus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bonus record);

    int updateByPrimaryKey(Bonus record);

    Bonus selectBonusByShopId(Integer id);
}