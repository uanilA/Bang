package com.bang.module.home.profile.bandrequest.model;

public class AcceptRejectModel {

    /**
     * code : 200
     * status : success
     * message : Bang request updated successfully
     * data : {"bang_request_detail":{"bangConnectionId":5,"sender_user_id":21,"receiver_user_id":21,"request_status":1,"nda_sent_by":null,"consent_sent_by":null,"nda_request_status":0,"consent_request_status":0,"nda_sender_signature":null,"nda_receiver_signature":null,"consent_sender_signature":null,"consent_receiver_signature":null}}
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
         * bang_request_detail : {"bangConnectionId":5,"sender_user_id":21,"receiver_user_id":21,"request_status":1,"nda_sent_by":null,"consent_sent_by":null,"nda_request_status":0,"consent_request_status":0,"nda_sender_signature":null,"nda_receiver_signature":null,"consent_sender_signature":null,"consent_receiver_signature":null}
         */

        private BangRequestDetailBean bang_request_detail;

        public BangRequestDetailBean getBang_request_detail() {
            return bang_request_detail;
        }

        public void setBang_request_detail(BangRequestDetailBean bang_request_detail) {
            this.bang_request_detail = bang_request_detail;
        }

        public static class BangRequestDetailBean {
            /**
             * bangConnectionId : 5
             * sender_user_id : 21
             * receiver_user_id : 21
             * request_status : 1
             * nda_sent_by : null
             * consent_sent_by : null
             * nda_request_status : 0
             * consent_request_status : 0
             * nda_sender_signature : null
             * nda_receiver_signature : null
             * consent_sender_signature : null
             * consent_receiver_signature : null
             */

            private int bangConnectionId;
            private int sender_user_id;
            private int receiver_user_id;
            private int request_status;
            private Object nda_sent_by;
            private Object consent_sent_by;
            private int nda_request_status;
            private int consent_request_status;
            private Object nda_sender_signature;
            private Object nda_receiver_signature;
            private Object consent_sender_signature;
            private Object consent_receiver_signature;

            public int getBangConnectionId() {
                return bangConnectionId;
            }

            public void setBangConnectionId(int bangConnectionId) {
                this.bangConnectionId = bangConnectionId;
            }

            public int getSender_user_id() {
                return sender_user_id;
            }

            public void setSender_user_id(int sender_user_id) {
                this.sender_user_id = sender_user_id;
            }

            public int getReceiver_user_id() {
                return receiver_user_id;
            }

            public void setReceiver_user_id(int receiver_user_id) {
                this.receiver_user_id = receiver_user_id;
            }

            public int getRequest_status() {
                return request_status;
            }

            public void setRequest_status(int request_status) {
                this.request_status = request_status;
            }

            public Object getNda_sent_by() {
                return nda_sent_by;
            }

            public void setNda_sent_by(Object nda_sent_by) {
                this.nda_sent_by = nda_sent_by;
            }

            public Object getConsent_sent_by() {
                return consent_sent_by;
            }

            public void setConsent_sent_by(Object consent_sent_by) {
                this.consent_sent_by = consent_sent_by;
            }

            public int getNda_request_status() {
                return nda_request_status;
            }

            public void setNda_request_status(int nda_request_status) {
                this.nda_request_status = nda_request_status;
            }

            public int getConsent_request_status() {
                return consent_request_status;
            }

            public void setConsent_request_status(int consent_request_status) {
                this.consent_request_status = consent_request_status;
            }

            public Object getNda_sender_signature() {
                return nda_sender_signature;
            }

            public void setNda_sender_signature(Object nda_sender_signature) {
                this.nda_sender_signature = nda_sender_signature;
            }

            public Object getNda_receiver_signature() {
                return nda_receiver_signature;
            }

            public void setNda_receiver_signature(Object nda_receiver_signature) {
                this.nda_receiver_signature = nda_receiver_signature;
            }

            public Object getConsent_sender_signature() {
                return consent_sender_signature;
            }

            public void setConsent_sender_signature(Object consent_sender_signature) {
                this.consent_sender_signature = consent_sender_signature;
            }

            public Object getConsent_receiver_signature() {
                return consent_receiver_signature;
            }

            public void setConsent_receiver_signature(Object consent_receiver_signature) {
                this.consent_receiver_signature = consent_receiver_signature;
            }
        }
    }
}
