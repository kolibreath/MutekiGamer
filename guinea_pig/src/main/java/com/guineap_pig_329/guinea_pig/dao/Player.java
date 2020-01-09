package com.guineap_pig_329.guinea_pig.dao;


import javax.persistence.*;

@Entity
@Table(name = "player" )
public class Player{

    @Id
    @GeneratedValue
    private int playerId;
    //队员的背号
    @Column
    private int number;
    //队员对号
    @Column
    private String codeName;
    @Column
    private String realName;
    @Column
    private int position;
    @Column
    private int teamId;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column
    private String avatar;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public Player(){}

    public Player(int number, String codeName, String realName, int position, int teamId) {
        this.number = number;
        this.codeName = codeName;
        this.realName = realName;
        this.position = position;
        this.teamId = teamId;
    }
}
