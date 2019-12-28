package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.Banner;
import com.guineap_pig_329.guinea_pig.model.SearchCriteria;
import com.guineap_pig_329.guinea_pig.repo.BannerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private BannerRepo bannerRepo;
    @RequestMapping("/api/test")
    public ResponseEntity<?> test(@RequestBody
                                  SearchCriteria criteria){
        int bannerId = criteria.getBannerId();
        Banner banners = bannerRepo.findBannerByBannerId(bannerId);
        return ResponseEntity.ok(banners);
    }
}
