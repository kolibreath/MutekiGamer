package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
用户游玩数据表
 */
@Entity
@Table(name = "game_data")
public class GameData {

    @Id
    @GeneratedValue
    private int gameDataId;

    @Column
    private int userId;

    @Column
    private  int gameId;

    @Column
    private  long gameLength;

    @Column
    private int score;

    public int getGameDataId() {
        return gameDataId;
    }

    public void setGameDataId(int gameDataId) {
        this.gameDataId = gameDataId;
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

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GameData(){

    }
}
