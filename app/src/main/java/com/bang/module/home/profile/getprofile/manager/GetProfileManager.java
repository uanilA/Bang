package com.bang.module.home.profile.getprofile.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.getprofile.model.MyProfileResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileManager {
    private ApiCallback.GetProfileCallback getProfileCallback;
    private Context mContext;

    public GetProfileManager(ApiCallback.GetProfileCallback getProfileCallback , Context mContext) {
        this.getProfileCallback = getProfileCallback;
        this.mContext=mContext;
    }

    public void callGetMyProfile(){
        Session session = new Session(mContext);
        getProfileCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<MyProfileResponse> getProfileResponseCall = api.callMyProfileApi(session.getAuthToken());
            getProfileResponseCall.enqueue(new Callback<MyProfileResponse>() {
                @Override
                public void onResponse(@NonNull Call<MyProfileResponse> call, @NonNull Response<MyProfileResponse> response) {
                    getProfileCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        getProfileCallback.onSuccessGetProfile(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                getProfileCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                getProfileCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<MyProfileResponse> call, @NonNull Throwable t) {
                    getProfileCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        getProfileCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        getProfileCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}