package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
聊天表
 */
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue
    private int chatId;

    @Column
    private int userId1;

    @Column
    private int userId2;

    @Column
    private int from;//标识是哪个用户发的消息

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
