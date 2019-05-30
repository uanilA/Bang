package com.bang.module.authentication.login.manager;

import android.content.Context;

import com.bang.R;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.errorResponse.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.serverhandling.API;
import com.bang.serverhandling.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckSocialManager {
    private ApiCallback.CheckSocialCallback checkSocialCallback;
    Context mContext;


    public CheckSocialManager(ApiCallback.CheckSocialCallback checkSocialCallback , Context mContext) {
        this.checkSocialCallback = checkSocialCallback;
        this.mContext=mContext;
    }

    public void callSocialCheck(String countryCode,String mobileNumber){
        checkSocialCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<CheckSocialResponse> otpResponseCall = api.callcheckSocialStatusApi(countryCode,mobileNumber);
            otpResponseCall.enqueue(new Callback<CheckSocialResponse>() {
                @Override
                public void onResponse(Call<CheckSocialResponse> call, Response<CheckSocialResponse> response) {
                    checkSocialCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        checkSocialCallback.onSuccessCheckSocial(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        checkSocialCallback.onError(apiErrors.getMessage());
                    }
                }
                @Override
                public void onFailure(Call<CheckSocialResponse> call, Throwable t) {
                    checkSocialCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        checkSocialCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        checkSocialCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
