package com.bang.module.home.newsfeed.presenter;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.model.ReportUserModel;
import com.bang.module.home.newsfeed.model.SelectReasonsModel;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportToUserPresenter {
    private ApiCallback.ReportToUserCallback reportToUserCallback;
    private Context mContext;
    private Session session;

    public ReportToUserPresenter(ApiCallback.ReportToUserCallback reportToUserCallback, Context mContext) {
        this.reportToUserCallback = reportToUserCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callSelectReasonsApi() {
        reportToUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            final API api = ServiceGenerator.createService(API.class);
            Call<SelectReasonsModel> selectReasonsCall = api.callGetReasonsApi(session.getAuthToken());
            selectReasonsCall.enqueue(new Callback<SelectReasonsModel>() {
                @Override
                public void onResponse(@NonNull Call<SelectReasonsModel> call, @NonNull Response<SelectReasonsModel> response) {
                    reportToUserCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        reportToUserCallback.onSuccessSelectReasons(response.body());
                    } else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                reportToUserCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                reportToUserCallback.onError(apiErrors.getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SelectReasonsModel> call, @NonNull Throwable t) {
                    reportToUserCallback.onHideBaseLoader();
                    if (t instanceof IOException) {
                        reportToUserCallback.onError(mContext.getString(R.string.internet_connection));
                    } else {
                        reportToUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }


    public void callReportToUserApi(String reporUserId,String newsFeedId,String reasons ,String descriptions ) {
        reportToUserCallback.onShowBaseLoader();
        if (AppHelper.isConnectingToInternet(mContext)) {
            final API api = ServiceGenerator.createService(API.class);
            Call<ReportUserModel> reportToUserResponseCall = api.callReportUserApi(session.getAuthToken()
            ,reporUserId,newsFeedId,reasons , descriptions);
            reportToUserResponseCall.enqueue(new Callback<ReportUserModel>() {
                @Override
                public void onResponse(@NonNull Call<ReportUserModel> call, @NonNull Response<ReportUserModel> response) {
                    reportToUserCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        reportToUserCallback.onSuccessReportToUser(response.body());
                    } else {
                        try {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                reportToUserCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                reportToUserCallback.onError(apiErrors.getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ReportUserModel> call, @NonNull Throwable t) {
                    reportToUserCallback.onHideBaseLoader();
                    if (t instanceof IOException) {
                        reportToUserCallback.onError(mContext.getString(R.string.internet_connection));
                    } else {
                        reportToUserCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}