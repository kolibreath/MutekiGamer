package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

@Entity
@Table(name = "team_data")
public class TeamData {
    @Id
    @GeneratedValue
    private int teamDataId;

    @Column
    private int teamId;

    @Column
    private int gameId;

    @Column
    private String time;//比赛时间

    @Column
    private String result;//比赛结果

    @Column
    private String award;//获奖情况

    public TeamData(int teamId, int gameId, String time, String result, String award) {
        this.teamId = teamId;
        this.gameId = gameId;
        this.time = time;
        this.result = result;
        this.award = award;
    }

    public TeamData(){

    }
    public int getTeamDataId() {
        return teamDataId;
    }

    public void setTeamDataId(int teamDataId) {
        this.teamDataId = teamDataId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
