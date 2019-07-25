package com.bang.network;


import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.authentication.genderselection.model.UpdateLocationResponse;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.module.home.addsurvey.model.CreateSurveyResponse;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;
import com.bang.module.home.newsfeed.model.AddNewsResponse;
import com.bang.module.home.newsfeed.model.LikeListResponse;
import com.bang.module.home.newsfeed.model.NewsFeedResponse;
import com.bang.module.home.profile.followersfollowing.model.FollowersResponse;
import com.bang.module.home.profile.followersfollowing.model.FollowingResponse;
import com.bang.module.home.profile.followersfollowing.model.LikeResponse;
import com.bang.module.home.profile.getprofile.model.MyProfileResponse;
import com.bang.module.home.profile.mypost.model.MyPostResponse;
import com.bang.module.home.profile.otheruserProfile.model.FollowUnFollowResponse;
import com.bang.module.home.profile.otheruserProfile.model.OtherUserProfileResponse;
import com.bang.module.home.profile.updateProfile.model.UpdateProfileResponse;
import com.bang.module.home.addsurvey.model.GetAllUserResponse;
import com.bang.module.home.survey.model.DocommentResponse;
import com.bang.module.home.survey.model.SurveyDetailModel;
import com.bang.module.home.survey.model.SurveyReceiveResponse;
import com.bang.module.home.survey.model.SurveySentResponse;
import com.bang.module.home.survey.model.ViewSurveyModel;
import com.bang.module.setting.model.ChangePasswordResponse;
import com.bang.module.setting.model.LogoutResponse;
import com.bang.module.preference.model.PreferenceResponse;
import com.bang.module.preference.model.SaveQuestionResponse;
import com.bang.module.preference.model.SelectedReferencesResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Anil Upadhyay on 25/05/2019.
 */

public interface API {

    @FormUrlEncoded
    @POST("auth/sendOtp")
    Call<SendOtpResponse> callOTPApi(@Field("type") String type
            , @Field("email") String email);

    @FormUrlEncoded
    @POST("auth/signin")
    Call<LoginResponse> callLoginApi(@Field("email") String email
            , @Field("password") String password
            , @Field("deviceToken") String deviceToken
            , @Field("deviceType") String deviceType);


    @Multipart
    @POST("auth/signup")
    Call<SignUpResponse> callSignUpApi(@Part("fullName") RequestBody fullName
            , @Part("email") RequestBody email
            , @Part("password") RequestBody password
            , @Part("countryCode") RequestBody countryCode
            , @Part("phoneNumber") RequestBody phoneNumber
            , @Part("deviceToken") RequestBody deviceToken
            , @Part("deviceType") RequestBody deviceType
            , @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("auth/socialSignup")
    Call<SignUpResponse> callSignUpApi1(@Field("fullName") String fullName
            , @Field("countryCode") String countryCode
            , @Field("phoneNumber") String phoneNumber
            , @Field("email") String email
            , @Field("deviceToken") String deviceToken
            , @Field("deviceType") String deviceType
            , @Field("profilePhoto") String profilePhoto
            , @Field("socialType") String socialType
            , @Field("socialId") String socialId);


    @Multipart
    @POST("auth/socialSignup")
    Call<SignUpResponse> callSignUpApi2(@Part("fullName") RequestBody fullName
            , @Part("countryCode") RequestBody countryCode
            , @Part("phoneNumber") RequestBody phoneNumber
            , @Part("email") RequestBody email
            , @Part("deviceToken") RequestBody deviceToken
            , @Part("deviceType") RequestBody deviceType
            , @Part("socialType") RequestBody socialType
            , @Part("socialId") RequestBody socialId
            , @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("user/updateGender")
    Call<UpdateGenderResponse> callUpdateGenderApi(@Header("auth-token") String token
            , @Field("gender") String gender
            , @Field("prefers") String prefers);

    @FormUrlEncoded
    @POST("auth/checkSocialStatus")
    Call<CheckSocialResponse> callcheckSocialStatusApi(@Field("socialId") String socialId
            , @Field("socialType") String socialType
            , @Field("deviceToken") String deviceToken
            , @Field("deviceType") String deviceType);

    @GET("user/getMyProfile")
    Call<MyProfileResponse> callMyProfileApi(@Header("auth-token") String token);

    @Multipart
    @POST("user/updateProfile")
    Call<UpdateProfileResponse> callUpdateProfileApi(@Header("auth-token") String token
            , @Part("fullName") RequestBody fullName
            , @Part("phoneNumber") RequestBody phoneNumber
            , @Part("countryCode") RequestBody countryCode
            , @Part MultipartBody.Part file);


    @POST("user/logout")
    Call<LogoutResponse> callLogoutApi(@Header("auth-token") String token);

    @GET("questions/getPreferenceQuestions")
    Call<PreferenceResponse> callPreferenceApi(@Header("auth-token") String token
            , @Query("gender") String gender
            , @Query("questionType") String questionType);

    @GET("questions/getPreferenceQuestions")
    Call<SurveyQuestionResponse> callSurveyApi(@Header("auth-token") String token
            , @Query("gender") String gender
            , @Query("questionType") String questionType);

    @FormUrlEncoded
    @POST("questions/saveUserPreference")
    Call<SaveQuestionResponse> callSaveQuestionsApi(@Header("auth-token") String token
            , @Field("questionId") String questionId
            , @Field("optionId") String optionId);

    @GET("user/getUserSurveyStats")
    Call<SelectedReferencesResponse> callSelectedPreferenceApi(@Header("auth-token") String token
            , @Query("questionType") String questionType);

    @GET("user/getAllUser")
    Call<GetAllUserResponse> callAllUsersApi(@Header("auth-token") String token);

    @FormUrlEncoded
    @POST("survey/createSurvey")
    Call<CreateSurveyResponse> callCreateSurveyApi(@Header("auth-token") String token
            , @Field("date") String date
            , @Field("time") String time
            , @Field("forUserId") String forUserId
            , @Field("forUserGender") String forUserGender
            , @Field("surveyAnswers") String surveyAnswers);


    @GET("survey/getSurveyList")
    Call<SurveySentResponse> callSurveyResponse(@Header("auth-token") String token
            , @Query("type") String type
            , @Query("limit") String limit
            , @Query("offset") String offset);

    @GET("survey/getSurveyList")
    Call<SurveyReceiveResponse> callReceiveSurveyResponse(@Header("auth-token") String token
            , @Query("type") String type
            , @Query("limit") String limit
            , @Query("offset") String offset);


    @GET("survey/viewSurvey")
    Call<ViewSurveyModel> callAllViewSurveyApi(@Header("auth-token") String token,
                                               @Query("surveyId") String surveyId);


    @GET("survey/getSurveyDetails")
    Call<SurveyDetailModel> callSurveyDetailsApi(@Header("auth-token") String token,
                                                 @Query("surveyId") String surveyId,
                                                 @Query("type") String type,
                                                 @Query("offset") String offset,
                                                 @Query("limit") String limit);

    @FormUrlEncoded
    @POST("survey/postComment")
    Call<DocommentResponse> callDoCommentApi(@Header("auth-token") String token,
                                             @Field("surveyId") String surveyId,
                                             @Field("comment") String comment);


    @FormUrlEncoded
    @POST("user/updateLocation")
    Call<UpdateLocationResponse> callUpdateLocationApi(@Header("auth-token") String token,
                                                       @Field("latitude") String latitude,
                                                       @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("user/changePassword")
    Call<ChangePasswordResponse> callChangePasswordApi(@Header("auth-token") String token,
                                                       @Field("oldPassword") String oldPassword,
                                                       @Field("newPassword") String newPassword,
                                                       @Field("confirmPassword") String confirmPassword);

    @GET("user/getUserPublicProfile")
    Call<OtherUserProfileResponse> callOtherUserProfileApi(@Header("auth-token") String token,
                                                           @Query("userId") String userId);


    @FormUrlEncoded
    @POST("user/follow")
    Call<FollowUnFollowResponse> callFollowUnfollowApi(@Header("auth-token") String token,
                                                       @Field("followingUserId") String followingUserId);


    @Multipart
    @POST("newsfeed/createNewsfeed")
    Call<AddNewsResponse> callAddNewsFeedApi(@Header("auth-token") String token
            , @Part("title") RequestBody title
            , @Part MultipartBody.Part image
            , @Part MultipartBody.Part video
            , @Part MultipartBody.Part videoThumb);

    @GET("user/getFollowList")
    Call<FollowingResponse> callFollowingFollowersApi(@Header("auth-token") String token,
                                                      @Query("type") String type,
                                                      @Query("userId") String userId,
                                                      @Query("searchTerm") String searchTerm,
                                                      @Query("offset") String offset,
                                                      @Query("limit") String limit);

    @GET("user/getFollowList")
    Call<FollowersResponse> callFollowerFollowersApi(@Header("auth-token") String token,
                                                     @Query("type") String type,
                                                     @Query("userId") String userId,
                                                     @Query("searchTerm") String searchTerm,
                                                     @Query("offset") String offset,
                                                     @Query("limit") String limit);


    @GET("newsfeed/getNewsfeedList")
    Call<NewsFeedResponse> callNewsFeedApi(@Header("auth-token") String token,
                                           @Query("offset") String offset,
                                           @Query("limit") String limit);

    @FormUrlEncoded
    @POST("newsfeed/like")
    Call<LikeResponse> callLikeApi(@Header("auth-token") String token,
                                   @Field("newsfeedId") String newsfeedId);


    @GET("newsfeed/getMyFeedList")
    Call<MyPostResponse> getMyFeedListApi(@Header("auth-token") String token,
                                          @Query("offset") String offset,
                                          @Query("limit") String limit);

    @GET("newsfeed/getFeedLikeList")
    Call<LikeListResponse> getFeedLikeListApi(@Header("auth-token") String token,
                                              @Query("newsfeedId") String newsfeedId,
                                              @Query("offset") String offset,
                                              @Query("limit") String limit);

    @FormUrlEncoded
    @POST("survey/share")
    Call<LikeListResponse> shareApi(@Header("auth-token") String token,
                                              @Field("title") String title,
                                              @Field("surveyId") String surveyId,
                                              @Field("surveyedUserId") String surveyedUserId,
                                              @Field("is_anonymous") String is_anonymous,
                                              @Field("survey_score") String survey_score);



/*


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
