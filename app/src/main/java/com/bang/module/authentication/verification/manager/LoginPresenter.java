package com.bang.module.authentication.verification.manager;


import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.login.model.ForgotPasswordResponse;
import com.bang.network.ServiceGenerator;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Anil Upadhyay on 25/05/2019.
 */

public class LoginPresenter {
    private ApiCallback.LoginManagerCallback loginManagerCallback;
    Context mContext;

    public LoginPresenter(ApiCallback.LoginManagerCallback loginManagerCallback, Context mContext) {
        this.loginManagerCallback = loginManagerCallback;
        this.mContext=mContext;
    }

    public void callLoginApi(String email, String password ,String deviceToken ,String deviceType) {
        loginManagerCallback.onShowBaseLoader();
            API api = ServiceGenerator.createService(API.class);
            Call<LoginResponse> callLoginApi = api.callLoginApi(email, password, deviceToken , deviceType);
            callLoginApi.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    loginManagerCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        loginManagerCallback.onSuccessLogin(response.body());
                    } else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            loginManagerCallback.onError(apiErrors.getMessage());
                        }catch (Exception e){e.printStackTrace();}
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
}