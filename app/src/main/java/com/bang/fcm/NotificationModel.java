package com.bang.fcm;

import java.io.Serializable;

public class NotificationModel implements Serializable {
    private String notification_type;
    private String body;
    private String title;
    private String by_user_id;
    private String survey_id;
    private String newsfeed_id;
    private String bang_request_id;


    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy_user_id() {
        return by_user_id;
    }

    public void setBy_user_id(String by_user_id) {
        this.by_user_id = by_user_id;
    }

    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getNewsfeed_id() {
        return newsfeed_id;
    }

    public void setNewsfeed_id(String newsfeed_id) {
        this.newsfeed_id = newsfeed_id;
    }

    public String getBang_request_id() {
        return bang_request_id;
    }

    public void setBang_request_id(String bang_request_id) {
        this.bang_request_id = bang_request_id;
    }
}
