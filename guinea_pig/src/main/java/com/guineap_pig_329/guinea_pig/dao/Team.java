package com.guineap_pig_329.guinea_pig.dao;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue
    private int teamId;

    @Column
    private int gameId;

    @Column
    private String teamName;

    @Column
    private int teamInfoId;

    public Team(int gameId, String teamName, int teamInfoId) {
        this.gameId = gameId;
        this.teamName = teamName;
        this.teamInfoId = teamInfoId;
    }

    public Team(){

    }
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamInfoId() {
        return teamInfoId;
    }

    public void setTeamInfoId(int teamInfoId) {
        this.teamInfoId = teamInfoId;
    }
}
