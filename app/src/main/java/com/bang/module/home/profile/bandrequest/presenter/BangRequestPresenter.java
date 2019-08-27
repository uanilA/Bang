package com.bang.module.home.profile.bandrequest.presenter;

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
    private ApiCallback.BangRequestsDataCallback bangRequestsDataCallback;
    private Context context;
    private Session session;

    public BangRequestPresenter(ApiCallback.BangRequestsDataCallback bangRequestsDataCallback, Context context) {
        this.bangRequestsDataCallback = bangRequestsDataCallback;
        this.context = context;
        session = new Session(context);
    }

    public void bangRequestApiCall(int offset){
        if (AppHelper.isConnectingToInternet(context)){
            bangRequestsDataCallback.onShowBaseLoader();
            API api = ServiceGenerator.createService(API.class);
            Call<BangRequestsModel> surveyReceiveResponseCall = api.callBangRequestApi(session.getAuthToken(),String.valueOf(offset),"");
            surveyReceiveResponseCall.enqueue(new Callback<BangRequestsModel>() {
                @Override
                public void onResponse(@NonNull Call<BangRequestsModel> call, @NonNull Response<BangRequestsModel> response) {
                    bangRequestsDataCallback.onHideBaseLoader();
                    if (response.isSuccessful()){
                        bangRequestsDataCallback.onSuccessRequests(response.body());
                    }else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                bangRequestsDataCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                bangRequestsDataCallback.onError(apiErrors.getMessage());
                            }
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
                @Override
                public void onFailure(@NonNull Call<BangRequestsModel> call, @NonNull Throwable t) {
                    bangRequestsDataCallback.onHideBaseLoader();
                    if (t instanceof IOException){
                        bangRequestsDataCallback.onError(context.getString(R.string.internet_connection));
                    }else {
                        bangRequestsDataCallback.onError(context.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(context).showToast(context, context.getString(R.string.alert_no_network));
        }
    }
}