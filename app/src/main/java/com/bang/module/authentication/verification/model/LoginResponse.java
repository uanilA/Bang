package com.bang.module.authentication.verification.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private LoginData data;
    private final static long serialVersionUID = -82969396551613795L;

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

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
