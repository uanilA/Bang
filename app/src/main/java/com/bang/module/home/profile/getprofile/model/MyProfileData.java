package com.bang.module.home.profile.getprofile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyProfileData implements Serializable {
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("email")
    private String email;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("social_type")
    private Object socialType;
    @SerializedName("social_id")
    private Object socialId;
    @SerializedName("profile_photo")
    private String profilePhoto;
    @SerializedName("is_profile_url")
    private Integer isProfileUrl;
    @SerializedName("gender")
    private String gender;
    @SerializedName("profile_step")
    private Integer profileStep;
    @SerializedName("prefers")
    private String prefers;
    @SerializedName("location")
    private Object location;
    @SerializedName("city")
    private Object city;
    @SerializedName("state")
    private Object state;
    @SerializedName("country")
    private Object country;
    @SerializedName("latitude")
    private Object latitude;
    @SerializedName("longitude")
    private Object longitude;
    @SerializedName("signup_from")
    private Object signupFrom;
    @SerializedName("device_type")
    private Integer deviceType;
    @SerializedName("device_token")
    private String deviceToken;
    @SerializedName("status")
    private Integer status;
    @SerializedName("auth_token")
    private String authToken;
    @SerializedName("created_on")
    private String createdOn;
    @SerializedName("updated_on")
    private String updatedOn;
    private final static long serialVersionUID = 2188480381909713512L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Object getSocialType() {
        return socialType;
    }

    public void setSocialType(Object socialType) {
        this.socialType = socialType;
    }

    public Object getSocialId() {
        return socialId;
    }

    public void setSocialId(Object socialId) {
        this.socialId = socialId;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getIsProfileUrl() {
        return isProfileUrl;
    }

    public void setIsProfileUrl(Integer isProfileUrl) {
        this.isProfileUrl = isProfileUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getProfileStep() {
        return profileStep;
    }

    public void setProfileStep(Integer profileStep) {
        this.profileStep = profileStep;
    }

    public String getPrefers() {
        return prefers;
    }

    public void setPrefers(String prefers) {
        this.prefers = prefers;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getSignupFrom() {
        return signupFrom;
    }

    public void setSignupFrom(Object signupFrom) {
        this.signupFrom = signupFrom;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

}
