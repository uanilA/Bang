package com.bang.module.preference.manager;


import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.module.preference.model.PreferenceResponse;
import com.bang.module.preference.model.SaveQuestionResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anil Upadhyay on 07/06/2019.
 */

public class PreferenceManager {
    private ApiCallback.PreferenceCallback preferenceCallback;
    private Context mContext;
    private Session session;

    public PreferenceManager(ApiCallback.PreferenceCallback preferenceCallback, Context mContext) {
        this.preferenceCallback = preferenceCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callPreferenceApi(String gender, String preference) {
        preferenceCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<PreferenceResponse> genderApi = api.callPreferenceApi(session.getAuthToken(), gender, preference);
        genderApi.enqueue(new Callback<PreferenceResponse>() {
            @Override
            public void onResponse(@NonNull Call<PreferenceResponse> call, @NonNull Response<PreferenceResponse> response) {
                preferenceCallback.onHideBaseLoader();
                if (response.isSuccessful()) {
                    preferenceCallback.onSuccessPreference(response.body());
                } else {
                    APIErrors apiErrors = ErrorUtils.parseError(response);
                    if (apiErrors.getMessage().equals("Invalid auth token")) {
                        preferenceCallback.onTokenChangeError(apiErrors.getMessage());
                    } else {
                        preferenceCallback.onError(apiErrors.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PreferenceResponse> call, @NonNull Throwable t) {
                preferenceCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    preferenceCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    preferenceCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }


    public void callSaveQuestionApi(String questionId, String optionId) {
        preferenceCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<SaveQuestionResponse> genderApi = api.callSaveQuestionsApi(session.getAuthToken(), questionId, optionId);
        genderApi.enqueue(new Callback<SaveQuestionResponse>() {
            @Override
            public void onResponse(@NonNull Call<SaveQuestionResponse> call, @NonNull Response<SaveQuestionResponse> response) {
                preferenceCallback.onHideBaseLoader();
                if (response.isSuccessful()) {
                    preferenceCallback.onSuccessSaveQuestions(response.body());
                } else {
                    APIErrors apiErrors = ErrorUtils.parseError(response);
                    if (apiErrors.getMessage().equals("Invalid auth token")) {
                        preferenceCallback.onTokenChangeError(apiErrors.getMessage());
                    } else {
                        preferenceCallback.onError(apiErrors.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SaveQuestionResponse> call, @NonNull Throwable t) {
                preferenceCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    preferenceCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    preferenceCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }
}