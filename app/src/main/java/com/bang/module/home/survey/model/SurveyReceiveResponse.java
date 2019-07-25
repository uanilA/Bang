package com.bang.module.home.survey.model;

import java.io.Serializable;
import java.util.List;

public class SurveyReceiveResponse implements Serializable {


    /**
     * code : 200
     * status : success
     * message : Survey list retrive successfully
     * data : {"surveyList":[{"survey_id":19,"score":3,"by_user_id":2,"by_user_name":"Manish","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":23,"score":4,"by_user_id":3,"by_user_name":"Testing user","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":27,"score":1,"by_user_id":3,"by_user_name":"Testing user","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":28,"score":0,"by_user_id":3,"by_user_name":"Testing user","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":29,"score":0,"by_user_id":3,"by_user_name":"Testing user","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":21,"score":4,"by_user_id":4,"by_user_name":"rahul","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"}]}
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
        private List<SurveyListBean> surveyList;

        public List<SurveyListBean> getSurveyList() {
            return surveyList;
        }

        public void setSurveyList(List<SurveyListBean> surveyList) {
            this.surveyList = surveyList;
        }

        public static class SurveyListBean {
            /**
             * survey_id : 19
             * score : 3
             * by_user_id : 2
             * by_user_name : Manish
             * badge_title : Unsatisfied
             * badge_image : http://localhost:3000/uploads/badge/unsatisfied.png
             */

            private int survey_id;
            private int score;
            private int by_user_id;
            private String by_user_name;
            private String badge_title;
            private String badge_image;

            public int getSurvey_id() {
                return survey_id;
            }

            public void setSurvey_id(int survey_id) {
                this.survey_id = survey_id;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getBy_user_id() {
                return by_user_id;
            }

            public void setBy_user_id(int by_user_id) {
                this.by_user_id = by_user_id;
            }

            public String getBy_user_name() {
                return by_user_name;
            }

            public void setBy_user_name(String by_user_name) {
                this.by_user_name = by_user_name;
            }

            public String getBadge_title() {
                return badge_title;
            }

            public void setBadge_title(String badge_title) {
                this.badge_title = badge_title;
            }

            public String getBadge_image() {
                return badge_image;
            }

            public void setBadge_image(String badge_image) {
                this.badge_image = badge_image;
            }
        }
    }
}
