package com.dawn.controller.portal;

import com.dawn.commons.Const;
import com.dawn.commons.ServerResponse;
import com.dawn.pojo.User;
import com.dawn.service.IScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/score/")
@Slf4j
public class ScoreController {

    @Autowired
    private IScoreService iScoreService;


    /**
     * 个人中心 获取积分列表
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "get_score_list.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getScoreInfo(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "5") int pageSize, HttpSession session){

        log.info("请求了 get_score_list 接口");

        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(user != null){
            return iScoreService.getScoreList(user.getId(), pageNum, pageSize);
        }

        return  ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }


}
