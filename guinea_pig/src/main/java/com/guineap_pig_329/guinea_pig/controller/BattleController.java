package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
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
    private UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private OfficialRepo officialRepo;
    @Autowired
    private ContestRepo contestRepo;


    /**
     * 找到关于一个游戏的官方号发的所有的帖子
     * @param session
     * @param gameId 查看具体的游戏Id 对应的帖子
     * @return
     */
    @RequestMapping("/news/{gameId}")
    //todo gameId
    public ResultBean news(HttpSession session, @PathVariable("gameId") int gameId){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        Official official = officialRepo.findByGameId(gameId);
        return ResultBean.success(postRepo.findAllByUserId(official.getUserId()));
    }

//
    @GetMapping("/game/")
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


}
