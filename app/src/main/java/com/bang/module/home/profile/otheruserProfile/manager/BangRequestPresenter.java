package com.bang.module.home.profile.otheruserProfile.manager;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.bandrequest.model.BangRequestsModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangRequestPresenter {

    private ApiCallback.SendBangRequestCallback sendBangRequestCallback;
    private Context mContext;
    private Session session;

    public BangRequestPresenter(ApiCallback.SendBangRequestCallback sendBangRequestCallback, Context mContext) {
        this.sendBangRequestCallback = sendBangRequestCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callBangRequest(String forUserId){
        if (AppHelper.isConnectingToInternet(mContext)) {
            sendBangRequestCallback.onShowBaseLoader();
            API api = ServiceGenerator.createService(API.class);
            Call<BangRequestsModel> requestModelCall = api.callsendRequestApi(session.getAuthToken(),forUserId);
            requestModelCall.enqueue(new Callback<BangRequestsModel>() {
                @Override
                public void onResponse(@NonNull Call<BangRequestsModel> call, @NonNull Response<BangRequestsModel> response) {
                    sendBangRequestCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        sendBangRequestCallback.onBangRequestSuccess(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                sendBangRequestCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                sendBangRequestCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<BangRequestsModel> call, @NonNull Throwable t) {
                    sendBangRequestCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        sendBangRequestCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        sendBangRequestCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
