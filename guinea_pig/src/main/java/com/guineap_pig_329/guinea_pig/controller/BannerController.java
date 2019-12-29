package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerRepo bannerRepo;

    //Banner指的是所有的Banner 不做内容的分类
    @RequestMapping("/all")
    public List<Banner> getBanners(){
        List<Banner> banner  = bannerRepo.findAll();
        return banner;
    }
}
