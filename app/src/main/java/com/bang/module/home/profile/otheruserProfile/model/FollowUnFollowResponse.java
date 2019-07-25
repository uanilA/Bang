package com.bang.module.home.profile.otheruserProfile.model;

public class FollowUnFollowResponse {

    /**
     * code : 200
     * status : success
     * is_following : 1
     * total_followers : 2
     * message : Follow successfully
     */

    private int code;
    private String status;
    private int is_following;
    private int total_followers;
    private String message;

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

    public int getIs_following() {
        return is_following;
    }

    public void setIs_following(int is_following) {
        this.is_following = is_following;
    }

    public int getTotal_followers() {
        return total_followers;
    }

    public void setTotal_followers(int total_followers) {
        this.total_followers = total_followers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
