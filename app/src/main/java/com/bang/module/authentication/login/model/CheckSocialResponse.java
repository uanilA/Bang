package com.bang.module.authentication.login.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckSocialResponse implements Serializable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("socialStatus")
    private Integer socialStatus;
    @SerializedName("message")
    private String message;
    private final static long serialVersionUID = 3485293638208020396L;

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

    public Integer getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(Integer socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
