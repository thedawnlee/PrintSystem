package com.dawn.service;

import com.dawn.commons.ServerResponse;
import com.dawn.form.ShareCreateForm;
import com.dawn.pojo.User;

import javax.servlet.http.HttpServletResponse;


public interface IShareService {

    ServerResponse insertShareRecode(ShareCreateForm shareCreateForm, User user);

    ServerResponse getShareList(int pageNum, int pageSize);

    ServerResponse getShareDetailById(Integer shareId, User user);

    ServerResponse prepareForDownload(Integer shareId, User user);

    ServerResponse downloadForUserCenter(String path, Integer id,Integer userId, HttpServletResponse response);

    ServerResponse downloadShareByShopId(String path, Integer shareId, User user, HttpServletResponse response);

    ServerResponse getShareListTypeSort(int pageNum, int pageSize, int type);

}
