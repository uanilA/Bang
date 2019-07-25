package com.bang.module.preference.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SelectedPreferenceSignle implements Serializable {
    @SerializedName("userSurveyStats")
    private List<SelectedPrefList> userSurveyStats = null;
    private final static long serialVersionUID = 3295273377530681526L;

    public List<SelectedPrefList> getUserSurveyStats() {
        return userSurveyStats;
    }

    public void setUserSurveyStats(List<SelectedPrefList> userSurveyStats) {
        this.userSurveyStats = userSurveyStats;
    }
}
