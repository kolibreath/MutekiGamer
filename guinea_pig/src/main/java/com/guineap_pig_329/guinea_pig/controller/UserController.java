package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.UserHomePageWrapper;
import com.guineap_pig_329.guinea_pig.dao.wrapper.UserWrapper;
import com.guineap_pig_329.guinea_pig.repo.*;
import com.guineap_pig_329.guinea_pig.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        userHomePageWrapper.setCity(userInfo.getUserCity());
        userHomePageWrapper.setAge(userInfo.getUserAge());
//        userHomePageWrapper.setAge();

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


    @RequestMapping("/test_al")
    public ResultBean test(HttpSession httpSession){
        UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USE_SESSION_KEY);
        return ResultBean.success(Util.recommend(userSession.getId(),10,userGameRepo));
    }


}
