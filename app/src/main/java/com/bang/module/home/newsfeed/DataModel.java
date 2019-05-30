package com.bang.module.home.newsfeed;

public class DataModel {
    String name;
    String likes;
    int id_;
    int image;

    public DataModel(String name, String likes, int id_, int image) {
        this.name = name;
        this.likes = likes;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return likes;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}