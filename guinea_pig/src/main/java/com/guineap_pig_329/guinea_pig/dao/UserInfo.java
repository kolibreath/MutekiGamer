package com.guineap_pig_329.guinea_pig.dao;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue
    private int userInfoId;

    @Column
    private int userId;

    @Column
    private String userAvatar;

    @Column
    @Nullable
    private String userIntro;

    @Column
    private int userSex;

    @Column
    @Nullable

    private String userCity;

    @Column
    private int userAge;



    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public UserInfo() {

    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public UserInfo(int userId, String userAvatar, String userIntro, int userSex, String userCity, int userAge) {
        this.userId = userId;
        this.userAvatar = userAvatar;
        this.userIntro = userIntro;
        this.userSex = userSex;
        this.userCity = userCity;
        this.userAge = userAge;
    }
}
