package com.bang.module.home.profile.mypost.model;

import java.util.List;

public class MyPostResponse {

    /**
     * code : 200
     * status : success
     * message : Myfeed list retrived successfully
     * data : {"myfeed_list":[{"newsfeedId":48,"posted_by_user_id":21,"title":"for test","photo":"http://localhost:3000/uploads/newsfeed_photo/pexels-photo-2167132_1563444714299.jpeg","video_thumb":"","video":"","created_on":"2019-07-18T10:11:54.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":38,"posted_by_user_id":21,"title":"breathing data ","photo":"","video_thumb":"http://localhost:3000/uploads/newsfeed_video_thumb/97db88da-5757-47c8-bc17-0ad30866920f_1563433405189.jpg","video":"http://localhost:3000/uploads/newsfeed_video/20190614_081431_1563433405188.mp4","created_on":"2019-07-18T07:03:25.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":37,"posted_by_user_id":21,"title":"fcbhh","photo":"","video_thumb":"http://localhost:3000/uploads/newsfeed_video_thumb/d9bcf435-a06e-4fa7-921c-324f7d5bca81_1563373563629.jpg","video":"http://localhost:3000/uploads/newsfeed_video/20190614_081431_1563373563628.mp4","created_on":"2019-07-17T14:26:03.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":1},{"newsfeedId":33,"posted_by_user_id":21,"title":"gfvg","photo":"","video_thumb":"","video":"","created_on":"2019-07-17T13:50:22.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":32,"posted_by_user_id":21,"title":"Upload news feed","photo":"","video_thumb":"http://localhost:3000/uploads/newsfeed_video_thumb/pexels-photo-2167132_1563356897898.jpeg","video":"http://localhost:3000/uploads/newsfeed_video/SampleVideos4_1563356897897.mp4","created_on":"2019-07-17T09:48:17.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":29,"posted_by_user_id":21,"title":"keyboard","photo":"","video_thumb":"","video":"","created_on":"2019-07-12T14:22:32.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":28,"posted_by_user_id":21,"title":"gfdg","photo":"http://localhost:3000/uploads/newsfeed_photo/Image_1562941117279.jpg","video_thumb":"","video":"","created_on":"2019-07-12T14:18:37.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":27,"posted_by_user_id":21,"title":"bhhgf","photo":"","video_thumb":"","video":"","created_on":"2019-07-12T14:18:15.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":23,"posted_by_user_id":21,"title":"nbxn","photo":"http://localhost:3000/uploads/newsfeed_photo/Image_1562939592517.jpg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:12.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null},{"newsfeedId":19,"posted_by_user_id":21,"title":"Test data","photo":"","video_thumb":"","video":"","created_on":"2019-07-12T13:39:21.000Z","current_date_time":"2019-07-23T05:29:14.000Z","total_like_count":null}]}
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
        private List<MyfeedListBean> myfeed_list;

        public List<MyfeedListBean> getMyfeed_list() {
            return myfeed_list;
        }

        public void setMyfeed_list(List<MyfeedListBean> myfeed_list) {
            this.myfeed_list = myfeed_list;
        }

        public static class MyfeedListBean {
            /**
             * newsfeedId : 48
             * posted_by_user_id : 21
             * title : for test
             * photo : http://localhost:3000/uploads/newsfeed_photo/pexels-photo-2167132_1563444714299.jpeg
             * video_thumb :
             * video :
             * created_on : 2019-07-18T10:11:54.000Z
             * current_date_time : 2019-07-23T05:29:14.000Z
             * total_like_count : null
             */

            private int newsfeedId;
            private int posted_by_user_id;
            private String title;
            private String photo;
            private String video_thumb;
            private String video;
            private String created_on;
            private String current_date_time;
            private int total_like_count;

            public int getNewsfeedId() {
                return newsfeedId;
            }

            public void setNewsfeedId(int newsfeedId) {
                this.newsfeedId = newsfeedId;
            }

            public int getPosted_by_user_id() {
                return posted_by_user_id;
            }

            public void setPosted_by_user_id(int posted_by_user_id) {
                this.posted_by_user_id = posted_by_user_id;
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
