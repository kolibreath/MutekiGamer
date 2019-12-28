package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/***
 * 管理整个主页面的逻辑请求
 */
@RestController
@RequestMapping("homepage")
public class HomepageController {

    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ResponseRepo responseRepo;

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


    //Banner指的是所有的Banner 不做内容的分类
    @RequestMapping("/banners")
    public List<Banner> getBanners(){
        List<Banner> banner  = bannerRepo.findAll();
        return banner;
    }

    @RequestMapping("/posts")
    public List<Post> getPosts(HttpSession session){
        UserSession user  = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId  = user.getId();
        List<Post> posts = postRepo.findAllByUserId(userId);
        return posts;
    }

    //TODO Usergame
    @RequestMapping("/games")
    public List<UserGame> getGames(HttpSession session){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        return userGameRepo.findAllByUserId(user.getId());
    }


    //重新排序帖子的方法
    private List<Post> sortPost(List<Post> post){
        for(Post p : post) {
            User user = userRepo.findUserByUserId(p.getUserId());
            int level = user.getLevel();
            int responseSize = responseRepo.findAllByPostId(p.getPostId()).size();
            long currentTime = System.currentTimeMillis();
            long timeSubstract = p.getTime() - currentTime;
            int weight = level * 10+ responseSize * 25 - (int)timeSubstract/100;
            Collections.sort(post, (o1, o2) -> weight);
        }
        return post;
    }


}
