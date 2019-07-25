package com.bang.module.home.survey.model;

import java.util.List;

public class SurveyDetailModel {


    /**
     * code : 200
     * status : success
     * message : Survey details retrived successfully
     * data : {"surveyData":{"user_survey_id":"79","user_id":11,"user_name":"Dharmraj Acharya","user_profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"},"surveyCommentTotalCount":1,"surveyCommentList":[{"surveyCommentId":22,"comment":"Testing only","commented_by_user_id":11,"commented_by_user_name":"You","commented_by_user_profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg","commented_on":"2019-06-29T06:38:38.000Z"}]}
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
        /**
         * surveyData : {"user_survey_id":"79","user_id":11,"user_name":"Dharmraj Acharya","user_profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg","badge_title":"Unsatisfied","badge_image":"http://localhost:3000/uploads/badge/unsatisfied.png"}
         * surveyCommentTotalCount : 1
         * surveyCommentList : [{"surveyCommentId":22,"comment":"Testing only","commented_by_user_id":11,"commented_by_user_name":"You","commented_by_user_profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg","commented_on":"2019-06-29T06:38:38.000Z"}]
         */

        private SurveyDataBean surveyData;
        private int surveyCommentTotalCount;
        private List<SurveyCommentListBean> surveyCommentList;

        public SurveyDataBean getSurveyData() {
            return surveyData;
        }

        public void setSurveyData(SurveyDataBean surveyData) {
            this.surveyData = surveyData;
        }

        public int getSurveyCommentTotalCount() {
            return surveyCommentTotalCount;
        }

        public void setSurveyCommentTotalCount(int surveyCommentTotalCount) {
            this.surveyCommentTotalCount = surveyCommentTotalCount;
        }

        public List<SurveyCommentListBean> getSurveyCommentList() {
            return surveyCommentList;
        }

        public void setSurveyCommentList(List<SurveyCommentListBean> surveyCommentList) {
            this.surveyCommentList = surveyCommentList;
        }

        public static class SurveyDataBean {
            /**
             * user_survey_id : 79
             * user_id : 11
             * user_name : Dharmraj Acharya
             * user_profile_photo : http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg
             * badge_title : Unsatisfied
             * badge_image : http://localhost:3000/uploads/badge/unsatisfied.png
             */

            private String user_survey_id;
            private int user_id;
            private String user_name;
            private String user_profile_photo;
            private String badge_title;
            private String badge_image;

            public String getUser_survey_id() {
                return user_survey_id;
            }

            public void setUser_survey_id(String user_survey_id) {
                this.user_survey_id = user_survey_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_profile_photo() {
                return user_profile_photo;
            }

            public void setUser_profile_photo(String user_profile_photo) {
                this.user_profile_photo = user_profile_photo;
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

        public static class SurveyCommentListBean {
            /**
             * surveyCommentId : 22
             * comment : Testing only
             * commented_by_user_id : 11
             * commented_by_user_name : You
             * commented_by_user_profile_photo : http://34.236.130.86/uploads/profile/thumb/Image_1561788873100.jpg
             * commented_on : 2019-06-29T06:38:38.000Z
             */

            private int surveyCommentId;
            private String comment;
            private int commented_by_user_id;
            private String commented_by_user_name;
            private String commented_by_user_profile_photo;
            private String commented_on;

            public int getSurveyCommentId() {
                return surveyCommentId;
            }

            public void setSurveyCommentId(int surveyCommentId) {
                this.surveyCommentId = surveyCommentId;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getCommented_by_user_id() {
                return commented_by_user_id;
            }

            public void setCommented_by_user_id(int commented_by_user_id) {
                this.commented_by_user_id = commented_by_user_id;
            }

            public String getCommented_by_user_name() {
                return commented_by_user_name;
            }

            public void setCommented_by_user_name(String commented_by_user_name) {
                this.commented_by_user_name = commented_by_user_name;
            }

            public String getCommented_by_user_profile_photo() {
                return commented_by_user_profile_photo;
            }

            public void setCommented_by_user_profile_photo(String commented_by_user_profile_photo) {
                this.commented_by_user_profile_photo = commented_by_user_profile_photo;
            }

            public String getCommented_on() {
                return commented_on;
            }

            public void setCommented_on(String commented_on) {
                this.commented_on = commented_on;
            }
        }
    }
}
