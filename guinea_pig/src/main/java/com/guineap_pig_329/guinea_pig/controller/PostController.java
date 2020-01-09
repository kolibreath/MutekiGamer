package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.Util;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.PostWrapper;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import com.guineap_pig_329.guinea_pig.repo.ResponseRepo;
import com.guineap_pig_329.guinea_pig.repo.UserInfoRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 发帖 删帖 回复帖子 等
 */
@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ResponseRepo responseRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;

    @RequestMapping("/selected")
    public ResultBean getPosts() {
        List<Post> posts = postRepo.findAll();
        posts = sortPost(posts);
        return ResultBean.success(Util.transform(posts,userRepo,userInfoRepo));
    }

    @RequestMapping("/postDetail")
    public ResultBean getpostDetail(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int postId=userSession.getPostId();
        List<Post> posts=postRepo.findAllByPostId(postId);
        return ResultBean.success(Util.transform(posts,userRepo,userInfoRepo));
    }
    /**
     * 获取用户的关注的某个游戏的游戏帖子列表
     *
     * @param gameId
     * @return
     */
    @RequestMapping("/user/{id}")
    public ResultBean getPostByGameId(@PathVariable("id") Integer gameId) {
        List<Post> posts = postRepo.findAllByGameId(gameId);
        return ResultBean.success(Util.transform(posts,userRepo,userInfoRepo));
    }
    //通过游戏Id 用户id得到帖子
    @RequestMapping("/usergame/{gameid}")
    public ResultBean getPostByUserIdGameId(@PathVariable("gameid") Integer gameId,HttpSession session) {
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        List<Post> posts = postRepo.findAllByGameIdAndUserId(userId,gameId);
        return ResultBean.success(Util.transform(posts,userRepo,userInfoRepo));
    }

    @PostMapping(value = "/new_post")
    //成功 200 错误 返回 500
    //从当前的userSession中取出
    public ResultBean newPost(HttpSession session,
                              @RequestBody Map<String, Object> map) {
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        String postContent = (String) map.get("postContent");
        String postTitle = (String) map.get("postTitle");
        int tag, gameId;
        String time;
        try {
            tag = (int) map.get("tag");
            gameId = (int) map.get("gameId");
            time = (String) map.get("time");
        } catch (Exception e) {
            return ResultBean.error(ResultBean.bad_request, "内容解析无效");
        }
        if (postContent == null || postTitle == null)
            return ResultBean.error(ResultBean.resources_not_found, "文章内容和标题不能为空");
        Post post = new Post(
                userId, time, postContent, tag, postTitle, gameId
        );
        postRepo.save(post);
        return ResultBean.success(null);
    }

    @PostMapping("/new_response")
    public ResultBean newResponse(HttpSession session, @RequestBody Map<String, Object> map) {
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        String responseContent = (String) map.get("responseContent");
        int postId;
        try {
            postId = (int) map.get("postId");
        } catch (Exception e) {
            return ResultBean.error(ResultBean.bad_request, "帖子不存在");
        }
        Response response = new Response(userId, postId, responseContent);
        if (responseContent == null) {
            return ResultBean.error(ResultBean.resources_not_found, "回复内容不能为空");
        }
        responseRepo.save(response);
        return ResultBean.success(null);
    }

    @RequestMapping("/getResponse/{postId}")
    public ResultBean getResponse(@PathVariable("postId") int postId){
        List<Response> responses=responseRepo.findAllByPostId(postId);
        return ResultBean.success(responses);
    }
    @PostMapping("/delete_post")
    public ResultBean deletePost(@RequestBody Map<String, Object> map) {
        int postId;
        try {
            postId = (int) map.get("postId");
        } catch (Exception e) {
            return ResultBean.error(ResultBean.resources_not_found, "帖子不存在");
        }
        try {
            postRepo.deleteById(postId);
        } catch (Exception e) {
            return ResultBean.error(ResultBean.bad_request, "不能重复删除帖子");
        }
        return ResultBean.success(null);
    }

    @PostMapping("/filter/{tag}")
    public ResultBean filterByTag(@PathVariable("tag") int tag) {
        List<PostWrapper> filteredPost;
        try {
            filteredPost = Util.transform(postRepo.findByTag(tag),userRepo,userInfoRepo);
        } catch (Exception e) {
            return ResultBean.error(ResultBean.internal_error, "没有帖子内容");
        }
        return ResultBean.success(filteredPost);
    }


    //重新排序帖子的方法
    //todo 根据用户的喜好进行推荐
    private List<Post> sortPost(List<Post> posts) {
        Collections.sort(posts, Comparator.comparingInt(this::weight));
        return posts;
    }

    private int weight(Post post) {
        User user = userRepo.findUserByUserId(post.getUserId());
        int level = user.getLevel();
        int responseSize = responseRepo.findAllByPostId(post.getPostId()).size();
        long currentTime = System.currentTimeMillis();
//        long timeSubstract = post.getTime() - currentTime;
        long timeSubstract = 0;
        int weight = level * 10 + responseSize * 25 + (int) timeSubstract / 10000;
        return weight;
    }

}
