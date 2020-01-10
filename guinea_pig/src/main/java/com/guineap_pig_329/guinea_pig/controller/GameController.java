package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.GamePostWrapper;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
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
    @Autowired
    private PostRepo postRepo;

    @RequestMapping("/user")
    public ResultBean getGames(HttpSession session) throws IOException {
        int userId = UserSession.getInstance(session).getCode();
        if(userId == ResultBean.SESSION_OUT_OF_DATE){
            return ResultBean.relogin("session 过期");
        }
        List<GamePostWrapper> games = new ArrayList<>();
        List<UserGame> userGames = userGameRepo.findAllByUserId(userId);
        for (UserGame userGame: userGames){
            Game game = gameRepo.findById(userGame.getGameId()).get();
            List<Post> mypost = postRepo.findByGameIdAndUserId(userGame.getGameId(),userId);
            GamePostWrapper gamePostWrapper = new GamePostWrapper(userGame.getGameId()
            ,game.getGameName(),game.getGameIntro(),game.getPicture(),mypost);
            games.add(gamePostWrapper);
        }
        //登陆用户关于这个游戏的所有帖子
        return ResultBean.success(games);
    }


    @RequestMapping("/allgame")
    public ResultBean getallgame(){
        List<Game>games=gameRepo.findAll();
        return ResultBean.success(games);
    }
//
//    private int gameWeight(List<Game> userGame){
//
//    }
//
//
//    @RequestMapping("/selected")
//    public ResultBean getSelectedGame(HttpSession httpSession) {
//        int userId = UserSession.getInstance(httpSession).getCode();
//        List<Game> games = gameRepo.findAll();
//        List<UserGame> userGameRepoList = userGameRepo.findAllByUserId(userId);
//        for (Game game : games) {
//            for (UserGame userGame : userGameRepoList) {
//                //说明已经关注过这个游戏
//                if(userGame.getGameId() == game.getGameId()){
//                    continue;
//                }
//
//            }
//        }
//    }

}
