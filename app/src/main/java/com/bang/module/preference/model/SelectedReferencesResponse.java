package com.bang.module.preference.model;

import java.io.Serializable;
import java.util.List;

public class SelectedReferencesResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : User Preferences Stats retrieved successfully
     * data : {"userSurveyStats":[{"gender":0,"total_question":6,"total_answered":0},{"gender":1,"total_question":6,"total_answered":6},{"gender":2,"total_question":0,"total_answered":0},{"gender":3,"total_question":0,"total_answered":0},{"gender":4,"total_question":0,"total_answered":0}]}
     */

    private int code;
    private String status;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UserSurveyStatsBean> userSurveyStats;

        public List<UserSurveyStatsBean> getUserSurveyStats() {
            return userSurveyStats;
        }

        public void setUserSurveyStats(List<UserSurveyStatsBean> userSurveyStats) {
            this.userSurveyStats = userSurveyStats;
        }

        public static class UserSurveyStatsBean {
            /**
             * gender : 0
             * total_question : 6
             * total_answered : 0
             */

            private int gender;
            private int total_question;
            private int total_answered;

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getTotal_question() {
                return total_question;
            }

            public void setTotal_question(int total_question) {
                this.total_question = total_question;
            }

            public int getTotal_answered() {
                return total_answered;
            }

            public void setTotal_answered(int total_answered) {
                this.total_answered = total_answered;
            }
        }
    }
}
