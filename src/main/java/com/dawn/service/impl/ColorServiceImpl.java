package com.dawn.service.impl;

import com.dawn.dao.ColorMapper;
import com.dawn.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iColorService")
public class ColorServiceImpl implements IColorService {

    @Autowired
    private ColorMapper colorMapper;

}
