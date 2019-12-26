package com.guineap_pig_329.guinea_pig.dao;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String username;
    @Column
    private String userEmail;
    @Column
    private int userType;
    @Column
    private int userInfoId;
    @Column
    private String managementElement;

    //默认情况下会设置成Personal
    public static int OFFICIAL ;
    public static int PERSONAL;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setManagementElement(String managementElement){
        this. managementElement = managementElement;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getManagementElement(){
        return managementElement;
    }

    public User(){}
    public User(String username, String userEmail, int userType, int userInfoId, String managementElement) {
        this.username = username;
        this.userEmail = userEmail;
        this.userType = userType;
        this.userInfoId = userInfoId;
        this.managementElement = managementElement;
    }

}
