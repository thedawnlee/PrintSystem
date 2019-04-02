package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by wangqian on 2019/3/29.
 */
@Controller
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @param session session
     * @return 登陆用户信息
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        log.info("请求了  login.do 接口，参数为：username = {}, password = {}",username, password);
        //service -->mybatis ->dao
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return  response;
    }

    /**
     * 获取用户信息
     * @param session 用户session
     * @return 用户信息
     */
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        log.info("请求了 get_user_info.do 接口");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return  ServerResponse.createBySuccess(user);
        }
        return  ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    /**
     * 用户登出
     * @param session 用户session
     * @return 结果
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public  ServerResponse<String> logout(HttpSession session){
        log.info("请求了 logout.do 接口");
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

}
