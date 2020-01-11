package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue
    private int gameId;

    @Column
    private String gameName;

    @Lob
    @Column(length = 10000)
    private String gameIntro;

    @Lob
    @Column(length = 10000)
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameIntro() {
        return gameIntro;
    }

    public void setGameIntro(String gameIntro) {
        this.gameIntro = gameIntro;
    }


    public Game(){

    }

    public Game(String gameName, String gameIntro, String picture) {
        this.gameName = gameName;
        this.gameIntro = gameIntro;
        this.picture = picture;
    }
}
