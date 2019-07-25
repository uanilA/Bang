package com.bang.module.preference.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionList implements Serializable {
    @SerializedName("questionId")
    private Integer questionId;
    @SerializedName("questionTitle")
    private String questionTitle;
    @SerializedName("questionDescription")
    private String questionDescription;
    @SerializedName("option")
    private List<OptionList> option = null;
    private final static long serialVersionUID = 5507147269822375094L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public List<OptionList> getOption() {
        return option;
    }

    public void setOption(List<OptionList> option) {
        this.option = option;
    }
}
