package com.bang.module.authentication.genderselection.manager;



import android.content.Context;
import android.support.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.authentication.genderselection.model.UpdateLocationResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Anil Upadhyay on 25/05/2019.
 */

public class GenderManager {
    private ApiCallback.GenderSelectionCallback genderSelectionCallback;
    Context mContext;
    Session session;

    public GenderManager(ApiCallback.GenderSelectionCallback genderSelectionCallback, Context mContext) {
        this.genderSelectionCallback = genderSelectionCallback;
        this.mContext=mContext;
        session = new Session(mContext);
    }

    public void callUpdateGenderApi(String gender, String preference){
        genderSelectionCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<UpdateGenderResponse> genderApi = api.callUpdateGenderApi(session.getAuthToken(),gender,preference);
        genderApi.enqueue(new Callback<UpdateGenderResponse>() {
            @Override
            public void onResponse(Call<UpdateGenderResponse> call, @NonNull Response<UpdateGenderResponse> response) {
                genderSelectionCallback.onHideBaseLoader();
                try {
                    if (response.isSuccessful()) {
                        genderSelectionCallback.onGenderSelectionResponse(response.body());
                    } else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            genderSelectionCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            genderSelectionCallback.onError(apiErrors.getMessage());
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
            }
            @Override
            public void onFailure(@NonNull Call<UpdateGenderResponse> call, @NonNull Throwable t) {
                genderSelectionCallback.onHideBaseLoader();
                if(t instanceof IOException){
                    genderSelectionCallback.onError(mContext.getString(R.string.internet_connection));
                }else {
                    genderSelectionCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }


    public void callUpdateLocationApi(String latitude, String longitude){
        genderSelectionCallback.onShowBaseLoader();
        final API api = ServiceGenerator.createService(API.class);
        Call<UpdateLocationResponse> genderApi = api.callUpdateLocationApi(session.getAuthToken(),latitude,longitude);
        genderApi.enqueue(new Callback<UpdateLocationResponse>() {
            @Override
            public void onResponse(Call<UpdateLocationResponse> call, @NonNull Response<UpdateLocationResponse> response) {
                genderSelectionCallback.onHideBaseLoader();
                try {
                    if (response.isSuccessful()) {
                        genderSelectionCallback.onUpdateLocationResponse(response.body());
                    } else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        if (apiErrors.getMessage().equals("Invalid auth token")) {
                            genderSelectionCallback.onTokenChangeError(apiErrors.getMessage());
                        } else {
                            genderSelectionCallback.onError(apiErrors.getMessage());
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
            }
            @Override
            public void onFailure(@NonNull Call<UpdateLocationResponse> call, @NonNull Throwable t) {
                genderSelectionCallback.onHideBaseLoader();
                if(t instanceof IOException){
                    genderSelectionCallback.onError(mContext.getString(R.string.internet_connection));
                }else {
                    genderSelectionCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }
}