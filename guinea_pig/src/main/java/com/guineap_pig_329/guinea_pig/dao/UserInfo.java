package com.guineap_pig_329.guinea_pig.dao;


import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue
    private int userInfoId;
    @Column(length = 255)
    private String userIntro;
    @Column
    //序列化完成
    private String statics;
    @Column
    private String avatar;
    @Column
    private int level;

    public UserInfo(String userIntro, String statics, String avatar, int level) {
        this.userIntro = userIntro;
        this.statics = statics;
        this.avatar = avatar;
        this.level = level;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public String getStatics() {
        return statics;
    }

    public void setStatics(String statics) {
        this.statics = statics;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public static class Statics{

        private String name ;
        private String season;
        private int score;
        private int rank;

        public Statics(String name, String season, int score, int rank) {
            this.name = name;
            this.season = season;
            this.score = score;
            this.rank = rank;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
