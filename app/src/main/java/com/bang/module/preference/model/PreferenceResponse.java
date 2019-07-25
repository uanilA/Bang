package com.bang.module.preference.model;

import java.io.Serializable;
import java.util.List;

public class PreferenceResponse implements Serializable {

    /**
     * code : 200
     * status : success
     * message : Preference Questions retrieved successfully
     * data : {"questionList":[{"questionId":1,"questionTitle":"Hygiene","preference_question":"What image best represent your ideal partners hygiene?","option":[{"optionId":1,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Barrel_Icon.png","questionId":1,"isSelected":"NO"},{"optionId":2,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SaltShaker_Icon.png","questionId":1,"isSelected":"NO"},{"optionId":3,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Soap_Icon.png","questionId":1,"isSelected":"NO"}]},{"questionId":2,"questionTitle":"Strength","preference_question":"What image best represents strength?","option":[{"optionId":4,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Noodle_Icon.png","questionId":2,"isSelected":"NO"},{"optionId":5,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/HotDog_Icon.png ","questionId":2,"isSelected":"NO"},{"optionId":6,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Brick_Icon.png","questionId":2,"isSelected":"NO"}]},{"questionId":3,"questionTitle":"Stamina","preference_question":"What image best represents your partners stamina?","option":[{"optionId":7,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/StopWatch_Icon.png","questionId":3,"isSelected":"NO"},{"optionId":8,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Marathon_Icon.png","questionId":3,"isSelected":"NO"},{"optionId":9,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SunMoon_Icon.png","questionId":3,"isSelected":"NO"}]},{"questionId":4,"questionTitle":"Skills","preference_question":"What image best represents your partners skills?","option":[{"optionId":10,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/BuildingBlocks_Icon.png","questionId":4,"isSelected":"NO"},{"optionId":11,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/ChessBoard_Icon.png","questionId":4,"isSelected":"NO"},{"optionId":12,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Freak_Icon.png","questionId":4,"isSelected":"NO"}]},{"questionId":5,"questionTitle":"Size","preference_question":"What image best represents the size of your partner?","option":[{"optionId":13,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Flower_Icon.png","questionId":5,"isSelected":"NO"},{"optionId":14,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Banana_Icon.png","questionId":5,"isSelected":"NO"},{"optionId":15,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Horse_Icon.png","questionId":5,"isSelected":"NO"}]},{"questionId":6,"questionTitle":"Connection","preference_question":"What image best represents the connection you have with your partner?","option":[{"optionId":16,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Magnets_Icon.png","questionId":6,"isSelected":"NO"},{"optionId":17,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/HandshakeMtoF_Icon.png","questionId":6,"isSelected":"NO"},{"optionId":18,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/LightingMale_Icon.png","questionId":6,"isSelected":"NO"}]}]}
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
             * preference_question : What image best represent your ideal partners hygiene?
             * option : [{"optionId":1,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Barrel_Icon.png","questionId":1,"isSelected":"NO"},{"optionId":2,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/SaltShaker_Icon.png","questionId":1,"isSelected":"NO"},{"optionId":3,"optionPhotos":"http://34.236.130.86/uploads/option/thumb/Soap_Icon.png","questionId":1,"isSelected":"NO"}]
             */

            private int questionId;
            private String questionTitle;
            private String preference_question;
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

            public String getPreference_question() {
                return preference_question;
            }

            public void setPreference_question(String preference_question) {
                this.preference_question = preference_question;
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
                 * isSelected : NO
                 */

                private int optionId;
                private String optionPhotos;
                private int questionId;
                private String isSelected;

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

                public String getIsSelected() {
                    return isSelected;
                }

                public void setIsSelected(String isSelected) {
                    this.isSelected = isSelected;
                }
            }
        }
    }
}
