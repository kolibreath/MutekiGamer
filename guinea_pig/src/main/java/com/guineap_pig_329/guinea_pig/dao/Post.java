package com.guineap_pig_329.guinea_pig.dao;

import org.springframework.lang.Nullable;

import javax.persistence.*;

/*
帖子表
 */
@Entity
@Table(name = "post")
public class    Post {

    @Id
    @GeneratedValue
    private int postId;

    @Column
    private int userId;

    @Column
    private long time;

    @Column
    private String content;

    @Column
    private int tag;

    @Column
    private String title;

    @Column
    private int gameId;

    @Nullable
    @Column
    private String link;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public Post(){

    }

    public Post(int userId, long time, String content, int tag, String title, int gameId) {
        this.userId = userId;
        this.time = time;
        this.content = content;
        this.tag = tag;
        this.title = title;
        this.gameId = gameId;
    }

    public Post(int userId, long time, String content, int tag, String title, int gameId, @Nullable String link) {
        this.userId = userId;
        this.time = time;
        this.content = content;
        this.tag = tag;
        this.title = title;
        this.gameId = gameId;
        this.link = link;
    }
}
