package com.guineap_pig_329.guinea_pig.dao.wrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostWrapper {
    private int postId;
    private int userId;
    private int gameId;
    private int tag;
    private String time;
    private String content;
    private String title;
    //发帖人的用户信息
    private String userName;
    private String userAvatar;


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public PostWrapper(int postId, int userId, int gameId, int tag, long time, String content, String title, String userName, String userAvatar) {
        this.postId = postId;
        this.userId = userId;
        this.gameId = gameId;
        this.tag = tag;
        this.time = long2Date(time);
        this.content = content;
        this.title = title;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }

    private String long2Date(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
