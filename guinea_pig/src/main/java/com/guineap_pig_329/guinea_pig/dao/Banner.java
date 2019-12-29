package com.guineap_pig_329.guinea_pig.dao;


import javax.persistence.*;

@Entity
@Table(name ="banner")
public class Banner {

    @GeneratedValue
    @Id
    private int bannerId;

    @Column
    private String bannerPicId;

    @Column
    private String bannerContentId;

    @Column
    private long time;

    @Column
    private String bannerIntro;

    @Column
    private int gameId;

    public Banner(){}

    public Banner(String bannerPicId, String bannerContentId, long time, String bannerIntro, int gameId) {
        this.bannerPicId = bannerPicId;
        this.bannerContentId = bannerContentId;
        this.time = time;
        this.bannerIntro = bannerIntro;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBannerIntro() {
        return bannerIntro;
    }

    public void setBannerIntro(String bannerIntro) {
        this.bannerIntro = bannerIntro;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerPicId() {
        return bannerPicId;
    }

    public void setBannerPicId(String bannerPicId) {
        this.bannerPicId = bannerPicId;
    }

    public String getBannerContentId() {
        return bannerContentId;
    }

    public void setBannerContentId(String bannerContentId) {
        this.bannerContentId = bannerContentId;
    }


}
