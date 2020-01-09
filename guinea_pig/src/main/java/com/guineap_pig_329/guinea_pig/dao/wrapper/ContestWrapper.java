package com.guineap_pig_329.guinea_pig.dao.wrapper;

public class ContestWrapper {

    private int matchId;
    private String teamName1;
    private String teamName2;
    private String teamAvatar1;
    private String teamAvatar2;
    private String time;
    private String reviewLink;
    private int teamScore1;
    private int teamScore2;
    private int gameId;
    private int winnerId;
    private int status;


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

    public String getTeamAvatar1() {
        return teamAvatar1;
    }

    public void setTeamAvatar1(String teamAvatar1) {
        this.teamAvatar1 = teamAvatar1;
    }

    public String getTeamAvatar2() {
        return teamAvatar2;
    }

    public void setTeamAvatar2(String teamAvatar2) {
        this.teamAvatar2 = teamAvatar2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReviewLink() {
        return reviewLink;
    }

    public void setReviewLink(String reviewLink) {
        this.reviewLink = reviewLink;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContestWrapper(String teamName1, String teamName2, String teamAvatar1, String teamAvatar2, String time,
                          String reviewLink, int teamScore1, int teamScore2, int gameId, int winnerId, int status,
                          int matchId) {
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamAvatar1 = teamAvatar1;
        this.teamAvatar2 = teamAvatar2;
        this.time = time;
        this.reviewLink = reviewLink;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.gameId = gameId;
        this.winnerId = winnerId;
        this.status = status;
        this.matchId = matchId;
    }
}
