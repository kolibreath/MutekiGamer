package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.Official;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.UserGame;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.*;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("battle")
public class TeamController {

    @Autowired
    GameRepo gameRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserGameRepo userGameRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    OfficialRepo officialRepo;

    @RequestMapping("/search?p={gameName}")
    public List<Game> search(@PathVariable("gameName")String gameName){
        List<Game> games=gameRepo.findAllByGameName(gameName);
        return games;
    }

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
}
