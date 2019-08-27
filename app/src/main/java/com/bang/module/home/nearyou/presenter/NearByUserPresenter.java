package com.bang.module.home.nearyou.presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.nearyou.model.NearbyUsersModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearByUserPresenter {
     private ApiCallback.NearByUserCallback nearByUserCallback;
     private Context mContext;

    public NearByUserPresenter(ApiCallback.NearByUserCallback nearByUserCallback, Context mContext) {
        this.nearByUserCallback = nearByUserCallback;
        this.mContext = mContext;
    }

    public void nearByUserCall(String latitude , String longitude ,int offset ,String limit){
        Session session = new Session(mContext);
        nearByUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<NearbyUsersModel> nearByUserCall = api.callNearbyUserApi(session.getAuthToken(),latitude ,longitude,String.valueOf(offset),limit);
            nearByUserCall.enqueue(new Callback<NearbyUsersModel>() {
                @Override
                public void onResponse(@NonNull Call<NearbyUsersModel> call, @NonNull Response<NearbyUsersModel> response) {
                    nearByUserCallback.onHideBaseLoader();
                    try {
                        if (response.isSuccessful()) {
                            nearByUserCallback.onSuccessNearUsers(response.body());
                        } else {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                nearByUserCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                nearByUserCallback.onError(apiErrors.getMessage());
                            }
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
                @Override
                public void onFailure(@NonNull Call<NearbyUsersModel> call, @NonNull Throwable t) {
                    nearByUserCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        nearByUserCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        nearByUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
    }
}
