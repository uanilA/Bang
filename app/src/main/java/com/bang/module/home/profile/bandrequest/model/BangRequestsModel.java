package com.bang.module.home.profile.bandrequest.model;

import java.util.List;

public class BangRequestsModel {

    /**
     * code : 200
     * status : success
     * message : Bang request list retrieved successfully
     * data : {"bang_request_list":[{"full_name":"test daily","bangConnectionId":5,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1562231422613.jpg","total_followers":1},{"full_name":"test daily","bangConnectionId":6,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1562231422613.jpg","total_followers":1},{"full_name":"test daily","bangConnectionId":7,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1562231422613.jpg","total_followers":1},{"full_name":"test daily","bangConnectionId":10,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/Image_1562231422613.jpg","total_followers":1}]}
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
        private List<BangRequestListBean> bang_request_list;

        public List<BangRequestListBean> getBang_request_list() {
            return bang_request_list;
        }

        public void setBang_request_list(List<BangRequestListBean> bang_request_list) {
            this.bang_request_list = bang_request_list;
        }

        public static class BangRequestListBean {
            /**
             * full_name : test daily
             * bangConnectionId : 5
             * profile_photo : http://34.236.130.86/uploads/profile/thumb/Image_1562231422613.jpg
             * total_followers : 1
             */

            private String full_name;
            private int bangConnectionId;
            private String profile_photo;
            private int total_followers;

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public int getBangConnectionId() {
                return bangConnectionId;
            }

            public void setBangConnectionId(int bangConnectionId) {
                this.bangConnectionId = bangConnectionId;
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