package com.bang.module.authentication.login.manager;

import android.content.Context;

import com.bang.R;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.errorResponse.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.serverhandling.API;
import com.bang.serverhandling.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOtpManager {
    private ApiCallback.SendOtpCallback signUpOtpCallback;
    Context mContext;


    public SendOtpManager(ApiCallback.SendOtpCallback signUpOtpCallback , Context mContext) {
        this.signUpOtpCallback = signUpOtpCallback;
        this.mContext=mContext;
    }

    public void callSendOtp(String type, String countryCode,String mobileNumber){
        signUpOtpCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
        API api = ServiceGenerator.createService(API.class);
        Call<SendOtpResponse>otpResponseCall = api.callOTPApi(type, countryCode,mobileNumber);
        otpResponseCall.enqueue(new Callback<SendOtpResponse>() {
            @Override
            public void onResponse(Call<SendOtpResponse> call, Response<SendOtpResponse> response) {
                signUpOtpCallback.onHideBaseLoader();
                if(response.isSuccessful()){
                    signUpOtpCallback.onSuccessSendOtp(response.body());
                }else {
                    APIErrors apiErrors = ErrorUtils.parseError(response);
                    signUpOtpCallback.onError(apiErrors.getMessage());
                }
            }
            @Override
            public void onFailure(Call<SendOtpResponse> call, Throwable t) {
                signUpOtpCallback.onHideBaseLoader();
                if(t instanceof IOException){
                    signUpOtpCallback.onError("Server down or no internet connection");
                }else {
                    signUpOtpCallback.onError("oops something went wrong");
                }
            }
        });
        } else {
           CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
       }
    }
}