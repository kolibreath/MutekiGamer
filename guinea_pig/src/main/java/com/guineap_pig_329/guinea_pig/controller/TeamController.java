package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Game;

import com.guineap_pig_329.guinea_pig.dao.Official;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.UserGame;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.*;
import javafx.geometry.Pos;

import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import javax.xml.transform.Result;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("battle")
public class TeamController {

    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserGameRepo userGameRepo;


    @Autowired
    OfficialRepo officialRepo;

    @RequestMapping("/news")
    public List<List<Post>> news(HttpSession session){
        UserSession user=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int userId=user.getId();
        List<UserGame> userGames=userGameRepo.findAllByUserId(userId);
        List<List<Post>>posts=new ArrayList<>();
        for(UserGame userGame:userGames){
            int gameId=userGame.getGameId();
            Official official=officialRepo.findAllByGameId(gameId);
            int official_userId=official.getUserId();
            List<Post>p=postRepo.findAllByUserId(official_userId);
            posts.add(p);
        }
        return posts;
    }

    @RequestMapping("/search/{gameName}")
    public ResultBean search(@PathVariable("gameName")String gameName){
        List<Game> list = gameRepo.findByGameNameLike(gameName);
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ResultBean.success_code);
        resultBean.setMessage("查询成功");
        resultBean.setData(list);
       return resultBean;
    }


}
