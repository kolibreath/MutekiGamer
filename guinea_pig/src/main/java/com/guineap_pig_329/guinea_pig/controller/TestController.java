package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.repo.*;
import com.guineap_pig_329.guinea_pig.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
public class TestController {


    @Autowired
    private BannerRepo bannerRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserPermissionRepo userPermissionRepo;
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private FriendsRepo friendsRepo;
    @Autowired
    private OfficialRepo officialRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private ContestRepo contestRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private GameAttributeRepo gameAttributeRepo;

//    private Game
    @RequestMapping("/inject")
    public String inject() {
        Util.setUserInfoRepo(userInfoRepo);
        Util.setUserRepo(userRepo);
        Util.setPostRepo(postRepo) ;
        Util.setContestRepo(contestRepo);
        Util.setTeamRepo(teamRepo);
        Util.setPlayerRepo(playerRepo);
        Util.setGameRepo(gameRepo);
        Util.setGameAttributeRepo(gameAttributeRepo);
        Util.setBannerRepo(bannerRepo);

        String path ="/guinea_pig/src/main/java/com/guineap_pig_329/guinea_pig/data_src/";
        Util.genData(path.replace("/", File.separator));

        //用户内容
        User rick = new User("rick",
                "rickpass",0 ,"rick@rick.com",10);

        userRepo.save(rick);

        User morty = new User("morty",
                "mortypass",0 ,"morty@morty.com",3);


        User summer = new User("summer",
                "summerpass",0 ,"summer@summer.com",4);



        UserInfo rickInfo = new UserInfo(rick.getUserId(), Constants.OVERWATCH_THUMBNAIL,
                "A universe destroyer",Constants.MALE,"Washington, USA",70,"大学本科","Travler");
        userInfoRepo.save(rickInfo);

        userRepo.save(rick);
        userRepo.save(morty);
        userRepo.save(summer);

        Friends rick2morty = new Friends(rick.getUserId(),morty.getUserId());
        Friends summer2morty = new Friends(summer.getUserId(),morty.getUserId());

        friendsRepo.save(rick2morty);
        friendsRepo.save(summer2morty);

        Game overwatch = gameRepo.findByGameName("守望先锋");
        Game hearthStone = gameRepo.findByGameName("炉石传说");
        Game lol = gameRepo.findByGameName("英雄联盟");

        GameManage gameManage = new GameManage(rick.getUserId(),overwatch.getGameId());
        userPermissionRepo.save(gameManage);

        //用户关注的游戏
        UserGame rick2ow = new UserGame(rick.getUserId(),overwatch.getGameId());
        UserGame rick2lol = new UserGame(rick.getUserId(),lol.getGameId());

        userGameRepo.save(rick2ow);
        userGameRepo.save(rick2lol
        );

        User ow_official  = userRepo.findByUserName("守望先锋官方账号");
        User wow_official = userRepo.findByUserName("炉石传说官方账号");

        Official ow_op = new Official(overwatch.getGameId(),ow_official.getUserId());
        Official wow_op = new Official(hearthStone.getGameId(),wow_official.getUserId());

        officialRepo.save(ow_op);
        officialRepo.save(wow_op);

        return "fuck";

    }

    @RequestMapping("/1")
    public String turn2TestAjax(){
        return "test_request_yu";
    }

    @RequestMapping("/2")
    public String turn2TestRequest(){
        return "test_request";
    }

    @RequestMapping("/3")
    public String turn2RickLoveU(){
//        ImageUploader.upload();
        return "test_upload";
    }


}
