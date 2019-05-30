package com.bang.module.authentication.login.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendOtpResponse implements Serializable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("otp")
    private Integer otp;
    private final static long serialVersionUID = -5614763080691812011L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

}
