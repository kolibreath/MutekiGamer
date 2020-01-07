package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

@Entity
@Table(name = "player_data")
public class PlayerData {

    @Id
    @GeneratedValue
    private int playerDataId;

    @Column
    private int playerId;

    @Column
    private String time;//比赛时间

    @Column
    private String result;//比赛结果

    @Column
    private String award;//获奖结果
    @Column
    private String intro;//介绍


    public PlayerData(int playerId, String time, String result, String award, String intro) {
        this.playerId = playerId;
        this.time = time;
        this.result = result;
        this.award = award;
        this.intro = intro;
    }
    public PlayerData(){

    }

    public int getPlayerDataId() {
        return playerDataId;
    }

    public void setPlayerDataId(int playerDataId) {
        this.playerDataId = playerDataId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
