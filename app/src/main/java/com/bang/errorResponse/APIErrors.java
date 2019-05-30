
package com.bang.errorResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class APIErrors {

    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    private final static long serialVersionUID = 2267651688434174863L;

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


}
