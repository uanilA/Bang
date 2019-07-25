package com.bang.module.preference.model;

public class PreferenceModel {
    int id_;
    String image;

    public PreferenceModel(int id_, String image) {
        this.id_ = id_;
        this.image = image;
    }



    public String getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
