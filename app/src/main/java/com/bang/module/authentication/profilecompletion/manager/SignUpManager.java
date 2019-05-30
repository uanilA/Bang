package com.bang.module.authentication.profilecompletion.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.bang.R;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.errorResponse.ServiceGenerator;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.serverhandling.API;
import com.bang.serverhandling.ApiCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpManager {
    private ApiCallback.SignUpCallback signUpCallback;
    Context mContext;

    public SignUpManager(ApiCallback.SignUpCallback signUpCallback, Context mContext) {
        this.signUpCallback = signUpCallback;
        this.mContext = mContext;
    }

    public void callSignUoApi(String fullName1, String countryCode1, String phoneNumber1, String deviceToken1, String deviceType1,
                              String signupFrom1, Uri uri,String socialType,String socialId , String facebookimage1) {
        signUpCallback.onShowBaseLoader();
        RequestBody fullName = RequestBody.create(MediaType.parse("text/plain"), fullName1);
        RequestBody countryCode = RequestBody.create(MediaType.parse("text/plain"), countryCode1);
        RequestBody phoneNumber = RequestBody.create(MediaType.parse("text/plain"), phoneNumber1);
        RequestBody deviceToken = RequestBody.create(MediaType.parse("text/plain"), deviceToken1);
        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), deviceType1);
        RequestBody signupFrom = RequestBody.create(MediaType.parse("text/plain"), signupFrom1);
        MultipartBody.Part body = null;
        if (uri !=null) {
            body = prepareFilePart(mContext, "profilePhoto", uri);
        }
        API api = ServiceGenerator.createService(API.class);
        Call<SignUpResponse> changePasswordResponseCall;
        if (facebookimage1.equals("")) {
            changePasswordResponseCall = api.callSignUpApi(fullName, countryCode, phoneNumber, deviceToken
                    , deviceType, signupFrom, body);
        } else {
            changePasswordResponseCall = api.callSignUpApi1(fullName1, countryCode1, phoneNumber1, deviceToken1
                    , deviceType1, signupFrom1,"",socialType,socialId, facebookimage1);
        }
        changePasswordResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpCallback.onHideBaseLoader();
                if (response.isSuccessful()) {
                    signUpCallback.onSuccessSignUp(response.body());
                } else {
                    APIErrors apiErrors = ErrorUtils.parseError(response);
                    signUpCallback.onError(apiErrors.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                signUpCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    signUpCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    signUpCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }

    private static MultipartBody.Part prepareFilePart(Context context, String partName, Uri uri) {
        File file = new File(getRealPathFromURI(context, uri));
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(uri)),
                        file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, "Image.jpg", requestFile);
    }

    private static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
