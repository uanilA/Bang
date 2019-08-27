package com.bang.module.home.newsfeed.presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.model.NewsFeedResponse;
import com.bang.module.home.profile.followersfollowing.model.LikeResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedPresenter  {
    private ApiCallback.NewsFeedCallback newsFeedCallback;
    private Context mContext;
    private Session session;
    public NewsFeedPresenter(ApiCallback.NewsFeedCallback newsFeedCallback, Context mContext) {
        this.newsFeedCallback = newsFeedCallback;
        this.mContext = mContext;
         session = new Session(mContext);
    }

    public void newsFeedApiCalling(int offset){
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+session.getAuthToken());
      //newsFeedCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<NewsFeedResponse> getProfileResponseCall = api.callNewsFeedApi(session.getAuthToken(),
                    String.valueOf(offset),"20");
            getProfileResponseCall.enqueue(new Callback<NewsFeedResponse>() {
                @Override
                public void onResponse(@NonNull Call<NewsFeedResponse> call, @NonNull Response<NewsFeedResponse> response) {
                    newsFeedCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        newsFeedCallback.onSuccessNewsFeed(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                newsFeedCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                newsFeedCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<NewsFeedResponse> call, @NonNull Throwable t) {
                    newsFeedCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        newsFeedCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        newsFeedCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }

    public void newsLikeApiCalling(int newsfeedId){
        newsFeedCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<LikeResponse> getProfileResponseCall = api.callLikeApi(session.getAuthToken(),
                    String.valueOf(newsfeedId));
            getProfileResponseCall.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {
                    newsFeedCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        newsFeedCallback.onSuccessLike(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                newsFeedCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                newsFeedCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<LikeResponse> call, @NonNull Throwable t) {
                    newsFeedCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        newsFeedCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        newsFeedCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}