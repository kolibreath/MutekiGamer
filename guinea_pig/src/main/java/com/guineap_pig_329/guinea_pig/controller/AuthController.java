package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 用户注册登录
 * 用户修改密码
 */
@Controller
public class AuthController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BannerRepo bannerRepo;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest httpServletRequest, HttpSession httpSession) {
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");

        User user = userRepo.findByUserEmail(name);
        if (password.equals(user.getUserPassword())) {
            UserSession usrSession = new UserSession(user.getUserId(), user.getUserName(), user.getUserPassword());
            httpSession.setAttribute(Constants.USE_SESSION_KEY, usrSession);
            return "HomePage";
        } else
            return "login";

    }

    @RequestMapping("/HomePage")
    public String HomePage1() {
        return "HomePage";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }


    @RequestMapping("/doregister")
    public String doregister(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");

        //TODO
        if (userRepo.findAllByUserEmail(email) != null) {
            return "register";//ajax
        } else {
            User user = new User(name, password, email);
            userRepo.save(user);
            return "login";
        }
    }

    //修改密码

    /**
     * @param session
     * @param map
     * @return 没有传新的用户密码 400
     * 出现异常500
     * 成功   200
     */
    @PostMapping("/password")
    @ResponseBody
    public ResultBean changePassword(HttpSession session,
                                     HttpServletResponse response,
                                     @RequestBody Map<String, Object> map) throws IOException {
        int userId = UserSession.getInstance(session).getCode();
        if(userId == ResultBean.SESSION_OUT_OF_DATE){
            response.sendRedirect("login");
        }
        String newPassword = (String) map.get("new_password");
        if (newPassword == null) return ResultBean.error(ResultBean.resources_not_found
                , "失败");
        int result = userRepo.updateUser(newPassword, userId);

        return ResultBean.success("修改成功");
    }


}

