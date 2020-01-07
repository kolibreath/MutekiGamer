package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
官方账号作为特殊用户
 */
@Entity
@Table(name = "official")
public class Official {
    @Id
    @GeneratedValue
    private int officialId;

    @Column
    private int gameId;

    @Column
    private int userId;//对应用户表的哪个用户

    public Official(int gameId, int userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    public Official(){

    }
    public int getOfficialId() {
        return officialId;
    }

    public void setOfficialId(int officialId) {
        this.officialId = officialId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
