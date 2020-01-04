package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Service.MailService;
import com.guineap_pig_329.guinea_pig.dao.Email;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController("mail")
public class MailSenderController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/checkCode")
    public ResultBean  sendEmail(@RequestBody Email email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的验证码" + checkCode;
        ResultBean bean;
        try {
            mailService.sendSimpleMail(email.getEmailAddress(),"注册验证码",message);
            return ResultBean.success(null);
        }catch (Exception e){
            return ResultBean.error(ResultBean.internal_error,"发送失败");
        }
    }

}
