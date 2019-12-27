package com.guineap_pig_329.guinea_pig.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.Util;
import com.guineap_pig_329.guinea_pig.dao.ManagementElement;
import com.guineap_pig_329.guinea_pig.dao.Plate;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.dao.UserInfo;
import com.guineap_pig_329.guinea_pig.repo.PlateRepo;
import com.guineap_pig_329.guinea_pig.repo.UserInfoRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    ObjectMapper mapper;

    public String getString(Object object){
        //具体例子具体分析
        String result = "";
        if(object instanceof UserInfo.Statics){
            try {
                result = mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping("/hello")
    public String say(){
//        Util util = new Util();
        injectInfo();
        return "hello world.";
    }


    @Autowired
    UserRepo userRepo;
    @Autowired
    UserInfoRepo repo;
    @Autowired
    PlateRepo plateRepo;

    public void injectInfo(){

        /**
         * Rick
         **/

        Plate overwatch = new Plate(Constants.OVERWATCH,Constants.OVERWATCH_THUMBNAIL);
        plateRepo.save(overwatch);

        UserInfo rickInfo = new UserInfo(
                "Universe destroyer",
                getString(
                        new UserInfo.Statics(Constants.OVERWATCH,"19",3500,500)),
                Constants.THUMBNAIL,
                10
        );

        repo.save(rickInfo);

        ManagementElement managementElement = new ManagementElement(Constants.OVERWATCH_ID);

        String managementRick = getString(managementElement);

        User rick = new User(
                "Rick",
                "rick@rick.com",
                Constants.PERSONAL,
                rickInfo.getUserInfoId(),
                managementRick
        );

        userRepo.save(rick);
    }

}
