package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/***
 * 管理整个主页面的逻辑请求
 */
@Controller
@RequestMapping("homepage")
public class HomepageController {
    //社区跳转
    @RequestMapping("/community")
    public String community(){
        return "community";
    }
    //头条跳转
    @RequestMapping("/news")
    public String news(){
        return "news";
    }
    //战场
    @RequestMapping("/battle")
    public String battle(){
        return "battle";
    }
    //我
    @RequestMapping("/my")
    public String my(){
        return "my";
    }

    @RequestMapping("/bbspost/{postId}")
    public String p(@PathVariable("postId") int postId, HttpSession httpSession){
        UserSession userSession=(UserSession)httpSession.getAttribute(Constants.USE_SESSION_KEY);
        userSession.setPostId(postId);
        httpSession.setAttribute(Constants.USE_SESSION_KEY,userSession);
        return "bbspost";}
}
