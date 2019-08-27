package com.bang.module.home.profile.getprofile.model;

import java.io.Serializable;

public class MyProfileResponse implements Serializable {


    /**
     * code : 200
     * status : success
     * message : successfully retrived user record
     * data : {"userId":21,"email":"anilupadhyay.mindiii@gmail.com","full_name":"hemant","phone_number":"9987676780","country_code":"+91","country_flag_code":"","social_type":null,"social_id":null,"profile_photo":"https://bang-development.s3.amazonaws.com/uploads/profile/thumb/1565673977650.png","is_profile_url":0,"gender":"0","profile_step":2,"prefers":"1","location":"Indore - Bhopal Rd, Brajeshwari Extension, Greater Brajeshwari, Indore, Madhya Pradesh 452016, India","city":"Indore","state":"Madhya Pradesh","country":"India","latitude":22.7051,"longitude":75.9092,"signup_from":null,"device_type":1,"device_token":"1","status":1,"auth_token":"eb80a3369879c3ae6a245f2b2c7c9ec3","created_on":"2019-07-04T09:10:22.000Z","updated_on":"2019-07-04T09:10:22.000Z","total_addictive":0,"total_satisfied":2,"total_unsatisfied":1,"total_followers":3,"total_following":8,"total_post":9}
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
         * userId : 21
         * email : anilupadhyay.mindiii@gmail.com
         * full_name : hemant
         * phone_number : 9987676780
         * country_code : +91
         * country_flag_code :
         * social_type : null
         * social_id : null
         * profile_photo : https://bang-development.s3.amazonaws.com/uploads/profile/thumb/1565673977650.png
         * is_profile_url : 0
         * gender : 0
         * profile_step : 2
         * prefers : 1
         * location : Indore - Bhopal Rd, Brajeshwari Extension, Greater Brajeshwari, Indore, Madhya Pradesh 452016, India
         * city : Indore
         * state : Madhya Pradesh
         * country : India
         * latitude : 22.7051
         * longitude : 75.9092
         * signup_from : null
         * device_type : 1
         * device_token : 1
         * status : 1
         * auth_token : eb80a3369879c3ae6a245f2b2c7c9ec3
         * created_on : 2019-07-04T09:10:22.000Z
         * updated_on : 2019-07-04T09:10:22.000Z
         * total_addictive : 0
         * total_satisfied : 2
         * total_unsatisfied : 1
         * total_followers : 3
         * total_following : 8
         * total_post : 9
         */

        private int userId;
        private String email;
        private String full_name;
        private String phone_number;
        private String country_code;
        private String country_flag_code;
        private Object social_type;
        private Object social_id;
        private String profile_photo;
        private int is_profile_url;
        private String gender;
        private int profile_step;
        private String prefers;
        private String location;
        private String city;
        private String state;
        private String country;
        private double latitude;
        private double longitude;
        private Object signup_from;
        private int device_type;
        private String device_token;
        private int status;
        private String auth_token;
        private String created_on;
        private String updated_on;
        private int total_addictive;
        private int total_satisfied;
        private int total_unsatisfied;
        private int total_followers;
        private int total_following;
        private int total_post;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
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

        public String getCountry_flag_code() {
            return country_flag_code;
        }

        public void setCountry_flag_code(String country_flag_code) {
            this.country_flag_code = country_flag_code;
        }

        public Object getSocial_type() {
            return social_type;
        }

        public void setSocial_type(Object social_type) {
            this.social_type = social_type;
        }

        public Object getSocial_id() {
            return social_id;
        }

        public void setSocial_id(Object social_id) {
            this.social_id = social_id;
        }

        public String getProfile_photo() {
            return profile_photo;
        }

        public void setProfile_photo(String profile_photo) {
            this.profile_photo = profile_photo;
        }

        public int getIs_profile_url() {
            return is_profile_url;
        }

        public void setIs_profile_url(int is_profile_url) {
            this.is_profile_url = is_profile_url;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getProfile_step() {
            return profile_step;
        }

        public void setProfile_step(int profile_step) {
            this.profile_step = profile_step;
        }

        public String getPrefers() {
            return prefers;
        }

        public void setPrefers(String prefers) {
            this.prefers = prefers;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public Object getSignup_from() {
            return signup_from;
        }

        public void setSignup_from(Object signup_from) {
            this.signup_from = signup_from;
        }

        public int getDevice_type() {
            return device_type;
        }

        public void setDevice_type(int device_type) {
            this.device_type = device_type;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAuth_token() {
            return auth_token;
        }

        public void setAuth_token(String auth_token) {
            this.auth_token = auth_token;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getUpdated_on() {
            return updated_on;
        }

        public void setUpdated_on(String updated_on) {
            this.updated_on = updated_on;
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

        public int getTotal_post() {
            return total_post;
        }

        public void setTotal_post(int total_post) {
            this.total_post = total_post;
        }
    }
}
