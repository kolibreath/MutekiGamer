package com.guineap_pig_329.guinea_pig.dao;
/*
游戏属性表
 */
import javax.persistence.*;

@Entity
@Table(name = "game_attribute")
public class GameAttribute {
    @Id
    @GeneratedValue
    private int gameAttributeId;

    @Column
    private int gameId;

    @Column
    private String paintingStyle;//游戏画风

    @Column
    private String gameBackgroud;

    @Column
    private int gamePrice;

    @Column
    private int playerNumber;//游戏人数

    @Column
    private int playerAge;//游戏玩家年龄限制

    @Column
    private String platform;

    @Column
    private long time;//游戏发行时间

    public int getGameAttributeId() {
        return gameAttributeId;
    }

    public void setGameAttributeId(int gameAttributeId) {
        this.gameAttributeId = gameAttributeId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPaintingStyle() {
        return paintingStyle;
    }

    public void setPaintingStyle(String paintingStyle) {
        this.paintingStyle = paintingStyle;
    }

    public String getGameBackgroud() {
        return gameBackgroud;
    }

    public void setGameBackgroud(String gameBackgroud) {
        this.gameBackgroud = gameBackgroud;
    }

    public int getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(int gamePrice) {
        this.gamePrice = gamePrice;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public GameAttribute(){

    }
}
