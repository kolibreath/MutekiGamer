package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;


    @RequestMapping("/user")
    public ResultBean getGames(HttpSession session){
        //todo 需要在处理
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        return ResultBean.success(userGameRepo.findAllByUserId(user.getId()) );
    }


}
