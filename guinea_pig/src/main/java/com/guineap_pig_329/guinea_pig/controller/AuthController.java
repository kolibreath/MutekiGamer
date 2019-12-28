package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BannerRepo bannerRepo;

//
    @RequestMapping("/test")
    public void inject() {
        Banner banner = new Banner("fuck","fuck");
        bannerRepo.save(banner);
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest httpServletRequest, HttpSession httpSession) {
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");

        User user = userRepo.findAllByUserEmail(name);
        if (password.equals(user.getUserPassword())) {
            UserSession usrSession = new UserSession(user.getUserId(), user.getUserName(), user.getUserPassword());
            httpSession.setAttribute(Constants.USE_SESSION_KEY, usrSession);
            return "HomePage1";
        } else
            return "login";

    }

    @RequestMapping("/HomePage1")
    public String HomePage1(){
        return "HomePage1";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/doregister")
    public String doregister(HttpServletRequest httpServletRequest,HttpSession httpSession)
    {
        String name=httpServletRequest.getParameter("name");
        String password=httpServletRequest.getParameter("password");
        String email=httpServletRequest.getParameter("email");

        //TODO
        if(userRepo.findAllByUserEmail(email)!=null){
            return "register";//ajax
        }
        else
        {
            User user=new User(name,password,email);
            userRepo.save(user);
            return "login";
        }
    }

//    private String default
}

