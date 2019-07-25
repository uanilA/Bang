package com.bang.module.authentication.genderselection.model;

public class UpdateLocationResponse {


    /**
     * code : 200
     * status : success
     * message : Location updated successfully
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
