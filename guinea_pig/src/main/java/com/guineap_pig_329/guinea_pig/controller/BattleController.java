package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.BattleSearchWrapper;
import com.guineap_pig_329.guinea_pig.dao.wrapper.ContestWrapper;
import com.guineap_pig_329.guinea_pig.repo.*;
import com.guineap_pig_329.guinea_pig.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

//import jdk.vm.ci.meta.Constant;

@RestController
@RequestMapping("battle")
public class BattleController {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    PostRepo postRepo;
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

    @Autowired
    private PlayerRepo playerRepo;

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


    /**
     * 搜索和游戏相关的内容
     * @param search
     * @return
     */
    @GetMapping("/game")
    public ResultBean search(@RequestParam String search){
        List<Game> resultGames = gameRepo.findByGameNameContaining(search);

        List<Post> resultPosts = new LinkedList<>();
        List<Post> posts = postRepo.findAll();
        for(Post post:posts){
            if(post.getTitle().contains(search) || post.getContent().contains(search)){
                resultPosts.add(post);
            }
        }
        int postLength = resultPosts.size() > 4? 4 :resultPosts.size();
        int gameLength = resultGames.size() > 4? 4 :resultGames.size();

        BattleSearchWrapper battleSearchWrapper = new BattleSearchWrapper(resultPosts.subList(0,postLength),
                resultGames.subList(0,gameLength));
       return ResultBean.success(battleSearchWrapper);
    }

    @RequestMapping("/newsDefault")
    public ResultBean getdefault(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int gameId=userSession.getGameId();
        Official official=officialRepo.findByGameId(gameId);
        List<Post>posts=postRepo.findAllByUserId(official.getUserId());
        return ResultBean.success(posts);
    }

    @RequestMapping("/match/{gameId}")
    public ResultBean getMatchRecord(@PathVariable("gameId") int gameId){
        List<Contest> contests = contestRepo.findByGameId(gameId);
        List<ContestWrapper> contestWrappers = new LinkedList<>();
        for(Contest contest:contests){
            Team team1 = teamRepo.findByTeamName(contest.getTeamName1());
            Team team2 = teamRepo.findByTeamName(contest.getTeamName2());

            ContestWrapper contestWrapper = new ContestWrapper(
              contest.getTeamName1(),
              contest.getTeamName2(),
                    team1.getAvatar(),
                    team2.getAvatar(),
                    contest.getTime(),
                    contest.getRecordReviewLink(),
                    contest.getTeamScore1(),
                    contest.getTeamScore2(),
                    contest.getGameId(),
                    contest.getWinnerId(),
                    contest.getStatus(),
                    contest.getMatchId()
            );

            contestWrappers.add(contestWrapper);
        }
        return ResultBean.success(contestWrappers);
    }


    @RequestMapping("/team/{gameId}")
    public ResultBean getTeamInfo(@PathVariable("gameId") int gameId){
        return  ResultBean.success(teamRepo.findByGameId(gameId));
    }

//    获得特定个战队的信息
    @RequestMapping("/teaminfo")
    public ResultBean getTeam(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int teamId=userSession.getTeamId();
        Team team=teamRepo.findAllByTeamId(teamId);
        return ResultBean.success(team);
    }
    @RequestMapping("/teamMember")
    public ResultBean getmember(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int teamId=userSession.getTeamId();
        List<Player>players=playerRepo.findAllByTeamId(teamId);
        return ResultBean.success(players);
    }

    @RequestMapping("/teamBattle")
    public ResultBean getTeamBattle(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int teamId=userSession.getTeamId();
        String teamName=teamRepo.findAllByTeamId(teamId).getTeamName();
        List<Contest>contests=contestRepo.findAllByTeamName1OrTeamName2(teamName,teamName);
        return ResultBean.success(contests);
    }
}
