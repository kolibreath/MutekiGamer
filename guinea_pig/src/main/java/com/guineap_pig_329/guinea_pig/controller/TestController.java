package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.repo.*;
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
    @Autowired
    private OfficialRepo officialRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private ContestRepo contestRepo;

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
                ,"1",
                "守望先锋无敌！",overwatch.getGameId());

        Banner anime = new Banner("https://upload-images.jianshu.io/upload_images/4714178-260647096c1ca369.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240",
        "https://www.bilibili.com/blackboard/topic/activity-2020bangumiQ1_web.html?spm_id_from=333.851.b_7265706f7274466972737431.1",
                "2","暗黑破坏神万岁",diablo.getGameId());

        bannerRepo.save(LOL);
        bannerRepo.save(anime);
//贴子
        Post post1 = new Post(rick.getUserId(),"222","this is a test",Constants.PREVIEWS,"守望先锋模式修改",overwatch.getGameId());
        Post post2 = new Post(rick.getUserId(),"222","this is a test",Constants.PREVIEWS,"炉石传说模式修改",hearthStone.getGameId());
        Post post3 = new Post(rick.getUserId(),"222","this is a test",Constants.PREVIEWS,"星际争霸模式修改",starcraft.getGameId());
        Post post4 = new Post(rick.getUserId(),"222","this is a test",Constants.PREVIEWS,"魔兽世界模式修改",wow.getGameId());

        postRepo.save(post1);
        postRepo.save(post2);
        postRepo.save(post3);
        postRepo.save(post4);

        Post p1=new Post(2,"33434","i love szy1",Constants.PREVIEWS,"title1!",2);
        Post p2=new Post(3,"33434","i love szy2",Constants.PREVIEWS,"title2!",3);
        postRepo.save(p1);
        postRepo.save(p2);

        UserInfo rickInfo = new UserInfo(rick.getUserId(),Constants.OVERWATCH_THUMBNAIL,"the universe destroy", Constants.MALE,"Washington",70);
        userInfoRepo.save(rickInfo);

        //todo more teams
        Team hzs = new Team(overwatch.getGameId(),
                "HangZhouSpark","https://spark.overwatchleague.cn/zh-cn/","https://upload-images.jianshu.io/upload_images/4714178-2973c254853e7ae5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        Team cdh = new Team(overwatch.getGameId(),
                "ChengduHunters","https://hunters.overwatchleague.cn/zh-cn/",
        "https://upload-images.jianshu.io/upload_images/4714178-80023db1c2c2ba2a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        Team shd = new Team(overwatch.getGameId(),
                "ShanghaiDragon","https://dragons.overwatchleague.cn/zh-cn/","https://upload-images.jianshu.io/upload_images/4714178-6921f0badfda9c02.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        Team gzc = new Team(overwatch.getGameId(),"GuangZhuoCharge","https://charge.overwatchleague.cn/zh-cn/",
                "https://upload-images.jianshu.io/upload_images/4714178-b94e79a88b566a5d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");


        teamRepo.save(hzs); teamRepo.save(cdh); teamRepo.save(gzc); teamRepo.save(shd);

        Contest contest1 = new Contest(
                shd.getTeamName(),
                cdh.getTeamName(),
                3,
                2,
                "回放连接",
                Constants.MATCH_END,
                "2019-05-20",
                overwatch.getGameId(),
                hzs.getTeamId());


        Contest contest2 = new Contest(
                gzc.getTeamName(),
                cdh.getTeamName(),
                4,
                1,
                "回放连接",
                Constants.MATCH_GAMING,
                "2020-01-13",
                overwatch.getGameId(),
                gzc.getTeamId());

        Contest contest3 = new Contest(
                hzs.getTeamName(),
                cdh.getTeamName(),
                1,
                5,
                "回放连接",
                Constants.MATCH_END,
                "2019-08-20",
                overwatch.getGameId(),
                cdh.getTeamId());

        Contest contest4 = new Contest(
                shd.getTeamName(),
                gzc.getTeamName(),
                4,
                0,
                "回放连接",
                Constants.MATCH_END,
                "2019-03-21",
                overwatch.getGameId(),
                shd.getTeamId());

        contestRepo.save(contest1); contestRepo.save(contest2); contestRepo.save(contest3); contestRepo.save(contest4);


        User ow_official = new User("守望先锋官方账号","owpass",Constants.OFFICLAL
        ,"ow@ow.com",10);
        User sc_official = new User("星际争霸官方账号","scpass",Constants.OFFICLAL
                ,"sc@ow.com",10);
        User ht_official = new User("炉石传说官方账号","htpass",Constants.OFFICLAL
                ,"ht@ow.com",10);
        User wow_official = new User("魔兽世界官方账号","wowpass",Constants.OFFICLAL
                ,"wow@ow.com",10);

        userRepo.save(ow_official);
        userRepo.save(sc_official);
        userRepo.save(ht_official);
        userRepo.save(wow_official);

        Official ow_op = new Official(overwatch.getGameId(),ow_official.getUserId());
        Official sc_op = new Official(starcraft.getGameId(),sc_official.getUserId());
        Official ht_op = new Official(hearthStone.getGameId(),ht_official.getUserId());
        Official wow_op = new Official(wow.getGameId(),wow_official.getUserId());

        officialRepo.save(ow_op);
        officialRepo.save(sc_op);
        officialRepo.save(ht_op);
        officialRepo.save(wow_op);

        Post owo_post = new Post(ow_official.getUserId(),
                "1",
                "守望先锋最好玩!",Constants.OFFICIAL_NEWS_PRESS,"守望先锋新英雄ash 真的好玩夜夜夜夜！",
        overwatch.getGameId(),null);

        Post sco_post = new Post(sc_official.getUserId(),
                "1",
                "星际争霸最好玩!",Constants.OFFICIAL_NEWS_PRESS,"我亲爱的指挥官，你又回来了！！",
                starcraft.getGameId(),null);

        Post hto_post = new Post(ht_official.getUserId(),
                "2",
                "炉石传说真你妈好玩！!",Constants.OFFICIAL_NEWS_PRESS,"炉石传说新酒馆战棋模式！！",
                hearthStone.getGameId(),null);

        Post wowo_post = new Post(wow_official.getUserId(),
                "1",
                "魔兽世界额最好玩!",Constants.OFFICIAL_NEWS_PRESS,"希尔瓦娜斯真饿好看！！",
                wow.getGameId(),null);

        postRepo.save(owo_post);
        postRepo.save(sco_post);
        postRepo.save(hto_post);
        postRepo.save(wowo_post);

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
        return "fuck";
    }


}
