package com.dawn.utils;

import com.dawn.dto.MessageParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class TencentMailMsgUtil {



    private String from = "1443511347@qq.com";
    @Autowired
    private static JavaMailSender javaMailSender;
    public static boolean sendmailmsg(MessageParamDto messageParamDto){
        String subject = "订单通知";
        /* 您的文件打印完毕，请在在{1}前凭取件码{2}，至{3}取件，若有问题请联系店主{4}。 */
        String content = "您的文件打印完毕，请在在"+messageParamDto.getParam1()+"前凭取件码"+messageParamDto.getParam2()+"，至"+messageParamDto.getParam3()+"取件，若有问题请联系店主"+messageParamDto.getParam4();
        try{
            //创建SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送人
            message.setFrom("1443511347@qq.com");
            //邮件接收人
            message.setTo(messageParamDto.getEmail());
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            message.setText(content);
            log.info(message.toString());
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
