package com.wrq.service;

import com.wrq.commons.ServerResponse;
import com.wrq.form.ShareCreateForm;
import com.wrq.pojo.User;

/**
 * Created by wangqian on 2019/4/21.
 */
public interface IShareService {

    ServerResponse insertShareRecode(ShareCreateForm shareCreateForm, User user);

    ServerResponse getShareList(int pageNum, int pageSize);

}
