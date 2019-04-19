package com.wrq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangqian on 2019/3/29.
 */
@Controller
public class PageController {

    /**
     * 跳转首页
     * @return 首页
     */
    @RequestMapping("/index")
    public String index(){
        return "portal/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "portal/login";
    }


    /* backend */

    @RequestMapping("/store/list")
    public String list(){
        return "backend/list";
    }

    @RequestMapping("/store/login")
    public String storeLogin(){
        return "backend/login";
    }

}
