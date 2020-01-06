package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.UserGame;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private GameRepo gameRepo;


    @RequestMapping("/user")
    public ResultBean getGames(HttpSession session) throws IOException {
        int userId = UserSession.getInstance(session).getCode();
        if(userId == ResultBean.SESSION_OUT_OF_DATE){
            return ResultBean.relogin("session 过期");
        }
        List<Game> games = new ArrayList<>();
        List<UserGame> userGames = userGameRepo.findAllByUserId(userId);
        for (UserGame userGame: userGames){
            Game game = gameRepo.findById(userGame.getGameId()).get();
            games.add(game);
        }
        return ResultBean.success(games);
    }


}
