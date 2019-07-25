package com.bang.module.setting.manager;

import android.content.Context;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.module.setting.model.ChangePasswordResponse;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.setting.model.LogoutResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutManager {
    private ApiCallback.LogoutCallback logoutCallback;
    Context mContext;
    Session session;


    public LogoutManager(ApiCallback.LogoutCallback logoutCallback , Context mContext) {
        this.logoutCallback = logoutCallback;
        this.mContext=mContext;
        session = new Session(mContext);
    }

    public void callLogoutApi(){
        logoutCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<LogoutResponse> otpResponseCall = api.callLogoutApi(session.getAuthToken());
            otpResponseCall.enqueue(new Callback<LogoutResponse>() {
                @Override
                public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                    logoutCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        logoutCallback.onSuccessLogout(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        logoutCallback.onError(apiErrors.getMessage());
                    }
                }
                @Override
                public void onFailure(Call<LogoutResponse> call, Throwable t) {
                    logoutCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        logoutCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        logoutCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }


    public void callChangePasswordApi(String oldPassword,String newPassword, String confmPassword){
        logoutCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<ChangePasswordResponse> otpResponseCall = api.callChangePasswordApi(session.getAuthToken(),oldPassword,newPassword,confmPassword);
            otpResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
                @Override
                public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                    logoutCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        logoutCallback.onSuccessChangePassword(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            logoutCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            logoutCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                    logoutCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        logoutCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        logoutCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }

}
