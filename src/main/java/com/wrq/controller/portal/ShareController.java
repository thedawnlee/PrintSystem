package com.wrq.controller.portal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.form.ShareCreateForm;
import com.wrq.pojo.Shop;
import com.wrq.pojo.User;
import com.wrq.service.IShareService;
import com.wrq.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqian on 2019/4/21.
 */
@Controller
@RequestMapping("/share/")
@Slf4j
public class ShareController {

    @Autowired
    private IShareService iShareService;


    @RequestMapping("create")
    public ModelAndView share(HttpSession session, Map<String, Object> map){

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user == null){
            map.put("msg", ResponseCode.NEED_LOGON_FOR_SHARE.getDesc());
            map.put("url", "/share/create");
            return new ModelAndView("portal/login" , map);
        }else {
            return new ModelAndView("portal/share" , map);
        }
    }

    @PostMapping("create")
    @ResponseBody
    public ServerResponse create(@Valid ShareCreateForm shareCreateForm, BindingResult bindingResult, HttpSession session){

        if ( bindingResult.hasErrors() ) {
            return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
        }
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getDesc());
        }
        return  iShareService.insertShareRecode(shareCreateForm, user);
    }

    @GetMapping("/list.do")
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return  iShareService.getShareList(pageNum, pageSize);
    }

}
