package com.bang.module.home.newsfeed.model;


import java.io.Serializable;

public class AddNewsResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : Newsfeed saved successfully
     * data : {"newsfeed_data":[{"posted_by_user_id":28,"title":"Upload news feed","is_survey_post":0,"survey_id":0,"survey_score":null,"status":1,"created_on":"2019-07-12T09:39:27.000Z","updated_on":"2019-07-12T09:39:27.000Z","photo":"http://34.236.130.86/uploads/newsfeed_photo/thumb/dovies_img_smaple_1562923709229.jpg","video":"","video_thumb":""},{"posted_by_user_id":28,"title":"test here","is_survey_post":0,"survey_id":null,"survey_score":null,"status":1,"created_on":"2019-07-12T11:18:36.000Z","updated_on":"2019-07-12T11:18:36.000Z","photo":"http://34.236.130.86/uploads/newsfeed_photo/thumb/subscribe_bg_1562930316981.png","video":"","video_thumb":""},{"posted_by_user_id":28,"title":"Go gao","is_survey_post":0,"survey_id":null,"survey_score":null,"status":1,"created_on":"2019-07-12T11:21:45.000Z","updated_on":"2019-07-12T11:21:45.000Z","photo":"http://34.236.130.86/uploads/newsfeed_photo/thumb/subscribe_bg_1562930505022.png","video":"","video_thumb":""}]}
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
