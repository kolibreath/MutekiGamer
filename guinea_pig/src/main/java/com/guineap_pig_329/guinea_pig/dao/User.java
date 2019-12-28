package com.guineap_pig_329.guinea_pig.dao;


import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String userName;
    @Column
    private String userPassword;
    @Column
    private int userType;
    @Nullable
    @Column
    private String userEmail;
    @Column
    private int level;



    public User() {

    }

    public User(String userName, String userPassword, int userType, String userEmail, int level) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userEmail = userEmail;
        this.level = level;
    }

    public User(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
