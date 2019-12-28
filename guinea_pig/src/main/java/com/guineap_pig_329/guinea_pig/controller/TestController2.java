package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController2 {


    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
//    private Game
    @RequestMapping("/test")
    public void inject() {
        Banner banner = new Banner("fuck","fuck",0);
        bannerRepo.save(banner);
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
