package com.guineap_pig_329.guinea_pig.dao.wrapper;

public class ResponseWrapper {
    private int responseId;
    private int userId;
    private int postId;
    private  String content;
    private String userName;
    private String userAvatar;

    public ResponseWrapper(int responseId, int userId, int postId, String content, String userName, String userAvatar) {
        this.responseId = responseId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.userName = userName;
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
}
