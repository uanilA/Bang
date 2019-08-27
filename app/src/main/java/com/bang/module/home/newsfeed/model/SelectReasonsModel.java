package com.bang.module.home.newsfeed.model;

import java.io.Serializable;
import java.util.List;

public class SelectReasonsModel implements Serializable {

    /**
     * code : 200
     * status : success
     * message : User report reasons list retrieved successfully
     * data : {"user_report_reasons_list":[{"OffendingContent":"Inapproriate profile picture"},{"OffendingContent":"Spam/Advertising"},{"OffendingContent":"Abusive/Harmful"},{"OffendingContent":"Objectionable content"},{"OffendingContent":"Fraud"},{"OffendingContent":"Other"}]}
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
        private List<UserReportReasonsListBean> user_report_reasons_list;

        public List<UserReportReasonsListBean> getUser_report_reasons_list() {
            return user_report_reasons_list;
        }

        public void setUser_report_reasons_list(List<UserReportReasonsListBean> user_report_reasons_list) {
            this.user_report_reasons_list = user_report_reasons_list;
        }

        public static class UserReportReasonsListBean {
            /**
             * OffendingContent : Inapproriate profile picture
             */

            private String OffendingContent;

            public String getOffendingContent() {
                return OffendingContent;
            }

            public void setOffendingContent(String OffendingContent) {
                this.OffendingContent = OffendingContent;
            }
        }
    }
}
