package com.bang.module.authentication.login.model;

import java.io.Serializable;

public class ForgotPasswordResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : A secure link has been sent to your email, please follow that link to resetpassword password
     *randomPassword : 42192296
     */

    private int code;
    private String status;
    private String message;
    private int randomPassword;


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

    public int getRandomPassword() {
        return randomPassword;
    }

    public void setRandomPassword(int randomPassword) {
        this.randomPassword = randomPassword;
    }
}
