package com.bang.module.authentication.country.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int flag;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("phone_code")
    @Expose
    private Integer phoneCode;
    @SerializedName("code")
    @Expose
    private String code;

    public boolean isSelected;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
