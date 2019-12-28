package com.guineap_pig_329.guinea_pig.dao;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue
    private int gameId;

    @Column
    private String gameName;

    @Column
    private String gameIntro;

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

    public Game(String gameName, String gameIntro) {
        this.gameName = gameName;
        this.gameIntro = gameIntro;
    }
}
