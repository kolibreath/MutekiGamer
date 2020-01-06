package com.guineap_pig_329.guinea_pig.dao;

/**
 *  关注或者取消关注传递的内容
 */
public class UserId {

    private int otherUserId;

    public int getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }

    public UserId(){}

    public UserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }
}
