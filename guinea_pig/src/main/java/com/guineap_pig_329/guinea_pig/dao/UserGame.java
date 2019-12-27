package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
用户关注的游戏表
 */
@Entity
@Table(name = "user_game")
public class UserGame {

    @Id
    @GeneratedValue
    private int userGameId;

    @Column
    private int userId;

    @Column
    private int gameId;

    public int getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(int userGameId) {
        this.userGameId = userGameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
