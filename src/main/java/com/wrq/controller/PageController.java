package com.wrq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangqian on 2019/3/29.
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "portal/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "portal/login";
    }

    @RequestMapping("/share/list")
    public String share(){
        return "portal/shared";
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

    @RequestMapping("/store/detail")
    public String shop(){
        return "backend/shop";
    }

    @RequestMapping("/store/modify")
    public String modify(){
        return "backend/mod-shop";
    }



}
