package com.bang.module.home.profile.otheruserProfile.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.otheruserProfile.model.OtherUserProfileResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherUserPresenter {
    private ApiCallback.OtherUserCallback otherUserCallback;
    private Context mContext;
    private Session session;

    public OtherUserPresenter(ApiCallback.OtherUserCallback otherUserCallback, Context mContext) {
        this.otherUserCallback=otherUserCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callOtherUSerProfile(String userId){
        otherUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<OtherUserProfileResponse> getProfileResponseCall = api.callOtherUserProfileApi(session.getAuthToken(),userId);
            getProfileResponseCall.enqueue(new Callback<OtherUserProfileResponse>() {
                @Override
                public void onResponse(@NonNull Call<OtherUserProfileResponse> call, @NonNull Response<OtherUserProfileResponse> response) {
                    otherUserCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        otherUserCallback.onSuccessOtherProfile(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                otherUserCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                otherUserCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<OtherUserProfileResponse> call, @NonNull Throwable t) {
                    otherUserCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        otherUserCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        otherUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
