package com.wrq.controller;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.vo.DetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

}
