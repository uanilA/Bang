package com.bang.module.home.newsfeed.presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.model.AddNewsResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import com.bang.network.ServiceGenerator;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bang.utils.AppUtils.prepareFilePart;

public class AddNewsFeedPresenter {
    private ApiCallback.AddNewsFeedCallback addNewsFeedCallback;
    private Context mContext;
    private Session session;


    public AddNewsFeedPresenter(ApiCallback.AddNewsFeedCallback addNewsFeedCallback, Context mContext) {
        this.addNewsFeedCallback = addNewsFeedCallback;
        this.mContext = mContext;
        session = new Session(mContext);
    }

    public void callAddNewsFeedApi(String title1, Uri image, File video, File thumbVideo) {
        addNewsFeedCallback.onShowBaseLoader();
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), title1);

        MultipartBody.Part body = null;
        MultipartBody.Part body1 = null;
        MultipartBody.Part body2 = null;
        if (image != null) {
            if (String.valueOf(image).contains("file:")) {
                File imageFile = new File(image.toString());
                String filePath = imageFile.getAbsolutePath();
                Cursor cursor = mContext.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID},
                        MediaStore.Images.Media.DATA + "=? ",
                        new String[]{filePath}, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.MediaColumns._ID));
                    Uri baseUri = Uri.parse("content://media/external/images/media");
                    body = prepareFilePart(mContext, "photo", baseUri);
                }
            } else {
                body = prepareFilePart(mContext, "photo", image);
            }
        } else {
            body = MultipartBody.Part.createFormData("photo", "");
        }

        if (thumbVideo != null) {
           /* if (String.valueOf(thumbVideo).contains("file:")) {
                File imageFile = new File(thumbVideo.toString());
                String filePath = imageFile.getAbsolutePath();
                Cursor cursor = mContext.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID},
                        MediaStore.Images.Media.DATA + "=? ",
                        new String[]{filePath}, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.MediaColumns._ID));
                    Uri baseUri = Uri.parse("content://media/external/images/media");
                    body1 = prepareFilePart(mContext, "videoThumb", baseUri);
                }
            } else {*/
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), thumbVideo);
            body1 = MultipartBody.Part.createFormData( "videoThumb",thumbVideo.getName(), requestBody);
          //  }
        } else {
            body1 = MultipartBody.Part.createFormData("videoThumb", "");
        }
        if (video != null) {
            RequestBody videoBody = RequestBody.create(MediaType.parse("video/mp4"), video);
            body2 = MultipartBody.Part.createFormData("video", video.getName(), videoBody);
        } else {
            body2 = MultipartBody.Part.createFormData("video", "");
        }


        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<AddNewsResponse> loginResponseCall = api.callAddNewsFeedApi(session.getAuthToken(), title, body, body1, body2);
            loginResponseCall.enqueue(new Callback<AddNewsResponse>() {
                @Override
                public void onResponse(@NonNull Call<AddNewsResponse> call, @NonNull Response<AddNewsResponse> response) {
                    addNewsFeedCallback.onHideBaseLoader();
                    try {
                        if (response.isSuccessful()) {
                            addNewsFeedCallback.onSuccessAddNewsFeed(response.body());
                        } else {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                addNewsFeedCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                addNewsFeedCallback.onError(apiErrors.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AddNewsResponse> call, @NonNull Throwable t) {
                    addNewsFeedCallback.onHideBaseLoader();
                    if (t instanceof IOException) {
                        addNewsFeedCallback.onError(mContext.getString(R.string.internet_connection));
                    } else {
                        addNewsFeedCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }

}