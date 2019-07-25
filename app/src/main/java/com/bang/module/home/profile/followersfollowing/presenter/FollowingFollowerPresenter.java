package com.bang.module.home.profile.followersfollowing.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.followersfollowing.model.FollowersResponse;
import com.bang.module.home.profile.followersfollowing.model.FollowingResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingFollowerPresenter {
    private ApiCallback.FollowersCallback followersCallback;
    private Context mContext;

    public FollowingFollowerPresenter(ApiCallback.FollowersCallback followersCallback, Context mContext){
        this.followersCallback = followersCallback;
        this.mContext = mContext;
    }

    public void followingCallingApi(String type, String userId, int offset){
            Session session = new Session(mContext);
           System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+session.getAuthToken());
            followersCallback.onShowBaseLoader();
            if (AppHelper.isConnectingToInternet(mContext)) {
                API api = ServiceGenerator.createService(API.class);
                Call<FollowingResponse> getProfileResponseCall = api.callFollowingFollowersApi(session.getAuthToken(),
                        type,userId,"","0","");
                getProfileResponseCall.enqueue(new Callback<FollowingResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FollowingResponse> call, @NonNull Response<FollowingResponse> response) {
                        followersCallback.onHideBaseLoader();
                        if(response.isSuccessful()){
                            followersCallback.onSuccessFollowing(response.body());
                        }else {
                            try {
                                APIErrors apiErrors = ErrorUtils.parseError(response);
                                if (apiErrors.getMessage().equals("Invalid auth token")) {
                                    followersCallback.onTokenChangeError(apiErrors.getMessage());
                                } else {
                                    followersCallback.onError(apiErrors.getMessage());
                                }
                            }catch (Exception e){e.printStackTrace();}
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<FollowingResponse> call, @NonNull Throwable t) {
                        followersCallback.onHideBaseLoader();
                        if(t instanceof IOException){
                            followersCallback.onError(mContext.getString(R.string.internet_connection));
                        }else {
                            followersCallback.onError(mContext.getString(R.string.oops_wrong));
                        }
                    }
                });
            } else {
                CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
            }
    }


    public void followerCallingApi(String type, String userId, int offset){
        Session session = new Session(mContext);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+session.getAuthToken());
        followersCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<FollowersResponse> getProfileResponseCall = api.callFollowerFollowersApi(session.getAuthToken(),
                    type,userId,"","0","10");
            getProfileResponseCall.enqueue(new Callback<FollowersResponse>() {
                @Override
                public void onResponse(@NonNull Call<FollowersResponse> call, @NonNull Response<FollowersResponse> response) {
                    followersCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        followersCallback.onSuccessFollowers(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                followersCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                followersCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<FollowersResponse> call, @NonNull Throwable t) {
                    followersCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        followersCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        followersCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }

}

