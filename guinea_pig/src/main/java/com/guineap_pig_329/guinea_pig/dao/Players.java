package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

@Entity
@Table(name="players")
public class Players {
    @Id
    @GeneratedValue
    private int playersId;

    @Column
    private int gameId;

    @Column
    private int teamId;

    @Column
    private String playerName;

    @Column
    private int dataId;//该队员的数据

    public Players(int gameId, int teamId, String playerName, int dataId) {
        this.gameId = gameId;
        this.teamId = teamId;
        this.playerName = playerName;
        this.dataId = dataId;
    }

    public Players(){

    }
    public int getPlayersId() {
        return playersId;
    }

    public void setPlayersId(int playersId) {
        this.playersId = playersId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
