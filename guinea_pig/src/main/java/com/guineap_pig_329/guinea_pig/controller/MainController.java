package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


/***
 * 管理整个主页面的逻辑请求
 */
@RestController
@RequestMapping("homepage")
public class MainController {

    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private GameRepo gameRepo;

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
        List<Banner> banner  = bannerRepo.findByBannerContentId("fuck");
        return banner;
    }

    @RequestMapping("/posts")
    public List<Post> getPosts(HttpSession session){
        //todo 之后调试 和注册登陆连接
//        User user  = (User) session.getAttribute(Constants.USE_SESSION_KEY);
//        int userId  = user.getUserId();
//        List<Post> posts = sortPost(postRepo.findAllByUserId(userId));
        List<Post> posts = postRepo.findAllByUserId(1);
        return posts;
    }

    //TODO Usergame
    @RequestMapping("/games")
    public List<Game> getGames(HttpSession session){
//        User use =
        return gameRepo.findAllByGameName("OVERWATCH");
    }


    //重新排序帖子的方法
    private List<Post> sortPost(List<Post> post){
        //TODO 算法
        return post;
    }


}
