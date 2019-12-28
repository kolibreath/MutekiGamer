package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.model.UserSession;
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

  //  @Autowired
    private UserRepo userRepo;
   // private UserDao userDao;

    @Autowired
    public void setUserRepo (UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/test")
    public void inject() {
        User user = new User("rick","rickpass",Constants.PERSONAL,"hahah",
                "rick@rick.com" );
        userRepo.save(user);
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
            return "main";
        } else
            return "login";

    }
}

