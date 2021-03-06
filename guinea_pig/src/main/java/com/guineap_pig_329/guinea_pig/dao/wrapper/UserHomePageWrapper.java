package com.guineap_pig_329.guinea_pig.dao.wrapper;

import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.Post;

import java.util.List;

/**
 * 用户关注的主页的信息
 */
public class UserHomePageWrapper {
    private String userName;
    private int age;
    private String city;
    private String occupation;
    private String degree;
    private int followerNum;
    private int postLength;
    private int followingNum;
    private String userAvatar;
    private String intro;
    public UserHomePageWrapper(){}
    private List<Game> games;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUserName() {
        return userName;
    }
    private List<Post> posts;
    private List<UserWrapper> followers;
    private List<UserWrapper> following;

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getPostLength() {
        return postLength;
    }

    public void setPostLength(int postLength) {
        this.postLength = postLength;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<UserWrapper> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserWrapper> followers) {
        this.followers = followers;
    }

    public List<UserWrapper> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserWrapper> following) {
        this.following = following;
    }

    public int getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }
}
