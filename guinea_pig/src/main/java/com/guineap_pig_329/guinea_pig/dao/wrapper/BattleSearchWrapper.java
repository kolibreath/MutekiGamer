package com.guineap_pig_329.guinea_pig.dao.wrapper;

import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.Post;
import java.util.List;

public class BattleSearchWrapper {

    private List<Post> posts;
    private List<Game> games;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


    public BattleSearchWrapper(List<Post> posts, List<Game> games) {
        this.posts = posts;
        this.games = games;
    }
}
