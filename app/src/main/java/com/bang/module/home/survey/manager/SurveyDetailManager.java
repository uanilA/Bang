package com.bang.module.home.survey.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.survey.model.DocommentResponse;
import com.bang.module.home.survey.model.SurveyDetailModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyDetailManager {
    private ApiCallback.ViewSurveyDetailCallback viewSurveyDetailCallback;
    private Context mContext;
    private Session session;

    public SurveyDetailManager(ApiCallback.ViewSurveyDetailCallback viewSurveyDetailCallback, Context mContext) {
        this.viewSurveyDetailCallback = viewSurveyDetailCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callViewSurveyDetail(String surveyId, String type , String offset , String limit){
        viewSurveyDetailCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<SurveyDetailModel> loginResponseCall = api.callSurveyDetailsApi(session.getAuthToken(),surveyId ,type,offset,limit);
            loginResponseCall.enqueue(new Callback<SurveyDetailModel>() {
                @Override
                public void onResponse(@NonNull Call<SurveyDetailModel> call, @NonNull Response<SurveyDetailModel> response) {
                    viewSurveyDetailCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        viewSurveyDetailCallback.onSuccessViewSurveyDetail(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            viewSurveyDetailCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            viewSurveyDetailCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<SurveyDetailModel> call, @NonNull Throwable t) {
                    viewSurveyDetailCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        viewSurveyDetailCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        viewSurveyDetailCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }


    public void callDoComment(String surveyId, String comment){
        viewSurveyDetailCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<DocommentResponse> loginResponseCall = api.callDoCommentApi(session.getAuthToken(),surveyId ,comment);
            loginResponseCall.enqueue(new Callback<DocommentResponse>() {
                @Override
                public void onResponse(@NonNull Call<DocommentResponse> call, @NonNull Response<DocommentResponse> response) {
                    viewSurveyDetailCallback.onHideBaseLoader();
                    if(response.isSuccessful()){
                        viewSurveyDetailCallback.OnSuccessComment(response.body());
                    }else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            viewSurveyDetailCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            viewSurveyDetailCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<DocommentResponse> call, @NonNull Throwable t) {
                    viewSurveyDetailCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        viewSurveyDetailCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        viewSurveyDetailCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
