package com.wrq.dao;

import com.wrq.pojo.PageSize;
import org.apache.ibatis.annotations.Param;

public interface PageSizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PageSize record);

    int insertSelective(PageSize record);

    PageSize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PageSize record);

    int updateByPrimaryKey(PageSize record);

    PageSize getPageSizeByShopIdAndSize(@Param("shopId") Integer shopId, @Param("pageSize") Integer pageSize);
}