package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.UserHomePageWrapper;
import com.guineap_pig_329.guinea_pig.dao.wrapper.UserWrapper;
import com.guineap_pig_329.guinea_pig.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 处理用户相关的请求
 * 用户请求个人页面 详细信息 好友信息等
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private FriendsRepo friendsRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GameAttributeRepo gameAttributeRepo;

    @RequestMapping("/follow")
    public ResultBean  follow(HttpSession session, @RequestBody Map<String,Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        int otherUserId;
        try {
            otherUserId = (int) map.get("otherUserId");
        }catch (Exception e){
            return ResultBean.error(ResultBean.internal_error,"服务器错误");
        }
        //todo code check
        if(friendsRepo.findByUserId1AndUserId2(userId,otherUserId)!= null)
            return ResultBean.error(ResultBean.internal_error,"重复创建好友关系");
        Friends friends = new Friends(userId, otherUserId);
        friendsRepo.save(friends);
        return ResultBean.success(null);
    }


    @RequestMapping("/unfollow")
    public ResultBean  unfollow(HttpSession session, @RequestBody Map<String,Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int otherUserId;
        try {
            otherUserId = (int) map.get("otherUserId");
        }catch (Exception e){
            return ResultBean.error(ResultBean.internal_error,"服务器错误");
        }
        Friends friend = friendsRepo.findByUserId1AndUserId2(user.getId(),otherUserId);
        friendsRepo.deleteById(friend.getFriendsId());
        return ResultBean.success(null);
    }

    /**
     * 返回用户的好友 游戏 关注 粉丝 等等信息
     * @param session
     * @return
     */

    @RequestMapping("/info")
    public ResultBean getUserInfo(HttpSession session){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();

        UserHomePageWrapper userHomePageWrapper = new UserHomePageWrapper();
        userHomePageWrapper.setUserName(user.getName());

        UserInfo userInfo = userInfoRepo.findUserInfoByUserId(userId);
        String userAvatar = userInfo.getUserAvatar();

        userHomePageWrapper.setUserAvatar(userAvatar);

        List<Game> games = new LinkedList<>();
        List<UserGame> user2Game =  userGameRepo.findAllByUserId(userId);

        for(UserGame userGame : user2Game){
            int gameId = userGame.getGameId();
            //如果存在
            Game game = gameRepo.findById(gameId).get();
            games.add(game);
        }

        userHomePageWrapper.setGames(games);

        //todo 返回内容确定 缩减
        List<Post> posts = postRepo.findAllByUserId(userId);
        userHomePageWrapper.setPosts(posts);
        userHomePageWrapper.setPostLength(posts.size());


        //找到所有的粉丝
        List<Friends> followed = friendsRepo.findFollowed(userId);
        //找到所有的关注者
        List<Friends> following = friendsRepo.findFollowing(userId);



        //所有的粉丝列表
        List<UserWrapper> followers = new LinkedList<>();
        //所有的关注者列表
        List<UserWrapper> followings = new LinkedList<>();

        for(Friends fd:followed){
            int fdId = fd.getUserId1();
            User fdUser = userRepo.findUserByUserId(fdId);
            UserInfo info  = userInfoRepo.findUserInfoByUserId(userId);
            UserWrapper userWrapper = new UserWrapper(user.getName(),
                    info.getUserAvatar(),
                    userId,
                    fdUser.getUserEmail(),
                    fdUser.getUserType());
            followers.add(userWrapper);
        }

        for(Friends fg:following){
            int fgId = fg.getUserId2();
            User fgUser = userRepo.findUserByUserId(fgId);
            UserInfo info  = userInfoRepo.findUserInfoByUserId(userId);
            UserWrapper userWrapper = new UserWrapper(user.getName(),
                    info.getUserAvatar(),
                    userId,
                    fgUser.getUserEmail(),
                    fgUser.getUserType());
            followings.add(userWrapper);
        }

        userHomePageWrapper.setFollowers(followers);
        userHomePageWrapper.setFollowing(followings);

        userHomePageWrapper.setFollowingNum(following.size());
        userHomePageWrapper.setFollowerNum(followers.size());

        ResultBean bean = new ResultBean();
        bean.setCode(200);
        bean.setData(userHomePageWrapper);
        bean.setMessage("成功请求");

        return bean;
    }

    @RequestMapping("/postUserInfo/{id}")
    public ResultBean getpostUserInfo(@PathVariable("id") int userId){
        User user=userRepo.findUserByUserId(userId);
        UserHomePageWrapper userHomePageWrapper = new UserHomePageWrapper();
        userHomePageWrapper.setUserName(user.getUserName());

        UserInfo userInfo = userInfoRepo.findUserInfoByUserId(userId);
        String userAvatar = userInfo.getUserAvatar();

        userHomePageWrapper.setUserAvatar(userAvatar);

        List<Game> games = new LinkedList<>();
        List<UserGame> user2Game =  userGameRepo.findAllByUserId(userId);

        for(UserGame userGame : user2Game){
            int gameId = userGame.getGameId();
            //如果存在
            Game game = gameRepo.findById(gameId).get();
            games.add(game);
        }

        //todo 返回内容确定 缩减
        List<Post> posts = postRepo.findAllByUserId(userId);
        userHomePageWrapper.setPosts(posts);
        userHomePageWrapper.setPostLength(posts.size());


        //找到所有的粉丝
        List<Friends> followed = friendsRepo.findFollowed(userId);
        //找到所有的关注者
        List<Friends> following = friendsRepo.findFollowing(userId);



        //所有的粉丝列表
        List<UserWrapper> followers = new LinkedList<>();
        //所有的关注者列表
        List<UserWrapper> followings = new LinkedList<>();

        for(Friends fd:followed){
            int fdId = fd.getUserId1();
            User fdUser = userRepo.findUserByUserId(fdId);
            UserInfo info  = userInfoRepo.findUserInfoByUserId(userId);
            UserWrapper userWrapper = new UserWrapper(user.getUserName(),
                    info.getUserAvatar(),
                    userId,
                    fdUser.getUserEmail(),
                    fdUser.getUserType());
            followers.add(userWrapper);
        }

        for(Friends fg:following){
            int fgId = fg.getUserId2();
            User fgUser = userRepo.findUserByUserId(fgId);
            UserInfo info  = userInfoRepo.findUserInfoByUserId(userId);
            UserWrapper userWrapper = new UserWrapper(user.getUserName(),
                    info.getUserAvatar(),
                    userId,
                    fgUser.getUserEmail(),
                    fgUser.getUserType());
            followings.add(userWrapper);
        }

        userHomePageWrapper.setFollowers(followers);
        userHomePageWrapper.setFollowing(followings);

        userHomePageWrapper.setFollowingNum(following.size());
        userHomePageWrapper.setFollowerNum(followers.size());

        ResultBean bean = new ResultBean();
        bean.setCode(200);
        bean.setData(userHomePageWrapper);
        bean.setMessage("成功请求");

        return bean;
    }


    @RequestMapping("/simple_info")
    private ResultBean getUserInfoSimple(HttpSession session){
        int userId = ((UserSession)session.getAttribute(Constants.USE_SESSION_KEY)).getId();
        UserInfo userInfo = userInfoRepo.findUserInfoByUserId(userId);

        return ResultBean.success(userInfo);
    }

    @RequestMapping("/alter_user_info")
    private ResultBean alterUserInfo(HttpSession httpSession,@RequestBody Map<String,Object> map){
        UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USE_SESSION_KEY);
        int userId = userSession.getId();

        String nickName ;
        String avatar;
        int sex ,age;
        String city,userOccupation,intro;

        try{

            nickName = (String) map.get("nickname");
            avatar = (String) map.get("avatar");
            sex = (int) map.get("sex");
            age = Integer.parseInt((String) map.get("age"));
            city = (String) map.get("city");
            userOccupation = (String) map.get("occupation");
            intro = (String) map.get("intro");
            User user = userRepo.findUserByUserId(userId);
            UserInfo userInfo = userInfoRepo.findUserInfoByUserId(userId);
            userInfo.setUserAvatar(avatar);
            user.setUserName(nickName);
            userInfo.setUserSex(sex);
            userInfo.setUserAge(age);
            userInfo.setUserCity(city);
            userInfo.setUserOccupation(userOccupation);
            userInfo.setUserIntro(intro);
            userRepo.save(user);
            userInfoRepo.save(userInfo);

            return ResultBean.success(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResultBean.error(ResultBean.bad_request,"解析异常");
        }
    }


    /**
     * 推荐算法
     * @param userId 用户名
     * @param n  返回的数量
     * @return
     */
    private List<GameRank> recommend(int userId,int n){

        String platforms[] = {"windows","macos","linux","phone","station"};
        int platformHash[] = new int[platforms.length];

        String divisions[] =  {"moba","rts","rpg","racing","horror","card","rogue-like","inde","fps",
        "arcade","shooting","ps"};
        int divisionHash[] = new int[divisions.length];

        int playerRange[] = {1,2,4,5,6,8};
        int playerRangeHash[] = new int[playerRange.length];

        int priceRange[] = {50,100,200,500};
        int priceRangeHash[] = new int[priceRange.length];

        List<UserGame> userGame =  userGameRepo.findAllByUserId(userId);
        // 遍历用户的游戏 生成一个object 和对应的得分
        for (UserGame game : userGame) {
            Game g = gameRepo.findById(game.getGameId()).get();
            GameAttribute gameAttribute = gameAttributeRepo.findByGameId(g.getGameId());

            String pStr[] = gameAttribute.getPlatform().split(" ");

            //遍历平台hash
            for (int i = 0; i < pStr.length ; i++) {
                for (int j = 0; j < platforms.length ; j++) {
                    if(platforms[j].equals(pStr[i].toLowerCase())){
                        divisionHash[i]++;
                    }
                }
            }

            String dStr[] = gameAttribute.getPaintingStyle().split(" ");
            //遍历风格分类hash
            for (int i = 0;i < dStr.length;i++){
                for (int j = 0; j <divisions.length ; j++) {
                    if(dStr[i].toLowerCase().equals(divisions[j])){
                        divisionHash[i]++;
                    }
                }
            }

            //遍历玩家人数
            int playerNumber = gameAttribute.getPlayerNumber();
            for (int i = 0; i < playerRange.length ; i++) {
                if(playerNumber <= playerRange[i])
                playerRangeHash[i]++;
            }

            //遍历价格区间
            int price = gameAttribute.getGamePrice();
            for (int i = 0; i < priceRange.length ; i++) {
                if(price <= priceRange[i]){
                    priceRangeHash[i]++;
                }
            }
        }


        List<String> platFormList = new LinkedList<>();
        List<String> divisionList = new LinkedList<>();
        List<Integer> playerRangeList = new LinkedList<>();
        List<Integer> gamePriceList = new LinkedList<>();

        platFormList = array2stringList(platforms,platformHash);
        divisionList = array2stringList(divisions,divisionHash);;

        playerRangeList = array2intList(playerRange,playerRangeHash);
        gamePriceList = array2intList(priceRange,priceRangeHash);

        String topPlatform = platFormList.get(0);
        String topDivision = divisionList.get(0);

        int topPlayerRange = playerRangeList.get(0);
        int topGamePrice = gamePriceList.get(0);

        Map<String,Integer> map = new TreeMap<>();
        List<Game> allGame = gameRepo.findAll();

        List<GameRank> sortedGame = new LinkedList<>();

        for(Game game:allGame){
            GameAttribute allGameAttribute = gameAttributeRepo.findByGameId(game.getGameId());

            String p[] = allGameAttribute.getPlatform().split(" ");
            List<String> allGamePlatforms = new LinkedList<>();

            for(String pStr: p){
                allGamePlatforms.add(pStr.toLowerCase());
            }

            int pRank = platFormList.size() - 1;
            for (int i = 0; i < platFormList.size() ; i++) {
                if(allGamePlatforms.contains(platFormList.get(i))){
                    pRank = i;
                    break;
                }
            }
            String d[]= allGameAttribute.getPaintingStyle().split(" ");
            List<String> allGameDivisions = new LinkedList<>();

            for(String dStr: d){
                allGameDivisions.add(dStr.toLowerCase());
            }

            int dRank = gamePriceList.size() - 1;
            for (int i = 0; i < gamePriceList.size() ; i++) {
                if(allGameDivisions.contains(gamePriceList.get(i))){
                    dRank = i;
                    break;
                }
            }

            //乡下匹配
            int allGamePlayerRangeMax = allGameAttribute.getPlayerNumber();
            int playerRank = playerRangeList.size()-1;
            for (int i = 0; i < playerRangeList.size() ; i++) {
                if(playerRangeList.get(i)<= allGamePlayerRangeMax){
                    playerRank =i;
                    break;
                }
            }

            int allGamePriceMax = allGameAttribute.getGamePrice();
            int priceRank = gamePriceList.size() - 1;
            for (int i = 0; i < gamePriceList.size() ; i++) {
                if(gamePriceList.get(i) <= allGamePriceMax){
                    priceRank = i;
                    break;
                }
            }

            int score = Integer.parseInt(pRank +"" + dRank +"" + playerRank +"" + priceRank);
            GameRank gameRank = new GameRank(game.getGameId(),score);
            sortedGame.add(gameRank);
        }

        Collections.sort(sortedGame, new Comparator<GameRank>() {
            @Override
            public int compare(GameRank o1, GameRank o2) {
                return (int) (o1.getRank() - o2.getRank());
            }
        });

        return sortedGame.size() > n ? sortedGame.subList(0,n) : sortedGame;
    }

    private List<String> array2stringList(String str[], int i[]){
        Map<String,Integer> map = new TreeMap<>();
        for (int j = 0; j < str.length ; j++) {
            map.put(str[j],i[j]);
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        List<String> result = new LinkedList<>();
        for(Map.Entry<String,Integer> mapping:list){
            result.add(mapping.getKey());
        }
        return result;
    }

    private List<Integer> array2intList(int str[],int i[]){
        Map<Integer, Integer> map = new TreeMap<>();
        for (int j = 0; j < str.length ; j++) {
            map.put(str[j],i[j]);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });


        List<Integer> result = new LinkedList<>();
        for(Map.Entry<Integer,Integer> mapping:list){
            result.add(mapping.getKey());
        }

        return result;
    }


    // 游戏评分 进行推荐排序的一句
    private class GameRank{
        private int gameId;
        private long rank;

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public long getRank() {
            return rank;
        }

        public void setRank(long rank) {
            this.rank = rank;
        }

        public GameRank(int gameId, long rank) {
            this.gameId = gameId;
            this.rank = rank;
        }
    }

}
