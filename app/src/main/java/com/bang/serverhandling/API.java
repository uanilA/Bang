package com.bang.serverhandling;


import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.module.authentication.login.model.SendOtpResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Anil Upadhyay on 25/05/2019.
 */

public interface API {

    @FormUrlEncoded
    @POST("auth/sendOtp")
    Call<SendOtpResponse> callOTPApi(@Field("type") String type, @Field("countryCode") String countryCode,@Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("auth/signin")
    Call<LoginResponse> callLoginApi(@Field("countryCode") String countryCode,@Field("phoneNumber") String phoneNumber);


    @Multipart
    @POST("auth/signup")
    Call<SignUpResponse> callSignUpApi(@Part("fullName") RequestBody fullName
            , @Part("countryCode") RequestBody countryCode
            , @Part("phoneNumber") RequestBody phoneNumber
            , @Part("deviceToken") RequestBody deviceToken
            , @Part("deviceType") RequestBody deviceType
            , @Part("signupFrom") RequestBody signupFrom
            , @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("auth/socialSignup")
    Call<SignUpResponse> callSignUpApi1(@Field("fullName") String fullName
            , @Field("countryCode")  String countryCode
            , @Field("phoneNumber")  String phoneNumber
            , @Field("deviceToken")  String deviceToken
            , @Field("deviceType")   String deviceType
            , @Field("signupFrom")   String signupFrom
            , @Field("profilePhoto") String profilePhoto
            , @Field("socialType") String socialType
            , @Field("socialId") String socialId
            , @Field("imageUrl") String imageUrl);


    @FormUrlEncoded
    @POST("user/updateGender")
    Call<UpdateGenderResponse> callUpdateGenderApi(@Header("auth-token") String token, @Field("gender") String gender, @Field("prefers") String prefers);

    @FormUrlEncoded
    @POST("auth/checkSocialStatus")
    Call<CheckSocialResponse> callcheckSocialStatusApi(@Field("socialId") String socialId, @Field("socialType") String socialType);

   /*

    @GET("category")
    Call<CategoryResponse> callGetCategoryApi();


    @GET("subcategory")
    Call<SubCategoryResponse> callGetSubCategoryApi(@Query("category_id") String catId);






    @Multipart
    @POST("addclassified")
    Call<AddUpdateClassifiedResponse> calladdclassifiedApi(@Header("ACCESS-TOKEN") String token
            , @Part("subject") RequestBody subject
            , @Part("price") RequestBody price
            , @Part("description") RequestBody description
            , @Part("contact_person") RequestBody contact_person
            , @Part("contact_number") RequestBody contact_number
            , @Part("city") RequestBody city
            , @Part("c_id") RequestBody c_id
            , @Part MultipartBody.Part file);

    @POST("returnurl")
    Call<ReturnUrlResponse> callreturnurlApi(@Header("ACCESS-TOKEN") String token, @Body ReturnUrlParameter returnUrlParameter);

    @GET("allclasified")
    Call<AllClassifiedResponse> callAllClasifiedListApi(@Query("city") String city);
*/
}
