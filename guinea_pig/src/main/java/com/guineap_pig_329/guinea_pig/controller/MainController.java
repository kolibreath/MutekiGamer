package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


/***
 * 管理整个主页面的逻辑请求
 */
@Controller
public class MainController {

    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PostRepo postRepo;

    //Banner指的是所有的Banner 不做内容的分类
    @RequestMapping("/banners")
    public ModelAndView getBanners(){
        List<Banner> list = bannerRepo.findAll();
        ModelAndView modelAndView = new ModelAndView();
        //指定对应的html
        modelAndView.setViewName("/HomePage");
        //banners 对应的key 是 banners
        modelAndView.addObject("banners",list);
        return modelAndView;
    }

    @RequestMapping("/post")
    public ModelAndView getPosts(HttpSession session){
        User user  = (User) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId  = user.getUserId();
        List<Post> posts = sortPost(postRepo.findAllByUserId(userId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/HomePage");
        modelAndView.addObject("sorted_posts",posts);
        return modelAndView;
    }


    //重新排序帖子的方法
    private List<Post> sortPost(List<Post> post){
        //TODO 算法
        return post;
    }


}
