package com.bang.module.home.addsurvey.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.model.GetAllUserResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListManager  {
    private ApiCallback.GetAllUserCallback getAllUserCallback;
    private Context mContext;
    private Session session;


    public ContactListManager(ApiCallback.GetAllUserCallback getAllUserCallback , Context mContext) {
        this.getAllUserCallback = getAllUserCallback;
        this.mContext=mContext;
        session = new Session(mContext);
    }

    public void callAllUserList(){
        getAllUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<GetAllUserResponse> loginResponseCall = api.callAllUsersApi(session.getAuthToken());
            loginResponseCall.enqueue(new Callback<GetAllUserResponse>() {
                @Override
                public void onResponse(@NonNull Call<GetAllUserResponse> call, @NonNull Response<GetAllUserResponse> response) {
                    getAllUserCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        getAllUserCallback.onSuccessAllUsers(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            getAllUserCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            getAllUserCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<GetAllUserResponse> call, @NonNull Throwable t) {
                    getAllUserCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        getAllUserCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        getAllUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
