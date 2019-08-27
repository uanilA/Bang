package com.bang.module.home.addsurvey.manager;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.model.ShareSurveyModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareSurveyPresenter {
    private ApiCallback.ShareSurveyCallback shareSurveyCallback;
    private Context mContext;
    private Session session;

    public ShareSurveyPresenter(ApiCallback.ShareSurveyCallback shareSurveyCallback, Context mContext) {
        this.shareSurveyCallback = shareSurveyCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void shareSurveyApiCall(String title, String surveyUserId, String surveyId, String is_anonymous, String survey_score) {
        shareSurveyCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            final Call<ShareSurveyModel> shareSurveyModelCall = api.shareApi(session.getAuthToken(), title, surveyId, surveyUserId
                    , is_anonymous, survey_score);
            shareSurveyModelCall.enqueue(new Callback<ShareSurveyModel>() {
                @Override
                public void onResponse(@NonNull Call<ShareSurveyModel> call, @NonNull Response<ShareSurveyModel> response) {
                    shareSurveyCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        shareSurveyCallback.onSuccessSharePost(response.body());
                    } else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            shareSurveyCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            shareSurveyCallback.onError(apiErrors.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ShareSurveyModel> call, @NonNull Throwable t) {
                    shareSurveyCallback.onHideBaseLoader();
                    if (t instanceof IOException) {
                        shareSurveyCallback.onError(mContext.getString(R.string.internet_connection));
                    } else {
                        shareSurveyCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}