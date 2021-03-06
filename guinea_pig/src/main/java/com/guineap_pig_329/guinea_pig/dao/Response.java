package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
帖子回复情况表
 */
@Entity
@Table(name = "response")
public class Response {

    @Id
    @GeneratedValue
    private int responseId;
    @Column
    private int userId;
    @Column
    private int postId;
    @Column
    private  String content;
    @Column
    private String userName;
    @Column
    private String userAvatar;

    public Response(int userId, int postId, String content, String userName, String userAvatar) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.userName = userName;
        this.userAvatar = userAvatar;
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

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Response(){

    }

    public Response(int userId, int postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
