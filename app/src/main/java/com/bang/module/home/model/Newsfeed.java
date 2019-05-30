package com.bang.module.home.model;

public class Newsfeed {
    public String newsFeedName;
    public String userName;
    public String likeCount;
    public String postDate;


    public Newsfeed(String newsFeedName, String userName, String likeCount, String postDate) {
        this.newsFeedName = newsFeedName;
        this.userName = userName;
        this.likeCount = likeCount;
        this.postDate = postDate;
    }
}
