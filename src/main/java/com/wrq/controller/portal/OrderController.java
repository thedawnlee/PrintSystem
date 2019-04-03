package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wangqian on 2019/4/4.
 */
@Controller
@RequestMapping("/order/")
@Slf4j
public class OrderController {


    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable("id") Integer id, Map<String, Object> map, HttpSession session) {

        log.info("请求了 /order/create 接口， 店铺 ID = {}", id);

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            map.put("msg", ResponseCode.NEED_LOGON_FOR_CREATE.getDesc());
            map.put("url", "/order/create/" + id);
            return new ModelAndView("portal/login" , map);
        }else {

            log.info("请求了 /order/create 接口 的 else 分支");
            map.put("shopInfo", "666");
        }

        return new ModelAndView("portal/create" , map);
    }
}
