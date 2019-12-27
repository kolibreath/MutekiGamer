package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

@Entity
@Table(name = "recent_matches")
public class RecentMatches {
    @Id
    @GeneratedValue
    private int matchId;

    @Column
    private int userId;

    @Column
    private int gameId;

    @Column
    private int number;//发起匹配的玩家数量

    @Column
    private String array;//玩家构成的组

    @Column
    private int result;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getArray() {
        return array;
    }

    public void setArray(String array) {
        this.array = array;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
