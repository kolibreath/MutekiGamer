package com.guineap_pig_329.guinea_pig.dao;

import org.omg.CORBA.PRIVATE_MEMBER;

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
}
