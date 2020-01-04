package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.Response;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.dao.UserSession;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import com.guineap_pig_329.guinea_pig.repo.ResponseRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


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

    @RequestMapping("/selected")
    public List<Post> getPosts(HttpSession session){
        UserSession user  = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId  = user.getId();
        List<Post> posts = postRepo.findAllByUserId(userId);
        return sortPost(posts);
    }

    /**
     * 获取用户的关注的某个游戏的游戏帖子列表
     * @param gameId
     * @return
     */
    @RequestMapping("/user/{id}")
    public List<Post> getPostByGameId(@PathVariable("id") Integer gameId){
        List<Post> posts  = postRepo.findAllByGameId(gameId);
        return posts;
        //todo 排序方式
    }

    @PostMapping(value = "/new_post")
    //成功 200 错误 返回 500
    //从当前的userSession中取出
    public int newPost(HttpSession session, @RequestBody Map<String,Object> map){
        UserSession user  = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        String postContent = (String) map.get("postContent");
        String postTitle = (String) map.get("postTitle");
        int tag , gameId;
        long time ;
        try {
            tag = Integer.parseInt((String) map.get("tag"));
            gameId = Integer.parseInt((String) map.get("gameId"));
            time = (long) map.get("time");
        }catch (Exception e){
            return 500;
        }
        if(postContent == null || postTitle == null)
            return 400;
        Post post = new Post(
                userId,time,postContent,tag,postTitle,gameId
        );
        postRepo.save(post);
        return 200;
    }

    @PostMapping("/new_response")
    public int newResponse(HttpSession session, @RequestBody Map<String, Object> map){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        int userId = user.getId();
        String responseContent =(String) map.get("responseContent");
        int postId;
        try {
            postId = (int) map.get("postId");
        }catch (Exception e){
            return 500;
        }
        Response response = new Response(userId, postId, responseContent);
        if(responseContent == null){
            return 400;
        }
            responseRepo.save(response);
        return 200;
    }

    @PostMapping("/delete_post")
    public int deletePost(@RequestBody Map<String,Object> map)
    {
        int postId;
        try {
            postId = Integer.parseInt((String) map.get("postId"));
        }catch (Exception e){
            return 500;
        }
       postRepo.deleteById(postId);
        return 200;
    }

    @PostMapping("/filter/{tag}")
    public ResultBean filterByTag(@PathVariable("tag") int tag){
        List<Post> filteredPost ;
        try{
            filteredPost = postRepo.findByTag(tag);
        }catch (Exception e){
            return ResultBean.error(ResultBean.internal_error,"没有帖子内容");
        }
        return ResultBean.success(filteredPost);
    }


    //重新排序帖子的方法
    private List<Post> sortPost(List<Post> posts){
        Collections.sort(posts, Comparator.comparingInt(this::weight));
        return posts;
    }

    private int weight(Post post){
        User user = userRepo.findUserByUserId(post.getUserId());
        int level = user.getLevel();
        int responseSize = responseRepo.findAllByPostId(post.getPostId()).size();
        long currentTime = System.currentTimeMillis();
        long timeSubstract = post.getTime() - currentTime;
        int weight = level * 10+ responseSize * 25 + (int)timeSubstract/10000;
        return weight;
    }
}
