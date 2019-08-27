package com.bang.module.home.addsurvey.model;

public class ShareSurveyModel {

    /**
     * code : 200
     * status : success
     * message : Survey share with bang follower successfully
     */

    private int code;
    private String status;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
