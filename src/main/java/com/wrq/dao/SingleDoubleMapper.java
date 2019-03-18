package com.wrq.dao;

import com.wrq.pojo.SingleDouble;

public interface SingleDoubleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SingleDouble record);

    int insertSelective(SingleDouble record);

    SingleDouble selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SingleDouble record);

    int updateByPrimaryKey(SingleDouble record);
}