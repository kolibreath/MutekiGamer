package com.guineap_pig_329.guinea_pig.controller;


import com.guineap_pig_329.guinea_pig.Constants;
import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.model.UserSession;
import com.guineap_pig_329.guinea_pig.repo.PostRepo;
import com.guineap_pig_329.guinea_pig.repo.ResponseRepo;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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


    //重新排序帖子的方法
    private List<Post> sortPost(List<Post> posts){
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return weight(o1) - weight(o2);
            }
        });
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
