package com.bang.module.home.addsurvey.model;

import java.io.Serializable;
import java.util.List;

public class SurveyQuestionResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : Preference Questions retrieved successfully
     * data : {"questionList":[{"questionId":1,"questionTitle":"Hygiene","survey_question":"What image best represent your ideal partners hygiene?","option":[{"optionId":1,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Barrel_Icon.png","questionId":1},{"optionId":2,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SaltShaker_Icon.png","questionId":1},{"optionId":3,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Soap_Icon.png","questionId":1}]},{"questionId":2,"questionTitle":"Strength","survey_question":"What image best represents strength?","option":[{"optionId":4,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Noodle_Icon.png","questionId":2},{"optionId":5,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/HotDog_Icon.png ","questionId":2},{"optionId":6,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Brick_Icon.png","questionId":2}]},{"questionId":3,"questionTitle":"Stamina","survey_question":"What image best represents your partners stamina?","option":[{"optionId":7,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/StopWatch_Icon.png","questionId":3},{"optionId":8,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Marathon_Icon.png","questionId":3},{"optionId":9,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SunMoon_Icon.png","questionId":3}]},{"questionId":4,"questionTitle":"Skills","survey_question":"What image best represents your partners skills?","option":[{"optionId":10,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/BuildingBlocks_Icon.png","questionId":4},{"optionId":11,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/ChessBoard_Icon.png","questionId":4},{"optionId":12,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Freak_Icon.png","questionId":4}]},{"questionId":5,"questionTitle":"Size","survey_question":"What image best represents the size of your partner?","option":[{"optionId":13,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Flower_Icon.png","questionId":5},{"optionId":14,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Banana_Icon.png","questionId":5},{"optionId":15,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Horse_Icon.png","questionId":5}]},{"questionId":6,"questionTitle":"Connection","survey_question":"What image best represents the connection you have with your partner?","option":[{"optionId":16,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Magnets_Icon.png","questionId":6},{"optionId":17,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/HandshakeMtoF_Icon.png","questionId":6},{"optionId":18,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/LightingMale_Icon.png","questionId":6}]}]}
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
        private List<QuestionListBean> questionList;

        public List<QuestionListBean> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<QuestionListBean> questionList) {
            this.questionList = questionList;
        }

        public static class QuestionListBean {
            /**
             * questionId : 1
             * questionTitle : Hygiene
             * survey_question : What image best represent your ideal partners hygiene?
             * option : [{"optionId":1,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Barrel_Icon.png","questionId":1},{"optionId":2,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SaltShaker_Icon.png","questionId":1},{"optionId":3,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Soap_Icon.png","questionId":1}]
             */

            private int questionId;
            private String questionTitle;
            private String survey_question;
            private List<OptionBean> option;

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getQuestionTitle() {
                return questionTitle;
            }

            public void setQuestionTitle(String questionTitle) {
                this.questionTitle = questionTitle;
            }

            public String getSurvey_question() {
                return survey_question;
            }

            public void setSurvey_question(String survey_question) {
                this.survey_question = survey_question;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            public static class OptionBean {
                /**
                 * optionId : 1
                 * optionPhotos : http://34.236.130.86/uploads/option/thumb/Barrel_Icon.png
                 * questionId : 1
                 */

                private int optionId;
                private String optionPhotos;
                private int questionId;

                public int getOptionId() {
                    return optionId;
                }

                public void setOptionId(int optionId) {
                    this.optionId = optionId;
                }

                public String getOptionPhotos() {
                    return optionPhotos;
                }

                public void setOptionPhotos(String optionPhotos) {
                    this.optionPhotos = optionPhotos;
                }

                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
                }
            }
        }
    }
}
