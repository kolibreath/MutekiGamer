package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
战队信息
 */
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
    private String officialWeb;
    @Column
    private String avatar;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Team(int gameId, String teamName, String officialWeb, String avatar) {
        this.gameId = gameId;
        this.teamName = teamName;
        this.officialWeb = officialWeb;
        this.avatar = avatar;
    }

    public String getOfficialWeb() {
        return officialWeb;
    }

    public void setOfficialWeb(String officialWeb) {
        this.officialWeb = officialWeb;
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

}
