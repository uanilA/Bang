package com.bang.module.home.survey.model;

import java.io.Serializable;
import java.util.List;

public class SurveySentResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : Survey list retrive successfully
     * data : {"surveyList":[{"survey_id":20,"score":1,"for_user_id":2,"for_user_name":"Manish","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":24,"score":1,"for_user_id":2,"for_user_name":"Manish","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},{"survey_id":22,"score":2,"for_user_id":7,"for_user_name":"Arvind Patidar","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"}]}
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
             * survey_id : 20
             * score : 1
             * for_user_id : 2
             * for_user_name : Manish
             * badge_title : Unsatisfied
             * badge_image : http://localhost:3000/uploads/badge/unsatisfied.png
             */

            private int survey_id;
            private int score;
            private int for_user_id;
            private String for_user_name;
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

            public int getFor_user_id() {
                return for_user_id;
            }

            public void setFor_user_id(int for_user_id) {
                this.for_user_id = for_user_id;
            }

            public String getFor_user_name() {
                return for_user_name;
            }

            public void setFor_user_name(String for_user_name) {
                this.for_user_name = for_user_name;
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
