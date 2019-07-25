package com.bang.network;


import com.bang.base.BaseInterface;
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

/**
 * Created by Anil Upadhyay on 01/05/2019.
 */

public interface ApiCallback {

    interface SendOtpCallback extends BaseInterface {
        void onSuccessSendOtp(SendOtpResponse sendOtpResponse);
    }

    interface LoginManagerCallback extends BaseInterface {
        void onSuccessLogin(LoginResponse loginResponse);
    }

    interface SignUpCallback extends BaseInterface {
        void onSuccessSignUp(SignUpResponse signUpResponse);
    }

    interface GenderSelectionCallback extends BaseInterface {
        void onGenderSelectionResponse(UpdateGenderResponse updateGenderResponse);
        void onUpdateLocationResponse(UpdateLocationResponse updateLocationResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface CheckSocialCallback extends BaseInterface {
        void onSuccessCheckSocial(CheckSocialResponse checkSocialResponse);
    }

    interface GetProfileCallback extends BaseInterface {
        void onSuccessGetProfile(MyProfileResponse myProfileResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface UpdateProfileCallback extends BaseInterface {
        void onSuccessUpdateProfile(UpdateProfileResponse updateProfileResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface LogoutCallback extends BaseInterface {
        void onSuccessLogout(LogoutResponse logoutResponse);
        void onSuccessChangePassword(ChangePasswordResponse changePasswordResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface PreferenceCallback extends BaseInterface {
        void onSuccessPreference(PreferenceResponse preferenceResponse);
        void onSuccessSaveQuestions(SaveQuestionResponse saveQuestionResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface SurveyCallback extends BaseInterface {
        void onSuccessSurvey(SurveyQuestionResponse surveyQuestionResponse);
        void onSuccessCreateSurvey(CreateSurveyResponse createSurveyResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface SelectedPreferenceCallback extends BaseInterface {
        void onSuccessSelectedPreference(SelectedReferencesResponse selectedReferencesResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface GetAllUserCallback extends BaseInterface {
        void onSuccessAllUsers(GetAllUserResponse getAllUserResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface GetSurveyCallback extends BaseInterface {
        void OnSuccessSurveyResponse(SurveySentResponse surveySentResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface GetReceiveSurveyCallback extends BaseInterface {
        void OnSuccessReceiveSurveyResponse(SurveyReceiveResponse receiveResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface ViewSurveyCallback extends BaseInterface {
        void OnSuccessViewResponse(ViewSurveyModel viewSurveyModel);
        void onTokenChangeError(String errorMessage);
    }

    interface ViewSurveyDetailCallback extends BaseInterface {
        void onSuccessViewSurveyDetail(SurveyDetailModel surveyDetailModel);
        void OnSuccessComment(DocommentResponse docommentResponse);
        void onTokenChangeError(String errorMessage);

    }

    interface OtherUserCallback extends BaseInterface {
        void onSuccessOtherProfile(OtherUserProfileResponse otherUserProfileResponse);
        void onSuccessFollowResponse(FollowUnFollowResponse followUnFollowResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface AddNewsFeedCallback extends BaseInterface {
        void onSuccessAddNewsFeed(AddNewsResponse addNewsResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface FollowersCallback extends BaseInterface {
        void onSuccessFollowing(FollowingResponse followingResponse);
        void onSuccessFollowers(FollowersResponse followersResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface NewsFeedCallback extends BaseInterface {
        void onSuccessNewsFeed(NewsFeedResponse newsFeedResponse);
        void onSuccessLike(LikeResponse likeResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface MyPostCallback extends BaseInterface{
        void onSuccessMyPost(MyPostResponse myPostResponse);
        void onSuccessListList(LikeListResponse likeListResponse);
        void onTokenChangeError(String errorMessage);
    }




   /*   interface SubCategoryManager extends BaseInterface{
        void onSuccessSubCategory(SubCategoryResponse categoryResponse);
        void onTokenChangeError(String errorMessage);
    }


    interface BloodDonorManagerCallBack extends BaseInterface{
        void onSuccessBloodDonor(BloodDonorResponse bloodDonorResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface UpdateProfileManagerCallBack extends BaseInterface{
        void onSuccessUpdateProfie(UpdateProfileResponse updateProfileResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface OurPackagesManagerCallBack extends BaseInterface{
        void onSuccessPackage(PackageResponse packageResponse);
        void OnSuccessReturnUrl(ReturnUrlResponse returnUrlResponse);
        void onAddSuccessPackage(ApplyPackageResponse applyPackageResponse);
        void onTokenChangeError(String errorMessage);

    }

    interface AddBloodDonorCallback extends BaseInterface{
        void onSuccessAddBloodDonor(AddBloodDonorResponse addBloodDonorResponse);
        void onTokenChangeError(String errorMessage);
        void onSuccessGetBloodDonor(GetBloodDonorResponse getBloodDonorResponse);
    }


*/
}
