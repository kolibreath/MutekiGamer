package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/**
好友表 rick 关注了 morty  那样就是 rick 为 userId1 morty 为 userId2
 */
@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue
    private int friendsId;

    @Column
    private int userId1;

    @Column
    private int userId2;

    public int getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(int friendsId) {
        this.friendsId = friendsId;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public Friends(){

    }

    public Friends(int userId1, int userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}
