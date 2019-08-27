package com.bang.module.home.survey.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.survey.model.ViewSurveyModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSurveyManager {
    private ApiCallback.ViewSurveyCallback viewSurveyCallback;
    private Context mContext;
    private Session session;

    public ViewSurveyManager(ApiCallback.ViewSurveyCallback viewSurveyCallback, Context mContext) {
        this.viewSurveyCallback = viewSurveyCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callViewSurveyList(String orderId){
        viewSurveyCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<ViewSurveyModel> loginResponseCall = api.callAllViewSurveyApi(session.getAuthToken(),orderId);
            loginResponseCall.enqueue(new Callback<ViewSurveyModel>() {
                @Override
                public void onResponse(@NonNull Call<ViewSurveyModel> call, @NonNull Response<ViewSurveyModel> response) {
                    viewSurveyCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        viewSurveyCallback.OnSuccessViewResponse(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            viewSurveyCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            viewSurveyCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ViewSurveyModel> call, @NonNull Throwable t) {
                    viewSurveyCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        viewSurveyCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        viewSurveyCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}