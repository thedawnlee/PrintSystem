package com.wrq.service.impl;

import com.wrq.dao.ColorMapper;
import com.wrq.pojo.Color;
import com.wrq.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangqian on 2019/4/1.
 */
public class ColorServiceImpl implements IColorService {

    @Autowired
    private ColorMapper colorMapper;

}
