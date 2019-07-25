package com.bang.module.home.newsfeed.model;

import java.io.Serializable;
import java.util.List;

public class NewsFeedResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : Newsfeed list retrived successfully.
     * data : {"total_newsfeed_count":10,"newsfeed_list":[{"newsfeed_id":43,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"&%R&^%","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg","video":"http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4","created_on":"2019-07-18T07:42:53.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":42,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Xcode","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:24:53.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":41,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Aggr","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:14:45.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":40,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Mindiii2","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:12:57.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":39,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Mindiii","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:08:42.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":36,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Video","photo":"","video_thumb":"","video":"","created_on":"2019-07-17T14:12:02.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":34,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"tegsgb sjjs","photo":"","video_thumb":"","video":"","created_on":"2019-07-17T13:57:00.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":30,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Agra","photo":"","video_thumb":"","video":"","created_on":"2019-07-13T10:29:58.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":26,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Test","photo":"","video_thumb":"","video":"","created_on":"2019-07-12T14:03:38.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":25,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Gh","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939625008.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:45.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0}]}
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
         * total_newsfeed_count : 10
         * newsfeed_list : [{"newsfeed_id":43,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"&%R&^%","photo":"","video_thumb":"http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg","video":"http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4","created_on":"2019-07-18T07:42:53.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":42,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Xcode","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:24:53.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":41,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Aggr","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:14:45.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":1,"is_like":1},{"newsfeed_id":40,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Mindiii2","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:12:57.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":39,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Mindiii","photo":"","video_thumb":"","video":"","created_on":"2019-07-18T07:08:42.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":36,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Video","photo":"","video_thumb":"","video":"","created_on":"2019-07-17T14:12:02.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":34,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"tegsgb sjjs","photo":"","video_thumb":"","video":"","created_on":"2019-07-17T13:57:00.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":30,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Agra","photo":"","video_thumb":"","video":"","created_on":"2019-07-13T10:29:58.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":26,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Test","photo":"","video_thumb":"","video":"","created_on":"2019-07-12T14:03:38.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0},{"newsfeed_id":25,"posted_by_user_id":23,"full_name":"Rohit Singh","profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg","title":"Gh","photo":"http://34.236.130.86/uploads/newsfeed_photo/file_1562939625008.jpeg","video_thumb":"","video":"","created_on":"2019-07-12T13:53:45.000Z","current_date_time":"2019-07-19T07:36:48.000Z","total_like_count":null,"is_like":0}]
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
             * posted_by_user_id : 23
             * full_name : Rohit Singh
             * profile_photo : http://34.236.130.86/uploads/profile/thumb/file_1563521643607.jpeg
             * title : &%R&^%
             * photo :
             * video_thumb : http://34.236.130.86/uploads/newsfeed_video_thumb/file_1563435773551.jpeg
             * video : http://34.236.130.86/uploads/newsfeed_video/file_1563435773550.mp4
             * created_on : 2019-07-18T07:42:53.000Z
             * current_date_time : 2019-07-19T07:36:48.000Z
             * total_like_count : 1
             * is_like : 1
             */

            private int newsfeed_id;
            private int posted_by_user_id;
            private String full_name;
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

            public int getPosted_by_user_id() {
                return posted_by_user_id;
            }

            public void setPosted_by_user_id(int posted_by_user_id) {
                this.posted_by_user_id = posted_by_user_id;
            }

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
