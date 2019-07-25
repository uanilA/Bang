package com.bang.module.preference.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedPrefList implements Serializable {
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("total_question")
    private Integer totalQuestion;
    @SerializedName("total_answered")
    private Integer totalAnswered;
    private final static long serialVersionUID = 4637777444495389401L;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public Integer getTotalAnswered() {
        return totalAnswered;
    }

    public void setTotalAnswered(Integer totalAnswered) {
        this.totalAnswered = totalAnswered;
    }
}
