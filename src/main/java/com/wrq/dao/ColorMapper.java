package com.wrq.dao;

import com.wrq.pojo.Color;

public interface ColorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Color record);

    int insertSelective(Color record);

    Color selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Color record);

    int updateByPrimaryKey(Color record);
}