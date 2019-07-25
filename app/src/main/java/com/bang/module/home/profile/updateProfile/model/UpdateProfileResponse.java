package com.bang.module.home.profile.updateProfile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateProfileResponse implements Serializable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UpdateProfileData data;
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

    public UpdateProfileData getData() {
        return data;
    }

    public void setData(UpdateProfileData data) {
        this.data = data;
    }
}
