package com.bang.module.home.profile.getprofile.model;

import java.io.Serializable;

public class MyProfileResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : successfully retrived user record
     * data : {"userId":23,"email":"a@gmail.com","full_name":"Rohit Singh","phone_number":"7999788855","country_code":"+355","social_type":"1","social_id":null,"profile_photo":"http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg","is_profile_url":0,"gender":"0","profile_step":2,"prefers":"1","location":null,"city":null,"state":null,"country":null,"latitude":null,"longitude":null,"signup_from":null,"device_type":0,"device_token":"ePK0Abd8WHc:APA91bHFo-RLavzIRkeXU9Ero04Jh5z_uG3kFes8fNo8j3jnL_1q92QqLJTS4Bhf8FQ5ZO7oeRllkH_nQYJ6-wpPV1pJPEfL-VWdOrUAKZUm3fcsrkpj7u5aikxgUxDrmh3r0q6Tnoe1","status":1,"auth_token":"2aee461d395bd39c14639336ac9525cf","created_on":"2019-07-04T11:18:52.000Z","updated_on":"2019-07-04T11:18:52.000Z","total_addictive":0,"total_satisfied":0,"total_unsatisfied":0,"total_followers":1,"total_following":2,"total_post":0}
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
         * userId : 23
         * email : a@gmail.com
         * full_name : Rohit Singh
         * phone_number : 7999788855
         * country_code : +355
         * social_type : 1
         * social_id : null
         * profile_photo : http://34.236.130.86/uploads/profile/thumb/file_1562676704694.jpeg
         * is_profile_url : 0
         * gender : 0
         * profile_step : 2
         * prefers : 1
         * location : null
         * city : null
         * state : null
         * country : null
         * latitude : null
         * longitude : null
         * signup_from : null
         * device_type : 0
         * device_token : ePK0Abd8WHc:APA91bHFo-RLavzIRkeXU9Ero04Jh5z_uG3kFes8fNo8j3jnL_1q92QqLJTS4Bhf8FQ5ZO7oeRllkH_nQYJ6-wpPV1pJPEfL-VWdOrUAKZUm3fcsrkpj7u5aikxgUxDrmh3r0q6Tnoe1
         * status : 1
         * auth_token : 2aee461d395bd39c14639336ac9525cf
         * created_on : 2019-07-04T11:18:52.000Z
         * updated_on : 2019-07-04T11:18:52.000Z
         * total_addictive : 0
         * total_satisfied : 0
         * total_unsatisfied : 0
         * total_followers : 1
         * total_following : 2
         * total_post : 0
         */

        private int userId;
        private String email;
        private String full_name;
        private String phone_number;
        private String country_code;
        private String social_type;
        private Object social_id;
        private String profile_photo;
        private int is_profile_url;
        private String gender;
        private int profile_step;
        private String prefers;
        private Object location;
        private Object city;
        private Object state;
        private Object country;
        private Object latitude;
        private Object longitude;
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

        public String getSocial_type() {
            return social_type;
        }

        public void setSocial_type(String social_type) {
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

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
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
