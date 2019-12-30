package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Friends;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.FriendsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 处理用户相关的请求
 * 用户请求个人页面 详细信息 好友信息等
 */

@RestController("/user")
public class UserController {

    @Autowired
    private FriendsRepo friendsRepo;

    /**
     * @param session
     * @param map
     * @return 关注成功 返回 200 关注失败返回 400 发生异常返回 500
     */
    @RequestMapping("/follow")
    public int  follow(HttpSession session, Map<String,Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        int otherUserId;
        try {
            otherUserId = (int) map.get("otherUserId");
        }catch (Exception e){
            return 500;
        }
        Friends friends = new Friends(userId, otherUserId, 0);
        friendsRepo.save(friends);
        return 200;
    }


    @RequestMapping("/unfollow")
    public int  unfollow(HttpSession session, Map<String,Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int otherUserId;
        try {
            otherUserId = (int) map.get("otherUserId");
        }catch (Exception e){
            return 500;
        }
        //todo test here
        Friends friend = friendsRepo.findByUserId1AndUserId2(user.getId(),otherUserId);
        friendsRepo.deleteById(friend.getFriendsId());
        return 200;
    }
    @RequestMapping("/myname")
    public String myname(HttpSession httpSession, Map<String,Object> map){
        UserSession user=(UserSession)httpSession.getAttribute(Constants.USE_SESSION_KEY);
        return user.getName();
    }

}
