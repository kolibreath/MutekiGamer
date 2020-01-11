package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.repo.*;
import com.guineap_pig_329.guinea_pig.util.Util;
import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.PostWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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
    @Autowired
    private UserGameRepo userGameRepo;
    @Autowired
    private GameRepo gameRepo;

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
    public ResultBean getPostByGameId(@PathVariable("id") Integer gameId,HttpSession session) {
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        user.setSelectedGameId(gameId);
        List<Post> posts = postRepo.findAllByGameId(gameId);
        List<PostWrapper> postWrappers = new LinkedList<>();

        Game game = gameRepo.findById(gameId).get();

        for (Post post:posts){

            User postUser = userRepo.findUserByUserId(post.getUserId());
            PostWrapper postWrapper = new PostWrapper(post.getPostId(),postUser.getUserId(),game.getGameId(),
                    post.getTag(), post.getTime(), post.getContent(), post.getTitle(), postUser.getUserName() ,game.getPicture());
            postWrappers.add(postWrapper);
        }
        return ResultBean.success(postWrappers);
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
        int gameId = user.getSelectedGameId(), tag;
        String time;
        try {
            tag = filterByValue((String) map.get("tag"));
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


    @PostMapping("/hot")
    public ResultBean getHotPost(HttpSession session){
        UserSession userSession = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        List<Util.GameRank> gameRanks = Util.recommend(userSession.getId(),10,userGameRepo);

        List<Post> posts = new LinkedList<>();
        for(Util.GameRank gameRank : gameRanks) {
            List<Post> postCore = postRepo.findAllByGameId(gameRank.getGameId());
            posts.addAll(postCore);
        }

        Collections.shuffle(posts);
        return ResultBean.success(posts);
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
//        Response response = new Response(userId, postId, responseContent);

        UserInfo u=userInfoRepo.findUserInfoByUserId(userId);
        Response response=new Response(userId,postId,responseContent,user.getName(),u.getUserAvatar());
        if (responseContent == null) {
            return ResultBean.error(ResultBean.resources_not_found, "回复内容不能为空");
        }
        responseRepo.save(response);
        return ResultBean.success(null);
    }

//    进入页面默认显示的帖子
    @RequestMapping("/defult")
    public ResultBean getDefult(HttpSession session){
        UserSession userSession=(UserSession)session.getAttribute(Constants.USE_SESSION_KEY);
        int gameId=userSession.getGameId();
        List<Post> posts=postRepo.findAllByGameId(gameId);
        return ResultBean.success(Util.transform(posts,userRepo,userInfoRepo));
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
        long timeSubstract = 0;
        try {
            timeSubstract = Util.dateStr2long(post.getTime()) - currentTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return level * 10 + responseSize * 25 + (int) timeSubstract / 10000;
    }


    private int filterByValue(String tagStr){
        switch(tagStr){
            case "长期施工":
                return 1;
            case "感想感言":
                return 2;
            case "游戏攻略":
                return 3;
            case "游戏前瞻":
                return 4;
            case "官方快讯":
                return 5;
        }
        return -1;
    }
}
