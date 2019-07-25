package com.bang.module.home.survey.model;

import java.io.Serializable;
import java.util.List;

public class ViewSurveyModel implements Serializable {


    /**
     * code : 200
     * status : success
     * message : Survey answers retrieved successfully.
     * data : {"survey_partner_gender":1,"survey_answers":[{"questionId":7,"question_title":"Hygiene","question_description":"What image best represent your ideal partners hygiene?","optionId":19,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Flower_Icon.png"},{"questionId":8,"question_title":"Feel","question_description":"Which image best represents how your partner felt?","optionId":22,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Knot_Icon.png"},{"questionId":9,"question_title":"Moisture","question_description":"What image best represents your partners moisture?","optionId":25,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Sahara_Icon.png"},{"questionId":10,"question_title":"Skills","question_description":"What image best represents your partners skills?","optionId":28,"option_photo":"http://34.236.130.86/uploads/profile/thumb/BuildingBlocks_Icon.png"},{"questionId":11,"question_title":"Size","question_description":"What image best represents the size of your partner?","optionId":31,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Soccer_Icon.png"},{"questionId":12,"question_title":"Connection","question_description":"What image best represents the connection you have with your partner?","optionId":34,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Magnets_Icon.png"}]}
     */

    private int code;
    private String status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * survey_partner_gender : 1
         * survey_answers : [{"questionId":7,"question_title":"Hygiene","question_description":"What image best represent your ideal partners hygiene?","optionId":19,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Flower_Icon.png"},{"questionId":8,"question_title":"Feel","question_description":"Which image best represents how your partner felt?","optionId":22,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Knot_Icon.png"},{"questionId":9,"question_title":"Moisture","question_description":"What image best represents your partners moisture?","optionId":25,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Sahara_Icon.png"},{"questionId":10,"question_title":"Skills","question_description":"What image best represents your partners skills?","optionId":28,"option_photo":"http://34.236.130.86/uploads/profile/thumb/BuildingBlocks_Icon.png"},{"questionId":11,"question_title":"Size","question_description":"What image best represents the size of your partner?","optionId":31,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Soccer_Icon.png"},{"questionId":12,"question_title":"Connection","question_description":"What image best represents the connection you have with your partner?","optionId":34,"option_photo":"http://34.236.130.86/uploads/profile/thumb/Magnets_Icon.png"}]
         */

        private int survey_partner_gender;
        private List<SurveyAnswersBean> survey_answers;

        public int getSurvey_partner_gender() {
            return survey_partner_gender;
        }

        public void setSurvey_partner_gender(int survey_partner_gender) {
            this.survey_partner_gender = survey_partner_gender;
        }

        public List<SurveyAnswersBean> getSurvey_answers() {
            return survey_answers;
        }

        public void setSurvey_answers(List<SurveyAnswersBean> survey_answers) {
            this.survey_answers = survey_answers;
        }

        public static class SurveyAnswersBean {
            /**
             * questionId : 7
             * question_title : Hygiene
             * question_description : What image best represent your ideal partners hygiene?
             * optionId : 19
             * option_photo : http://34.236.130.86/uploads/profile/thumb/Flower_Icon.png
             */

            private int questionId;
            private String question_title;
            private String question_description;
            private int optionId;
            private String option_photo;

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getQuestion_title() {
                return question_title;
            }

            public void setQuestion_title(String question_title) {
                this.question_title = question_title;
            }

            public String getQuestion_description() {
                return question_description;
            }

            public void setQuestion_description(String question_description) {
                this.question_description = question_description;
            }

            public int getOptionId() {
                return optionId;
            }

            public void setOptionId(int optionId) {
                this.optionId = optionId;
            }

            public String getOption_photo() {
                return option_photo;
            }

            public void setOption_photo(String option_photo) {
                this.option_photo = option_photo;
            }
        }
    }
}
