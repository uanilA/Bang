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
import com.bang.module.home.survey.model.SurveySentResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentSurveyManager {
    private ApiCallback.GetSurveyCallback surveyCallback;
    private Context mContext;
    private Session session;

    public SentSurveyManager(ApiCallback.GetSurveyCallback surveyCallback, Context mContext) {
        this.surveyCallback = surveyCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callAllUserList(String type, String limit, int offset){
        surveyCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<SurveySentResponse> loginResponseCall = api.callSurveyResponse(session.getAuthToken(),type ,limit,String.valueOf(offset));
            loginResponseCall.enqueue(new Callback<SurveySentResponse>() {
                @Override
                public void onResponse(@NonNull Call<SurveySentResponse> call, @NonNull Response<SurveySentResponse> response) {
                    surveyCallback.onHideBaseLoader();
                    try {
                        if (response.isSuccessful()) {
                            surveyCallback.OnSuccessSurveyResponse(response.body());
                        } else {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                surveyCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                surveyCallback.onError(apiErrors.getMessage());
                            }
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
                @Override
                public void onFailure(@NonNull Call<SurveySentResponse> call, @NonNull Throwable t) {
                    surveyCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        surveyCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        surveyCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
