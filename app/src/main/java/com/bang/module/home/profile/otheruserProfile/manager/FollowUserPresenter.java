package com.bang.module.home.profile.otheruserProfile.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.otheruserProfile.model.FollowUnFollowResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUserPresenter {

    private ApiCallback.FollowUserCallback followUserCallback;
    private Context mContext;
    private Session session;

    public FollowUserPresenter(ApiCallback.FollowUserCallback followUserCallback, Context mContext) {
        this.followUserCallback=followUserCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callFollowApi(String userId){

        followUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<FollowUnFollowResponse> getProfileResponseCall = api.callFollowUnfollowApi(session.getAuthToken(),userId);
            getProfileResponseCall.enqueue(new Callback<FollowUnFollowResponse>() {
                @Override
                public void onResponse(@NonNull Call<FollowUnFollowResponse> call, @NonNull Response<FollowUnFollowResponse> response) {
                    followUserCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        followUserCallback.onSuccessFollowResponse(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                followUserCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                followUserCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<FollowUnFollowResponse> call, @NonNull Throwable t) {
                    followUserCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        followUserCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        followUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }

}
