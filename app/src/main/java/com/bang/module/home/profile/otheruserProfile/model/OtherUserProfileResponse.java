package com.bang.module.home.profile.otheruserProfile.model;

import java.io.Serializable;

public class OtherUserProfileResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : User public profile retrieved successfully.
     * data : {"full_name":"Rahul Singh V","user_profile_photo":"http://34.236.130.86/uploads/profile/thumb/dovies_img_smaple_1561618503439.jpg","phone_number":"9713578048","country_code":"+91","total_addictive":0,"total_satisfied":0,"total_unsatisfied":8,"total_send_survey":34,"total_received_survey":8,"total_followers":0,"total_following":0,"is_following":0,"total_post":0}
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
         * full_name : Rahul Singh V
         * user_profile_photo : http://34.236.130.86/uploads/profile/thumb/dovies_img_smaple_1561618503439.jpg
         * phone_number : 9713578048
         * country_code : +91
         * total_addictive : 0
         * total_satisfied : 0
         * total_unsatisfied : 8
         * total_send_survey : 34
         * total_received_survey : 8
         * total_followers : 0
         * total_following : 0
         * is_following : 0
         * total_post : 0
         */

        private String full_name;
        private String user_profile_photo;
        private String phone_number;
        private String country_code;
        private int total_addictive;
        private int total_satisfied;
        private int total_unsatisfied;
        private int total_send_survey;
        private int total_received_survey;
        private int total_followers;
        private int total_following;
        private int is_following;
        private int total_post;

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getUser_profile_photo() {
            return user_profile_photo;
        }

        public void setUser_profile_photo(String user_profile_photo) {
            this.user_profile_photo = user_profile_photo;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public int getTotal_addictive() {
            return total_addictive;
        }

        public void setTotal_addictive(int total_addictive) {
            this.total_addictive = total_addictive;
        }

        public int getTotal_satisfied() {
            return total_satisfied;
        }

        public void setTotal_satisfied(int total_satisfied) {
            this.total_satisfied = total_satisfied;
        }

        public int getTotal_unsatisfied() {
            return total_unsatisfied;
        }

        public void setTotal_unsatisfied(int total_unsatisfied) {
            this.total_unsatisfied = total_unsatisfied;
        }

        public int getTotal_send_survey() {
            return total_send_survey;
        }

        public void setTotal_send_survey(int total_send_survey) {
            this.total_send_survey = total_send_survey;
        }

        public int getTotal_received_survey() {
            return total_received_survey;
        }

        public void setTotal_received_survey(int total_received_survey) {
            this.total_received_survey = total_received_survey;
        }

        public int getTotal_followers() {
            return total_followers;
        }

        public void setTotal_followers(int total_followers) {
            this.total_followers = total_followers;
        }

        public int getTotal_following() {
            return total_following;
        }

        public void setTotal_following(int total_following) {
            this.total_following = total_following;
        }

        public int getIs_following() {
            return is_following;
        }

        public void setIs_following(int is_following) {
            this.is_following = is_following;
        }

        public int getTotal_post() {
            return total_post;
        }

        public void setTotal_post(int total_post) {
            this.total_post = total_post;
        }
    }
}
