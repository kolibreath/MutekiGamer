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

    @Lob
    @Column(length = 10000)
    private String gameBackground;

    @Column
    private int gamePrice;

    @Column
    private int playerNumber;//游戏人数

    @Column
    private int playerAge;//游戏玩家年龄限制

    @Column
    private String platform;

    @Column
    private String time;//游戏发行时间

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

    public String getGameBackground() {
        return gameBackground;
    }

    public void setGameBackground(String gameBackground) {
        this.gameBackground = gameBackground;
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


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GameAttribute(){

    }
}
