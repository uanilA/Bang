package com.bang.module.home.addsurvey.model;

import java.util.List;

public class GetAllUserResponse {


    /**
     * code : 200
     * status : success
     * message : User list retrieved successfully
     * data : {"userList":[{"userId":2,"country_code":"+91","phone_number":"9098191728","gender":"0"},{"userId":3,"country_code":"+91","phone_number":"9806610605","gender":"0"},{"userId":4,"country_code":"+91","phone_number":"9713578048","gender":"0"},{"userId":7,"country_code":"+91","phone_number":"7869979679","gender":"0"},{"userId":9,"country_code":"+91","phone_number":"9630347896","gender":"0"}]}
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
        private List<UserListBean> userList;

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * userId : 2
             * country_code : +91
             * phone_number : 9098191728
             * gender : 0
             */

            private int userId;
            private String country_code;
            private String phone_number;
            private String gender;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }
    }
}
