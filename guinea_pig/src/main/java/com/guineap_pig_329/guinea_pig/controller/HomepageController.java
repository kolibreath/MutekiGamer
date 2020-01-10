package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import com.guineap_pig_329.guinea_pig.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TeamRepo repo;
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
    public String bbspost(@PathVariable("postId") int postId, HttpSession httpSession){
        UserSession userSession=(UserSession)httpSession.getAttribute(Constants.USE_SESSION_KEY);
        userSession.setPostId(postId);
        httpSession.setAttribute(Constants.USE_SESSION_KEY,userSession);
        return "bbspost";
    }

    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    @RequestMapping("/teaminfo/{teamId}")
    public String team(@PathVariable("teamId") int teamId,HttpSession httpSession){
        UserSession userSession=(UserSession)httpSession.getAttribute(Constants.USE_SESSION_KEY);
        userSession.setTeamId(teamId);
        httpSession.setAttribute(Constants.USE_SESSION_KEY,userSession);
        return "teaminfo";
    }

    @RequestMapping("/alter_userinfo")
    public String alterUserInfo(){
        return "alter_userinfo";
    }
}
