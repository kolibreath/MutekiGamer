package com.guineap_pig_329.guinea_pig.dao.wrapper;

import com.guineap_pig_329.guinea_pig.dao.Post;
import java.util.List;

public class GamePostWrapper {
    private int gameId;
    private String gameName;
    private String gameIntro;
    private String picture;

    private List<Post> myPost;

    public GamePostWrapper(int gameId, String gameName, String gameIntro, String picture, List<Post> myPost) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameIntro = gameIntro;
        this.picture = picture;
        this.myPost = myPost;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameIntro() {
        return gameIntro;
    }

    public void setGameIntro(String gameIntro) {
        this.gameIntro = gameIntro;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Post> getMyPost() {
        return myPost;
    }

    public void setMyPost(List<Post> myPost) {
        this.myPost = myPost;
    }
}
