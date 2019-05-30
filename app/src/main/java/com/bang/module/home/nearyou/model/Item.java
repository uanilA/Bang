package com.bang.module.home.nearyou.model;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Item {

    private final int id;
    private final String name;
    private final String distance;
    private final String followers;
    private final int image;

    public Item(int id, String name, String distance, String followers, int image) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.followers = followers;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getFollowers() {
        return followers;
    }

    public int getImage() {
        return image;
    }
}
