package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * @param session
     * @param map
     * @return 关注成功 返回 200 关注失败返回 400 发生异常返回 500
     */
    @RequestMapping("/follow")
    public ResultBean  follow(HttpSession session, Map<String,Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        int otherUserId;
        try {
            otherUserId = (int) map.get("otherUserId");
        }catch (Exception e){
            return ResultBean.error(ResultBean.internal_error,"服务器错误");
        }
        //todo code check
        Friends friends = new Friends(userId, otherUserId);
        friendsRepo.save(friends);
        return ResultBean.success(null);
    }


    @RequestMapping("/unfollow")
    public ResultBean  unfollow(HttpSession session, Map<String,Object> map){
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

        UserHomePage userHomePage = new UserHomePage();
        userHomePage.setUserName(user.getName());

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
        userHomePage.setPosts(posts);

        //找到所有的粉丝
        List<Friends> followed = friendsRepo.findFollowed(userId);
        //找到所有的关注者
        List<Friends> following = friendsRepo.findFollowing(userId);

        userHomePage.setFollowingNum(following.size());
        userHomePage.setFollowerNum(following.size());

        //所有的粉丝列表
        List<User> followers = new LinkedList<>();
        //所有的关注者列表
        List<User> followings = new LinkedList<>();

        for(Friends fd:followed){
            int fdId = fd.getUserId1();
            User fdUser = userRepo.findUserByUserId(fdId);
            followers.add(fdUser);
        }

        for(Friends fg:following){
            int fgId = fg.getUserId2();
            User fgUser = userRepo.findUserByUserId(fgId);
            followings.add(fgUser);
        }

        userHomePage.setFollowers(followers);
        userHomePage.setFollowing(followings);

        ResultBean bean = new ResultBean();
        bean.setCode(200);
        bean.setData(userHomePage);
        bean.setMessage("成功请求");

        return bean;
    }

}
