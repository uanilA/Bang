package com.bang.module.authentication.profilecompletion.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.bang.R;
import com.bang.errorResponse.APIErrors;
import com.bang.errorResponse.ErrorUtils;
import com.bang.network.ServiceGenerator;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
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

public class SignUpManager {
    private ApiCallback.SignUpCallback signUpCallback;
    private Context mContext;

    public SignUpManager(ApiCallback.SignUpCallback signUpCallback, Context mContext) {
        this.signUpCallback = signUpCallback;
        this.mContext = mContext;
    }

    public void callSignUpApi(String fullName1, String email1,String password1 ,String countryCode1, String phoneNumber1, String deviceToken1, String deviceType1,
                              String signupFrom1, Uri uri, String socialType, String socialId, String facebookimage1) {
        signUpCallback.onShowBaseLoader();
        RequestBody fullName    = RequestBody.create(MediaType.parse("text/plain"), fullName1);
        RequestBody countryCode = RequestBody.create(MediaType.parse("text/plain"), countryCode1);
        RequestBody phoneNumber = RequestBody.create(MediaType.parse("text/plain"), phoneNumber1);
        RequestBody deviceToken = RequestBody.create(MediaType.parse("text/plain"), deviceToken1);
        RequestBody deviceType  = RequestBody.create(MediaType.parse("text/plain"), deviceType1);
        RequestBody signupFrom  = RequestBody.create(MediaType.parse("text/plain"), signupFrom1);
        RequestBody email       = RequestBody.create(MediaType.parse("text/plain"), email1);
        RequestBody password    = RequestBody.create(MediaType.parse("text/plain"), password1);
        MultipartBody.Part body = null;
        if (uri != null) {
            if (String.valueOf(uri).contains("file:")) {
                File imageFile = new File(uri.toString());
                String filePath = imageFile.getAbsolutePath();
                Cursor cursor = mContext.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID},
                        MediaStore.Images.Media.DATA + "=? ",
                        new String[]{filePath}, null);

                if (cursor != null && cursor.moveToFirst()) {
                    Uri baseUri = Uri.parse("content://media/external/images/media");
                    body = prepareFilePart(mContext, "profilePhoto", baseUri);
                }
            } else {
                body = prepareFilePart(mContext, "profilePhoto", uri);
            }
        }
        API api = ServiceGenerator.createService(API.class);
        Call<SignUpResponse> signUpResponseCall;
        if (facebookimage1.equals("")) {
            signUpResponseCall = api.callSignUpApi(fullName,email,password, countryCode, phoneNumber, deviceToken
                    , deviceType, body);
        } else {
            RequestBody socialType1 = RequestBody.create(MediaType.parse("text/plain"), socialType);
            RequestBody socialId1 = RequestBody.create(MediaType.parse("text/plain"), socialId);
            if (uri != null) {
                signUpResponseCall = api.callSignUpApi2(fullName, countryCode, phoneNumber,email, deviceToken
                        , deviceType, socialType1, socialId1,body);
            } else {
                signUpResponseCall = api.callSignUpApi1(fullName1, countryCode1, phoneNumber1,email1, deviceToken1
                        , deviceType1, facebookimage1, socialType, socialId);
            }
        }
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                try {
                    signUpCallback.onHideBaseLoader();
                    if (response.isSuccessful()) {
                        signUpCallback.onSuccessSignUp(response.body());
                    } else {
                        APIErrors apiErrors = ErrorUtils.parseError(response);
                        signUpCallback.onError(apiErrors.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                signUpCallback.onHideBaseLoader();
                if (t instanceof IOException) {
                    signUpCallback.onError(mContext.getString(R.string.internet_connection));
                } else {
                    signUpCallback.onError(mContext.getString(R.string.oops_wrong));
                }
            }
        });
    }
}
