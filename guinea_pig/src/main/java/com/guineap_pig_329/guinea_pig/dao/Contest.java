package com.guineap_pig_329.guinea_pig.dao;

/*
战队比赛情况表
 */

import javax.persistence.*;

@Table(name = "contest")
@Entity
public class Contest {

    @GeneratedValue
    @Id
    private int matchId;

    @Column
    private String teamName1;

    @Column
    private String teamName2;

    @Column
    private int teamScore1;

    @Column
    private int teamScore2;

    @Column
    private String recordReviewLink;

    @Column
    private int status;

    @Column
    private String time;

    @Column
    private int gameId;

    @Column
    private int winnerId;

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public int getTeamScore1() {
        return teamScore1;
    }

    public void setTeamScore1(int teamScore1) {
        this.teamScore1 = teamScore1;
    }

    public int getTeamScore2() {
        return teamScore2;
    }

    public void setTeamScore2(int teamScore2) {
        this.teamScore2 = teamScore2;
    }

    public String getRecordReviewLink() {
        return recordReviewLink;
    }

    public void setRecordReviewLink(String recordReviewLink) {
        this.recordReviewLink = recordReviewLink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Contest(){}

    public Contest(String teamName1, String teamName2, int teamScore1, int teamScore2, String recordReviewLink, int status, String time, int gameId, int winnerId) {
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.recordReviewLink = recordReviewLink;
        this.status = status;
        this.time = time;
        this.gameId = gameId;
        this.winnerId = winnerId;
    }
}
