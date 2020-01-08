package com.guineap_pig_329.guinea_pig.dao.wrapper;

public class UserWrapper {

    private String userName;
    private String userAvatar;
    private int userId;
    private String userEmail;
    private int userType;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public UserWrapper(String userName, String userAvatar, int userId, String userEmail, int userType) {
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userType = userType;
    }
}
