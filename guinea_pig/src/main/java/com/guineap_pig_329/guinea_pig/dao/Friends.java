package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
好友表
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

    @Column
    private int relationship;
    /*
    0=user1关注user2
    1=user2关注user1
    2=互相关注
     */

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

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }
}
