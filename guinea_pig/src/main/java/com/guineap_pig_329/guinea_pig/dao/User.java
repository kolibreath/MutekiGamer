package com.guineap_pig_329.guinea_pig.dao;


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
    @Column
    private String userPermission;
    @Column
    private String userEmail;

    public User() {

    }

    public User(String userName, String userPassword, int userType, String userPermission, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userPermission = userPermission;
        this.userEmail = userEmail;
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

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
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
