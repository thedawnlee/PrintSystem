package com.wrq.controller.portal;

import com.wrq.commons.Const;
import com.wrq.commons.ResponseCode;
import com.wrq.commons.ServerResponse;
import com.wrq.pojo.User;
import com.wrq.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by wangqian on 2019/4/6.
 */
@Controller
@RequestMapping("/file/")
@Slf4j
public class FileController {


    @Autowired
    private IFileService iFileService;

    /**
     * 文件上传，上传到upload然后放到ftp服务器
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

        log.info(" 请求了 upload.do 接口， file = {}", file);

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登陆");
        }

        String path = request.getSession().getServletContext().getRealPath("upload");

        return iFileService.upload(file, path, user.getId());
    }
}
