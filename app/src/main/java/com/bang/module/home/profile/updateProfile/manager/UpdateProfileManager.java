package com.bang.module.home.profile.updateProfile.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.updateProfile.model.UpdateProfileResponse;
import com.bang.network.API;
import com.bang.network.ApiCallback;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.bang.utils.AppUtils.prepareFilePart;

public class UpdateProfileManager {
    private ApiCallback.UpdateProfileCallback updateProfileCallback;
    private Context mContext;


    public UpdateProfileManager(ApiCallback.UpdateProfileCallback updateProfileCallback , Context mContext) {
        this.updateProfileCallback = updateProfileCallback;
        this.mContext=mContext;
    }

    public void callGetMyProfile(String fullName1, Uri uri, String mobileNumber1 , String countryCode1
    ,String countryFlagCode1){
        Session session = new Session(mContext);
        updateProfileCallback.onShowBaseLoader();
        RequestBody fullName = RequestBody.create(MediaType.parse("text/plain"), fullName1);
        RequestBody mobileNumber = RequestBody.create(MediaType.parse("text/plain"), mobileNumber1);
        RequestBody countryCode = RequestBody.create(MediaType.parse("text/plain"), countryCode1);
        RequestBody countryFlagCode = RequestBody.create(MediaType.parse("text/plain"),countryFlagCode1);

        MultipartBody.Part body = null;
        if (uri !=null) {
            if (String.valueOf(uri).contains("file:")){
                File imageFile=new File(uri.toString());
                String filePath = imageFile.getAbsolutePath();
                Cursor cursor = mContext.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.Media._ID },
                        MediaStore.Images.Media.DATA + "=? ",
                        new String[] { filePath }, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.MediaColumns._ID));
                    Uri baseUri = Uri.parse("content://media/external/images/media");
                    body = prepareFilePart(mContext, "profilePhoto", baseUri);
                }
            }else {
                body = prepareFilePart(mContext, "profilePhoto", uri);
            }
        }else {
            body = MultipartBody.Part.createFormData("profilePhoto", "");
        }
        if (AppHelper.isConnectingToInternet(mContext)) {
            API api = ServiceGenerator.createService(API.class);
            Call<UpdateProfileResponse> updateProfileResponseCall = api.callUpdateProfileApi(session.getAuthToken(),fullName,mobileNumber,countryCode,
                    countryFlagCode,body);
            updateProfileResponseCall.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(@NonNull Call<UpdateProfileResponse> call, @NonNull Response<UpdateProfileResponse> response) {
                    updateProfileCallback.onHideBaseLoader();
                    try {
                        if (response.isSuccessful()) {
                            updateProfileCallback.onSuccessUpdateProfile(response.body());
                        } else {
                            APIErrors apiErrors = ErrorUtils.parseError(response);
                            if (apiErrors.getMessage().equals("Invalid auth token")) {
                                updateProfileCallback.onTokenChangeError(apiErrors.getMessage());
                            } else {
                                updateProfileCallback.onError(apiErrors.getMessage());
                            }
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
                @Override
                public void onFailure(@NonNull Call<UpdateProfileResponse> call, @NonNull Throwable t) {
                    updateProfileCallback.onHideBaseLoader();
                    if(t instanceof IOException){
                        updateProfileCallback.onError(mContext.getString(R.string.internet_connection));
                    }else {
                        updateProfileCallback.onError(mContext.getString(R.string.oops_wrong));
                    }
                }
            });
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, mContext.getString(R.string.alert_no_network));
        }
    }
}
