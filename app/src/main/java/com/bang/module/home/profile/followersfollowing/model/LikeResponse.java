package com.bang.module.home.profile.followersfollowing.model;

public class LikeResponse {

    /**
     * code : 200
     * status : success
     * is_like : 1
     * total_like : 1
     * message : Like successfully
     */

    private int code;
    private String status;
    private int is_like;
    private int total_like;
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

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getTotal_like() {
        return total_like;
    }

    public void setTotal_like(int total_like) {
        this.total_like = total_like;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
