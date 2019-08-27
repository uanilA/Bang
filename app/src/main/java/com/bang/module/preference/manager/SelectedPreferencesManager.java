package com.bang.module.preference.manager;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.module.preference.model.SelectedReferencesResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedPreferencesManager {
    private ApiCallback.SelectedPreferenceCallback selectedPreferenceCallback;
    private Context mContext;
    private Session session;

    public SelectedPreferencesManager(ApiCallback.SelectedPreferenceCallback selectedPreferenceCallback, Context mContext) {
        this.selectedPreferenceCallback = selectedPreferenceCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callPreferenceApi(String preference) {
        selectedPreferenceCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<SelectedReferencesResponse> genderApi = api.callSelectedPreferenceApi(session.getAuthToken(), preference);
        genderApi.enqueue(new Callback<SelectedReferencesResponse>() {
            @Override
            public void onResponse(@NonNull Call<SelectedReferencesResponse> call, @NonNull Response<SelectedReferencesResponse> response) {
                selectedPreferenceCallback.onHideBaseLoader();
                if (response.isSuccessful()) {
                    selectedPreferenceCallback.onSuccessSelectedPreference(response.body());
                } else {
                    try {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            selectedPreferenceCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            selectedPreferenceCallback.onError(apiErrors.getMessage());
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
            }

            @Override
            public void onFailure(@NonNull Call<SelectedReferencesResponse> call, @NonNull Throwable t) {
                selectedPreferenceCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    selectedPreferenceCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    selectedPreferenceCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }
}
