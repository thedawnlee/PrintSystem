package com.dawn.service;

import com.dawn.pojo.PageSize;


public interface IPageSizeService {

    PageSize getPageSizeByShopIdAndSize(Integer shopId, Integer pageSize);

}
