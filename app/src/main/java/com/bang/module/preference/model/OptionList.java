package com.bang.module.preference.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OptionList implements Serializable {
    @SerializedName("optionId")
    private Integer optionId;
    @SerializedName("optionPhotos")
    private String optionPhotos;
    @SerializedName("questionId")
    private Integer questionId;
    @SerializedName("isSelected")
    private String isSelected;
    private final static long serialVersionUID = 8378576211287835557L;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionPhotos() {
        return optionPhotos;
    }

    public void setOptionPhotos(String optionPhotos) {
        this.optionPhotos = optionPhotos;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
