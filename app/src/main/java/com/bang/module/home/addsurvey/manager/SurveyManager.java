package com.bang.module.home.addsurvey.manager;


import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.module.home.addsurvey.model.CreateSurveyResponse;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anil Upadhyay on 07/06/2019.
 */

public class SurveyManager {
    private ApiCallback.SurveyCallback surveyCallback;
    private Context mContext;
    private Session session;

    public SurveyManager(ApiCallback.SurveyCallback surveyCallback, Context mContext) {
        this.surveyCallback = surveyCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callGetSurveyQuestionApi(String gender, String preference) {
        surveyCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<SurveyQuestionResponse> genderApi = api.callSurveyApi(session.getAuthToken(), gender, preference);
        genderApi.enqueue(new Callback<SurveyQuestionResponse>() {
            @Override
            public void onResponse(@NonNull Call<SurveyQuestionResponse> call, @NonNull Response<SurveyQuestionResponse> response) {
                surveyCallback.onHideBaseLoader();
                if (response.isSuccessful()) {
                    surveyCallback.onSuccessSurvey(response.body());
                } else {
                    APIErrors apiErrors = ErrorUtils.parseError(response);
                    if (apiErrors.getMessage().equals("Invalid auth token")) {
                        surveyCallback.onTokenChangeError(apiErrors.getMessage());
                    } else {
                        surveyCallback.onError(apiErrors.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SurveyQuestionResponse> call, @NonNull Throwable t) {
                surveyCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    surveyCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    surveyCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }

    public void callCreateSurveyApi(String date, String time, String forUserId, String gender ,String userFullName
            , String newJson) {
        surveyCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<CreateSurveyResponse> genderApi = api.callCreateSurveyApi(session.getAuthToken(), date, time,forUserId ,gender,newJson,
                userFullName);
        genderApi.enqueue(new Callback<CreateSurveyResponse>() {
            @Override
            public void onResponse(@NonNull Call<CreateSurveyResponse> call, @NonNull Response<CreateSurveyResponse> response) {
                surveyCallback.onHideBaseLoader();
               try {
                   if (response.isSuccessful()) {
                       surveyCallback.onSuccessCreateSurvey(response.body());
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
            public void onFailure(@NonNull Call<CreateSurveyResponse> call, @NonNull Throwable t) {
                surveyCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    surveyCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    surveyCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }

}