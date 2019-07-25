package com.bang.module.preference.model;

import java.io.Serializable;

public class SaveQuestionResponse implements Serializable {


    /**
     * code : 200
     * status : success
     * message : Your preference saved successfully
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
