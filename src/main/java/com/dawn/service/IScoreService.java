package com.dawn.service;

import com.github.pagehelper.PageInfo;
import com.dawn.commons.ServerResponse;


public interface IScoreService {

    ServerResponse<PageInfo> getScoreList(Integer userId, Integer pageNum, Integer pageSize);

}
