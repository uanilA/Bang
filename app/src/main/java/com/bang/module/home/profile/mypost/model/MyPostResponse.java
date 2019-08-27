package com.bang.module.home.profile.mypost.model;

import java.util.List;

public class MyPostResponse {
    /**
     * code : 200
     * status : success
     * message : Myfeed list retrived successfully
     * data : {"total_myfeed_count":10,"myfeed_list":[{"newsfeed_id":67,"is_survey_post":1,"survey_score":4,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":14,"surveyed_user_full_name":"ABC","title":"[21] is unsatisfied with @ [14]","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T11:01:23.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":65,"is_survey_post":1,"survey_score":0,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":14,"surveyed_user_full_name":"ABC","title":"21 is unsatisfied with @ 14","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T10:57:04.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":64,"is_survey_post":1,"survey_score":0,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":11,"surveyed_user_full_name":"Dharmraj Acharya","title":"21 is unsatisfied with @ 11","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T10:50:23.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":61,"is_survey_post":1,"survey_score":8,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":3,"surveyed_user_full_name":null,"title":"[1] is Addictive with @ [3]","photo":"http://34.236.130.86/uploads/badge/addictive.png","video_thumb":"","video":"","created_on":"2019-07-25T08:44:07.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":60,"is_survey_post":1,"survey_score":6,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"http://34.236.130.86/uploads/badge/satisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T07:57:29.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":58,"is_survey_post":1,"survey_score":5,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"","video_thumb":"","video":"","created_on":"2019-07-25T07:30:39.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":57,"is_survey_post":1,"survey_score":6,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"http://34.236.130.86/uploads/badge/satisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T07:29:21.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":48,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"for test","photo":"http://34.236.130.86/uploads/newsfeed_photo/pexels-photo-2167132_1563444714299.jpeg","video_thumb":"","video":"","created_on":"2019-07-18T10:11:54.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":38,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"breathing data ","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/97db88da-5757-47c8-bc17-0ad30866920f_1563433405189.jpg","video":"http://34.236.130.86/uploads/newsfeed_video/20190614_081431_1563433405188.mp4","created_on":"2019-07-18T07:03:25.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":37,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"fcbhh","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/d9bcf435-a06e-4fa7-921c-324f7d5bca81_1563373563629.jpg","video":"http://34.236.130.86/uploads/newsfeed_video/20190614_081431_1563373563628.mp4","created_on":"2019-07-17T14:26:03.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":1}]}
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
         * total_myfeed_count : 10
         * myfeed_list : [{"newsfeed_id":67,"is_survey_post":1,"survey_score":4,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":14,"surveyed_user_full_name":"ABC","title":"[21] is unsatisfied with @ [14]","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T11:01:23.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":65,"is_survey_post":1,"survey_score":0,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":14,"surveyed_user_full_name":"ABC","title":"21 is unsatisfied with @ 14","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T10:57:04.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":64,"is_survey_post":1,"survey_score":0,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":11,"surveyed_user_full_name":"Dharmraj Acharya","title":"21 is unsatisfied with @ 11","photo":"http://34.236.130.86/uploads/badge/unsatisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T10:50:23.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":61,"is_survey_post":1,"survey_score":8,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":3,"surveyed_user_full_name":null,"title":"[1] is Addictive with @ [3]","photo":"http://34.236.130.86/uploads/badge/addictive.png","video_thumb":"","video":"","created_on":"2019-07-25T08:44:07.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":60,"is_survey_post":1,"survey_score":6,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"http://34.236.130.86/uploads/badge/satisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T07:57:29.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":58,"is_survey_post":1,"survey_score":5,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"","video_thumb":"","video":"","created_on":"2019-07-25T07:30:39.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":57,"is_survey_post":1,"survey_score":6,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":7,"surveyed_user_full_name":"Arvind Patidar","title":"[23] is Addictive with @ [7]","photo":"http://34.236.130.86/uploads/badge/satisfied.png","video_thumb":"","video":"","created_on":"2019-07-25T07:29:21.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":48,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"for test","photo":"http://34.236.130.86/uploads/newsfeed_photo/pexels-photo-2167132_1563444714299.jpeg","video_thumb":"","video":"","created_on":"2019-07-18T10:11:54.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":38,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"breathing data ","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/97db88da-5757-47c8-bc17-0ad30866920f_1563433405189.jpg","video":"http://34.236.130.86/uploads/newsfeed_video/20190614_081431_1563433405188.mp4","created_on":"2019-07-18T07:03:25.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":null},{"newsfeed_id":37,"is_survey_post":0,"survey_score":null,"posted_by_user_id":21,"posted_by_user_full_name":"test daily","surveyed_user_id":0,"surveyed_user_full_name":"","title":"fcbhh","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/d9bcf435-a06e-4fa7-921c-324f7d5bca81_1563373563629.jpg","video":"http://34.236.130.86/uploads/newsfeed_video/20190614_081431_1563373563628.mp4","created_on":"2019-07-17T14:26:03.000Z","current_date_time":"2019-07-25T13:30:51.000Z","total_like_count":1}]
         */

        private int total_myfeed_count;
        private List<MyfeedListBean> myfeed_list;

        public int getTotal_myfeed_count() {
            return total_myfeed_count;
        }

        public void setTotal_myfeed_count(int total_myfeed_count) {
            this.total_myfeed_count = total_myfeed_count;
        }

        public List<MyfeedListBean> getMyfeed_list() {
            return myfeed_list;
        }

        public void setMyfeed_list(List<MyfeedListBean> myfeed_list) {
            this.myfeed_list = myfeed_list;
        }

        public static class MyfeedListBean {
            /**
             * newsfeed_id : 67
             * is_survey_post : 1
             * survey_score : 4
             * posted_by_user_id : 21
             * posted_by_user_full_name : test daily
             * surveyed_user_id : 14
             * surveyed_user_full_name : ABC
             * title : [21] is unsatisfied with @ [14]
             * photo : http://34.236.130.86/uploads/badge/unsatisfied.png
             * video_thumb :
             * video :
             * created_on : 2019-07-25T11:01:23.000Z
             * current_date_time : 2019-07-25T13:30:51.000Z
             * total_like_count : null
             */

            private int newsfeed_id;
            private int is_survey_post;
            private int survey_score;
            private int posted_by_user_id;
            private String posted_by_user_full_name;
            private int surveyed_user_id;
            private String surveyed_user_full_name;
            private String title;
            private String photo;
            private String video_thumb;
            private String video;
            private String created_on;
            private String current_date_time;
            private int total_like_count;

            public int getNewsfeed_id() {
                return newsfeed_id;
            }

            public void setNewsfeed_id(int newsfeed_id) {
                this.newsfeed_id = newsfeed_id;
            }

            public int getIs_survey_post() {
                return is_survey_post;
            }

            public void setIs_survey_post(int is_survey_post) {
                this.is_survey_post = is_survey_post;
            }

            public int getSurvey_score() {
                return survey_score;
            }

            public void setSurvey_score(int survey_score) {
                this.survey_score = survey_score;
            }

            public int getPosted_by_user_id() {
                return posted_by_user_id;
            }

            public void setPosted_by_user_id(int posted_by_user_id) {
                this.posted_by_user_id = posted_by_user_id;
            }

            public String getPosted_by_user_full_name() {
                return posted_by_user_full_name;
            }

            public void setPosted_by_user_full_name(String posted_by_user_full_name) {
                this.posted_by_user_full_name = posted_by_user_full_name;
            }

            public int getSurveyed_user_id() {
                return surveyed_user_id;
            }

            public void setSurveyed_user_id(int surveyed_user_id) {
                this.surveyed_user_id = surveyed_user_id;
            }

            public String getSurveyed_user_full_name() {
                return surveyed_user_full_name;
            }

            public void setSurveyed_user_full_name(String surveyed_user_full_name) {
                this.surveyed_user_full_name = surveyed_user_full_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getVideo_thumb() {
                return video_thumb;
            }

            public void setVideo_thumb(String video_thumb) {
                this.video_thumb = video_thumb;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public String getCurrent_date_time() {
                return current_date_time;
            }

            public void setCurrent_date_time(String current_date_time) {
                this.current_date_time = current_date_time;
            }

            public int getTotal_like_count() {
                return total_like_count;
            }

            public void setTotal_like_count(int total_like_count) {
                this.total_like_count = total_like_count;
            }
        }
    }
}
