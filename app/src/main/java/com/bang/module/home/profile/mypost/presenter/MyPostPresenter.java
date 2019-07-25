package com.bang.module.home.profile.mypost.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.model.LikeListResponse;
import com.bang.module.home.profile.mypost.model.MyPostResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostPresenter {
    private ApiCallback.MyPostCallback myPostCallback;
    private Context mContext;
    private Session session;

    public MyPostPresenter(ApiCallback.MyPostCallback myPostCallback, Context mContext) {
        this.myPostCallback = myPostCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void myPostCallApi(int offset) {
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<MyPostResponse> getProfileResponseCall = api.getMyFeedListApi(session.getAuthToken(),
                    String.valueOf(offset),"10");
            getProfileResponseCall.enqueue(new Callback<MyPostResponse>() {
                @Override
                public void onResponse(@NonNull Call<MyPostResponse> call, @NonNull Response<MyPostResponse> response) {
                    myPostCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        myPostCallback.onSuccessMyPost(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                myPostCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                myPostCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<MyPostResponse> call, @NonNull Throwable t) {
                    myPostCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        myPostCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        myPostCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }


    public void myPostLikeListApi(String newsfeedId,int offset) {
        myPostCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<LikeListResponse> getProfileResponseCall = api.getFeedLikeListApi(session.getAuthToken(),
                    newsfeedId,String.valueOf(offset),"10");
            getProfileResponseCall.enqueue(new Callback<LikeListResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeListResponse> call, @NonNull Response<LikeListResponse> response) {
                    myPostCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        myPostCallback.onSuccessListList(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                myPostCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                myPostCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<LikeListResponse> call, @NonNull Throwable t) {
                    myPostCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        myPostCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        myPostCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}