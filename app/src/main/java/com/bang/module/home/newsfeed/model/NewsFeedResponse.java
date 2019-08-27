package com.bang.module.home.newsfeed.model;

import java.io.Serializable;
import java.util.List;

public class NewsFeedResponse implements Serializable {


    /**
     * code : 200
     * status : success
     * message : Newsfeed list retrived successfully.
     * data : {"total_newsfeed_count":4,"newsfeed_list":[{"newsfeed_id":43,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"&%R&^%","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg","video":"http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4","created_on":"2019-07-18T07:42:53.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":4,"is_like":1},{"newsfeed_id":25,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"Gh","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939625008.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:45.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":24,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"Rff","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939603921.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:23.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":1,"is_like":0},{"newsfeed_id":8,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"ABC","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562934187489.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T12:23:07.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":null,"is_like":0}]}
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
         * total_newsfeed_count : 4
         * newsfeed_list : [{"newsfeed_id":43,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"&%R&^%","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg","video":"http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4","created_on":"2019-07-18T07:42:53.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":4,"is_like":1},{"newsfeed_id":25,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"Gh","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939625008.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:45.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":24,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"Rff","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939603921.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:23.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":1,"is_like":0},{"newsfeed_id":8,"is_survey_post":0,"is_anonymous":0,"posted_by_user_id":23,"posted_by_user_full_name":"Rohit Singh","surveyed_user_id":0,"surveyed_user_full_name":"","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg","title":"ABC","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562934187489.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T12:23:07.000Z","current_date_time":"2019-08-02T06:01:10.000Z","total_like_count":null,"is_like":0}]
         */

        private int total_newsfeed_count;
        private List<NewsfeedListBean> newsfeed_list;

        public int getTotal_newsfeed_count() {
            return total_newsfeed_count;
        }

        public void setTotal_newsfeed_count(int total_newsfeed_count) {
            this.total_newsfeed_count = total_newsfeed_count;
        }

        public List<NewsfeedListBean> getNewsfeed_list() {
            return newsfeed_list;
        }

        public void setNewsfeed_list(List<NewsfeedListBean> newsfeed_list) {
            this.newsfeed_list = newsfeed_list;
        }

        public static class NewsfeedListBean {
            /**
             * newsfeed_id : 43
             * is_survey_post : 0
             * is_anonymous : 0
             * posted_by_user_id : 23
             * posted_by_user_full_name : Rohit Singh
             * surveyed_user_id : 0
             * surveyed_user_full_name :
             * profile_photo : http://34.236.130.86/uploads/profile/thumb/file_1563862950469.jpeg
             * title : &%R&^%
             * photo :
             * video_thumb : http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg
             * video : http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4
             * created_on : 2019-07-18T07:42:53.000Z
             * current_date_time : 2019-08-02T06:01:10.000Z
             * total_like_count : 4
             * is_like : 1
             */

            private int newsfeed_id;
            private int is_survey_post;
            private int is_anonymous;
            private int posted_by_user_id;
            private String posted_by_user_full_name;
            private int surveyed_user_id;
            private String surveyed_user_full_name;
            private String profile_photo;
            private String title;
            private String photo;
            private String video_thumb;
            private String video;
            private String created_on;
            private String current_date_time;
            private int total_like_count;
            private int is_like;

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

            public int getIs_anonymous() {
                return is_anonymous;
            }

            public void setIs_anonymous(int is_anonymous) {
                this.is_anonymous = is_anonymous;
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

            public String getProfile_photo() {
                return profile_photo;
            }

            public void setProfile_photo(String profile_photo) {
                this.profile_photo = profile_photo;
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

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }
        }
    }
}
