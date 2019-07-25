package com.bang.module.home.profile.followersfollowing.model;

import java.util.List;

public class FollowersResponse {
    /**
     * code : 200
     * status : success
     * message : Follower list retrieved successfully
     * data : {"total_followers":1,"follower_list":[{"full_name":"Rohit Singh","userId":23,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg","total_followers":1}]}
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
         * total_followers : 1
         * follower_list : [{"full_name":"Rohit Singh","userId":23,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg","total_followers":1}]
         */

        private int total_followers;
        private List<FollowerListBean> follower_list;

        public int getTotal_followers() {
            return total_followers;
        }

        public void setTotal_followers(int total_followers) {
            this.total_followers = total_followers;
        }

        public List<FollowerListBean> getFollower_list() {
            return follower_list;
        }

        public void setFollower_list(List<FollowerListBean> follower_list) {
            this.follower_list = follower_list;
        }

        public static class FollowerListBean {
            /**
             * full_name : Rohit Singh
             * userId : 23
             * profile_photo : http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg
             * total_followers : 1
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
