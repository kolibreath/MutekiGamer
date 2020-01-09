package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.Util;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("battle")
public class BattleController {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private OfficialRepo officialRepo;
    @Autowired
    private ContestRepo contestRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;


    /**
     * 找到关于一个游戏的官方号发的所有的帖子
     * @param gameId 查看具体的游戏Id 对应的帖子
     * @return
     */
    @RequestMapping("/news/{gameId}")
    //todo gameId
    // todo 改wrapper
    public ResultBean news(@PathVariable("gameId") int gameId){
        Official official = officialRepo.findByGameId(gameId);
        return ResultBean.success(
                Util.transform(postRepo.findAllByUserId(official.getUserId()),userRepo,userInfoRepo));
    }

    @RequestMapping("/newsDefault")
    public ResultBean getNewsDefault(HttpSession httpSession){
        UserSession userSession=(UserSession)httpSession.getAttribute(Constants.USE_SESSION_KEY);
        int gameId=userSession.getGameId();
        Official official = officialRepo.findByGameId(gameId);
        return ResultBean.success(
                Util.transform(postRepo.findAllByUserId(official.getUserId()),userRepo,userInfoRepo));
    }
//
    @GetMapping("/game")
    public ResultBean search(@RequestParam String search){
        List<Game> list = gameRepo.findByGameNameContaining(search);
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ResultBean.success_code);
        resultBean.setMessage("查询成功");
        resultBean.setData(list);
       return resultBean;
    }


    @RequestMapping("/match/{gameId}")
    public ResultBean getMatchRecord(@PathVariable("gameId") int gameId){
        List<Contest> contests = contestRepo.findByGameId(gameId);
        return ResultBean.success(contests);
    }


    @RequestMapping("/team/{gameId}")
    public ResultBean getTeamInfo(@PathVariable("gameId") int gameId){
        return  ResultBean.success(teamRepo.findByGameId(gameId));
    }


}
