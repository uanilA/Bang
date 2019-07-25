package com.bang.module.preference.model;

public class ParentPreferenceModel {
    String name;
    String questions;


    public ParentPreferenceModel(String name, String questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
