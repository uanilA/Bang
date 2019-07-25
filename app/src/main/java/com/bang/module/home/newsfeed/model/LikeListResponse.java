package com.bang.module.home.newsfeed.model;

import java.util.List;

public class LikeListResponse {

    /**
     * code : 200
     * status : success
     * message : Feed like list retrived successfully
     * data : {"feed_like_count":2,"feed_like_list":[{"full_name":"test daily","profile_photo":"http://localhost:3000/uploads/profile/thumb/Image_1562231422613.jpg","created_on":"2019-07-19T13:42:41.000Z","current_date_time":"2019-07-23T12:06:25.000Z"},{"full_name":"Arvind Patidar","profile_photo":"http://graph.facebook.com/2595191150491701/picture?width=500&height=500","created_on":"2019-07-19T14:49:11.000Z","current_date_time":"2019-07-23T12:06:25.000Z"}]}
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
         * feed_like_count : 2
         * feed_like_list : [{"full_name":"test daily","profile_photo":"http://localhost:3000/uploads/profile/thumb/Image_1562231422613.jpg","created_on":"2019-07-19T13:42:41.000Z","current_date_time":"2019-07-23T12:06:25.000Z"},{"full_name":"Arvind Patidar","profile_photo":"http://graph.facebook.com/2595191150491701/picture?width=500&height=500","created_on":"2019-07-19T14:49:11.000Z","current_date_time":"2019-07-23T12:06:25.000Z"}]
         */

        private int feed_like_count;
        private List<FeedLikeListBean> feed_like_list;

        public int getFeed_like_count() {
            return feed_like_count;
        }

        public void setFeed_like_count(int feed_like_count) {
            this.feed_like_count = feed_like_count;
        }

        public List<FeedLikeListBean> getFeed_like_list() {
            return feed_like_list;
        }

        public void setFeed_like_list(List<FeedLikeListBean> feed_like_list) {
            this.feed_like_list = feed_like_list;
        }

        public static class FeedLikeListBean {
            /**
             * full_name : test daily
             * profile_photo : http://localhost:3000/uploads/profile/thumb/Image_1562231422613.jpg
             * created_on : 2019-07-19T13:42:41.000Z
             * current_date_time : 2019-07-23T12:06:25.000Z
             */

            private String full_name;
            private String profile_photo;
            private String created_on;
            private String current_date_time;

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getProfile_photo() {
                return profile_photo;
            }

            public void setProfile_photo(String profile_photo) {
                this.profile_photo = profile_photo;
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
        }
    }
}
