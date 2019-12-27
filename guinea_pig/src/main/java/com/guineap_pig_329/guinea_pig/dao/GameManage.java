package com.guineap_pig_329.guinea_pig.dao;
/*
论坛管理信息表
 */
import javax.persistence.*;

@Entity
@Table(name = "game_manage")
public class GameManage {
    @Id
    @GeneratedValue
    private int gameManageId;

    @Column
    private int userId;

    @Column
    private int gameId;

    public int getGameManageId() {
        return gameManageId;
    }

    public void setGameManageId(int gameManageId) {
        this.gameManageId = gameManageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
