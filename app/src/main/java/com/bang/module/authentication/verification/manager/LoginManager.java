package com.bang.module.authentication.verification.manager;



import android.content.Context;
import android.support.annotation.NonNull;

import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.errorResponse.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.serverhandling.API;
import com.bang.serverhandling.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Anil Upadhyay on 25/05/2019.
 */

public class LoginManager {
    private ApiCallback.LoginManagerCallback loginManagerCallback;
    Context mContext;

    public LoginManager(ApiCallback.LoginManagerCallback loginManagerCallback, Context mContext) {
        this.loginManagerCallback = loginManagerCallback;
        this.mContext=mContext;
    }

    public void callLoginApi(String countryCode, String number) {
        loginManagerCallback.onShowBaseLoader();
      ///  if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<LoginResponse> callLoginApi = api.callLoginApi(countryCode, number);
            callLoginApi.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    loginManagerCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        loginManagerCallback.onSuccessLogin(response.body());
                    } else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        loginManagerCallback.onError(apiErrors.getMessage());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    loginManagerCallback.onHideBaseLoader();
                    if (t instanceof IOException) {
                        loginManagerCallback.onError("Server down or no internet connection");
                    } else {
                        loginManagerCallback.onError("oops something went wrong");
                    }
                }
            });
        }
   // }
}