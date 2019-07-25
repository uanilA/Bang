package com.bang.module.home.profile.followersfollowing.model;

import java.util.List;

public class FollowingResponse {

    /**
     * code : 200
     * status : success
     * message : Following list retrieved successfully
     * data : {"total_followings":3,"following_list":[{"full_name":"Manish","userId":2,"profile_photo":"http://34.236.130.86/uploads/placeholders/user_profile_placeholder.png","total_followers":0},{"full_name":"ABC","userId":14,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562155642725.jpeg","total_followers":0},{"full_name":"Rohit Singh","userId":23,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg","total_followers":1}]}
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
         * total_followings : 3
         * following_list : [{"full_name":"Manish","userId":2,"profile_photo":"http://34.236.130.86/uploads/placeholders/user_profile_placeholder.png","total_followers":0},{"full_name":"ABC","userId":14,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562155642725.jpeg","total_followers":0},{"full_name":"Rohit Singh","userId":23,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg","total_followers":1}]
         */

        private int total_followings;
        private List<FollowingListBean> following_list;

        public int getTotal_followings() {
            return total_followings;
        }

        public void setTotal_followings(int total_followings) {
            this.total_followings = total_followings;
        }

        public List<FollowingListBean> getFollowing_list() {
            return following_list;
        }

        public void setFollowing_list(List<FollowingListBean> following_list) {
            this.following_list = following_list;
        }

        public static class FollowingListBean {
            /**
             * full_name : Manish
             * userId : 2
             * profile_photo : http://34.236.130.86/uploads/placeholders/user_profile_placeholder.png
             * total_followers : 0
             */

            private String full_name;
            private int userId;
            private String profile_photo;
            private int total_followers;

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getProfile_photo() {
                return profile_photo;
            }

            public void setProfile_photo(String profile_photo) {
                this.profile_photo = profile_photo;
            }

            public int getTotal_followers() {
                return total_followers;
            }

            public void setTotal_followers(int total_followers) {
                this.total_followers = total_followers;
            }
        }
    }
}
