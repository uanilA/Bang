package com.bang.module.home.profile.bandrequest.presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.bandrequest.model.AcceptRejectModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangRequestUpdatePresenter {

    private ApiCallback.BangRequestUpdateCallback bangRequestUpdateCallback;
    private Context mContext;

    public BangRequestUpdatePresenter(ApiCallback.BangRequestUpdateCallback bangRequestUpdateCallback, Context mContext) {
        this.bangRequestUpdateCallback = bangRequestUpdateCallback;
        this.mContext = mContext;
    }

    public void requestUpdateApiCall(String bangConnectId,String requestStatus){
        Session session= new Session(mContext);
        if (AppHelper.isConnectingToInternet(mContext)){
            bangRequestUpdateCallback.onShowBaseLoader();
            API api = ServiceGenerator.createService(API.class);
            Call<AcceptRejectModel> surveyReceiveResponseCall = api.callAcceptRejectApi(session.getAuthToken(),bangConnectId,requestStatus);
            surveyReceiveResponseCall.enqueue(new Callback<AcceptRejectModel>() {
                @Override
                public void onResponse(@NonNull Call<AcceptRejectModel> call, @NonNull Response<AcceptRejectModel> response) {
                    bangRequestUpdateCallback.onHideBaseLoader();
                    if (response.isSuccessful()){
                        bangRequestUpdateCallback.onSuccessRequestUpdate(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                bangRequestUpdateCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                bangRequestUpdateCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<AcceptRejectModel> call, @NonNull Throwable t) {
                    bangRequestUpdateCallback.onHideBaseLoader();
                    if (t instanceof IOException){
                        bangRequestUpdateCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        bangRequestUpdateCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}