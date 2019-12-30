package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.repo.*;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


//    private Game
    @RequestMapping("/inject")
    public String inject() {
        //todo 修改游戏介绍的图片

        //游戏内容介绍
        Game overwatch  = new Game("守望先锋","《守望先锋》是暴雪出品的首款团队射击游戏，" +
                "现已正式来到中国。游戏以近未来地球为背景，来自全球的超级英雄们将使用自己独特的能力在战场上厮杀，带给玩家顶尖的射击体验。"
        , Constants.OVERWATCH_THUMBNAIL);
        Game diablo = new Game("暗黑破坏神","《暗黑破坏神》是1996年暴雪娱乐公司推出的一款动作RPG经典游戏系列" +
                "，英文名Diablo，源于西班牙语，意为魔王、恶魔的意思。",Constants.OVERWATCH_THUMBNAIL);
        Game starcraft = new Game("星际争霸","精彩的对战、史诗级的战役、前所未有的合作模式、乐趣横生的自定义地图，尽在《星际争霸II：虚空之遗》",Constants.OVERWATCH_THUMBNAIL);
        Game wow = new Game("魔兽世界","在最新资料片《魔兽世界®：暗影国度》中游历彼岸的国度。加入盟约并围攻永恒囚牢，拯救迷失的灵魂和所有的现实。 经典怀旧服现已开放，让我们一起回家",Constants.OVERWATCH_THUMBNAIL);
        Game hearthStone = new Game("炉石传说","《炉石传说》最新扩展包“巨龙降临”现已上线，登录即可免费获得大量福利。",Constants.OVERWATCH_THUMBNAIL);

        gameRepo.save(overwatch);
        gameRepo.save(diablo);
        gameRepo.save(starcraft);
        gameRepo.save(wow);
        gameRepo.save(hearthStone);

        //用户内容
        User rick = new User("rick",
                "rickpass",0 ,"rick@rick.com",10);

        userRepo.save(rick);

        User morty = new User("morty",
                "mortypass",0 ,"morty@morty.com",3);


        User summer = new User("summer",
                "summerpass",0 ,"summer@summer.com",4);

        userRepo.save(rick);
        userRepo.save(morty);
        userRepo.save(summer);

        Friends rick2morty = new Friends(rick.getUserId(),morty.getUserId());
        Friends summer2morty = new Friends(summer.getUserId(),morty.getUserId());

        friendsRepo.save(rick2morty);
        friendsRepo.save(summer2morty);



        //用户管理权限
        GameManage gameManage = new GameManage(rick.getUserId(),overwatch.getGameId());
        userPermissionRepo.save(gameManage);

        //用户关注的游戏
        UserGame rick2ow = new UserGame(rick.getUserId(),overwatch.getGameId());
        UserGame rick2diablo = new UserGame(rick.getUserId(),diablo.getGameId());
        UserGame rick2starcraft = new UserGame(rick.getUserId(),starcraft.getGameId());

        userGameRepo.save(rick2ow);
        userGameRepo.save(rick2diablo);
        userGameRepo.save(rick2starcraft);

        //Banner
        Banner LOL = new Banner(
                "https://upload-images.jianshu.io/upload_images/4714178-222cf9be94eb28f8.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
        ,"https://www.bilibili.com/video/av79860792"
                ,System.currentTimeMillis(),
                "守望先锋无敌！",overwatch.getGameId());

        Banner anime = new Banner("https://upload-images.jianshu.io/upload_images/4714178-260647096c1ca369.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240",
        "https://www.bilibili.com/blackboard/topic/activity-2020bangumiQ1_web.html?spm_id_from=333.851.b_7265706f7274466972737431.1",
                System.currentTimeMillis(),"暗黑破坏神万岁",diablo.getGameId());

        bannerRepo.save(LOL);
        bannerRepo.save(anime);

        Post post1 = new Post(rick.getUserId(),System.currentTimeMillis(),"this is a test",Constants.PREVIEWS,"守望先锋模式修改",overwatch.getGameId());
        Post post2 = new Post(rick.getUserId(),System.currentTimeMillis(),"this is a test",Constants.PREVIEWS,"炉石传说模式修改",hearthStone.getGameId());
        Post post3 = new Post(rick.getUserId(),System.currentTimeMillis(),"this is a test",Constants.PREVIEWS,"星际争霸模式修改",starcraft.getGameId());
        Post post4 = new Post(rick.getUserId(),System.currentTimeMillis(),"this is a test",Constants.PREVIEWS,"魔兽世界模式修改",wow.getGameId());

        postRepo.save(post1);
        postRepo.save(post2);
        postRepo.save(post3);
        postRepo.save(post4);

        UserInfo rickInfo = new UserInfo(rick.getUserId(),Constants.OVERWATCH_THUMBNAIL,"the universe destroy", Constants.MALE,"Washington",70);
        userInfoRepo.save(rickInfo);

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


}
