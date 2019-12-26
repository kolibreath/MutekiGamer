package com.guineap_pig_329.guinea_pig.dao;


import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    //组队邀请 陪练
    public static int INVATATION= 1;
    //前沿资讯
    public static int NEWS  =2;
    //游戏攻略
    public static int WALKTHROUGH = 3;
    //有感而发
    public static int THOUGHTS = 4;
    //长期施工
    public static int LONG_TERM = 5;
    //置顶内容
    public static int PLACE_TOP = 6;
    //Banner 内容
    public static int BANNER    = 7;

    @Id
    @GeneratedValue
    private int postId;

    @Column
    private long time;

    @Column
    private String title;

    @Column
    private String content;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    @Column
    private int part;

    @Column
    private String bannerUrl;

}

