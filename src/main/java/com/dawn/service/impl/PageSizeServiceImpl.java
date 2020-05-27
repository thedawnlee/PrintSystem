package com.dawn.service.impl;

import com.dawn.dao.PageSizeMapper;
import com.dawn.pojo.PageSize;
import com.dawn.service.IPageSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iPageSizeService")
public class PageSizeServiceImpl implements IPageSizeService {

    @Autowired
    private PageSizeMapper pageSizeMapper;

    @Override
    public PageSize getPageSizeByShopIdAndSize(Integer shopId, Integer pageSize) {
        return pageSizeMapper.getPageSizeByShopIdAndSize(shopId, pageSize);
    }
}
