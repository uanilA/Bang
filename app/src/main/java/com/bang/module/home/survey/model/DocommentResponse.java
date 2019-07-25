package com.bang.module.home.survey.model;

import java.io.Serializable;

public class DocommentResponse implements Serializable {


    /**
     * code : 200
     * status : success
     * message : Comment posted successfully.
     * data : {"comment_data":{"survey_comment_id":98,"commented_by_user_name":"You","comment":"Testing only","commented_on":"2019-06-29T10:34:39.000Z"}}
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
         * comment_data : {"survey_comment_id":98,"commented_by_user_name":"You","comment":"Testing only","commented_on":"2019-06-29T10:34:39.000Z"}
         */

        private CommentDataBean comment_data;

        public CommentDataBean getComment_data() {
            return comment_data;
        }

        public void setComment_data(CommentDataBean comment_data) {
            this.comment_data = comment_data;
        }

        public static class CommentDataBean {
            /**
             * survey_comment_id : 98
             * commented_by_user_name : You
             * comment : Testing only
             * commented_on : 2019-06-29T10:34:39.000Z
             */

            private int survey_comment_id;
            private String commented_by_user_name;
            private String comment;
            private String commented_on;

            public int getSurvey_comment_id() {
                return survey_comment_id;
            }

            public void setSurvey_comment_id(int survey_comment_id) {
                this.survey_comment_id = survey_comment_id;
            }

            public String getCommented_by_user_name() {
                return commented_by_user_name;
            }

            public void setCommented_by_user_name(String commented_by_user_name) {
                this.commented_by_user_name = commented_by_user_name;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
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
