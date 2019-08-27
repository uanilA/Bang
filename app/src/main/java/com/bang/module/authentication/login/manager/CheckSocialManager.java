package com.bang.module.authentication.login.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

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

    public void callSocialCheck(String countryCode,String mobileNumber , String deviceToken,String deviceType ){
        checkSocialCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<CheckSocialResponse> loginResponseCall = api.callcheckSocialStatusApi(countryCode,mobileNumber,deviceToken,deviceType);
            loginResponseCall.enqueue(new Callback<CheckSocialResponse>() {
                @Override
                public void onResponse(@NonNull Call<CheckSocialResponse> call, @NonNull Response<CheckSocialResponse> response) {
                    checkSocialCallback.onHideBaseLoader();
                    try {
                        if (response.isSuccessful()) {
                            checkSocialCallback.onSuccessCheckSocial(response.body());
                        } else {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            checkSocialCallback.onError(apiErrors.getMessage());
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
                @Override
                public void onFailure(@NonNull Call<CheckSocialResponse> call, @NonNull Throwable t) {
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
