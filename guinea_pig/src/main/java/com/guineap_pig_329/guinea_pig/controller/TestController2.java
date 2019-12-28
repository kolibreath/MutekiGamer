package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController2 {


    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserRepo userRepo;

//    private Game
    @RequestMapping("/test")
    public void inject() {
        Banner banner = new Banner("fuck","fuck",0);
        Post post = new Post(1,2,"this is fuck", 3,"this is what the fuck");
        Game game  = new Game("OVERWATCH","洗屁股");
        bannerRepo.save(banner);
        postRepo.save(post);
        gameRepo.save(game);
        User user = new User("rick","rickpass",0,"fuck","rick@rick.com",10);
        userRepo.save(user);
    }

    @RequestMapping("/1")
    public String turn2TestAjax(){
        return "TestAjax";
    }

    @RequestMapping("/2")
    public String turn2TestRequest(){
        return "test_request";
    }


}
