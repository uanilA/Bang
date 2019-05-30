package com.bang.serverhandling;


import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.module.authentication.login.model.SendOtpResponse;

/**
 * Created by Ravi Thakur on 9/17/2018.
 */

public interface ApiCallback {

    interface SendOtpCallback extends BaseInterface{
        void onSuccessSendOtp(SendOtpResponse sendOtpResponse);
    }
    interface LoginManagerCallback extends BaseInterface{
        void onSuccessLogin(LoginResponse loginResponse);
    }

    interface SignUpCallback extends BaseInterface{
        void onSuccessSignUp(SignUpResponse signUpResponse);
    }

    interface GenderSelectionCallback extends BaseInterface{
        void onGenderSelectionResponse(UpdateGenderResponse sliderRespone);
        void onTokenChangeError(String errorMessage);
    }

    interface CheckSocialCallback extends BaseInterface{
        void onSuccessCheckSocial(CheckSocialResponse checkSocialResponse);
    }

    /*interface LogoutCallback extends BaseInterface{
        void onSuccesLogout(ForgotPasswordResponse forgotPasswordResponse);
    }*/
    /*

     interface HomeFragmentCallback extends BaseInterface{
        void onSliderResponse(SliderResponse sliderRespone);
        void onCategoryResponse(CategoryResponse categoryResponse);
        void onTokenChangeError(String errorMessage);
    }

     interface ChangePasswordCallback extends BaseInterface{
        void onSuccessChangePassword(ChangePasswordResponse changePasswordResponse);
        void onTokenChangeError(String errorMessage);
    }

     interface SubCategoryManager extends BaseInterface{
        void onSuccessSubCategory(SubCategoryResponse categoryResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface BloodDonorCallback extends BaseInterface{
        void onSuccessBloodGroup(BloodGroupResponse bloodGroupResponse);
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

    interface AddAdsPostCallback extends BaseInterface{
        void onSuccessAddPost(AddPostResponse addPostResponse);
        void onSuccessOffer(OfferResponse offerResponse);
        void onSuccessCategory(CategoryResponse categoryResponse);
        void OnSuccessSubCategory(SubCategoryResponse subCategoryResponse);
        void OnSuccessCityList(CityResponse cityResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface UpdateMobileManagerCallBack extends BaseInterface{
        void onSuccessUpdateMobile(UpdateMobileResponse updateMobileResponse);
        void onSuccessUpdatedMobile(UpdatedMobileResponse updatedMobileResponse);
        void onSuccessUpdatedImage(UpdateImageResponse updateImageResponse);
    }

    interface UpdateAddPostManagerCallBack extends BaseInterface{
        void onSuccessUpdateMobile(UpdatePostResponse updatePostResponse);
        void onSuccessOffer(OfferResponse offerResponse);
        void OnSuccessCityList(CityResponse cityResponse);
        void onTokenChangeError(String errorMessage);
        void onSuccessAddPost(AddPostResponse addPostResponse);
    }

    interface NewsManagerCallBack extends BaseInterface{
        void onSuccessNews(NewsResponse updatePostResponse);
        void OnSuccessNewCity(NewCityResponse newCityResponse);
    }
   interface  HomeCityManagerCallBack extends BaseInterface{
       void OnSuccessCityList(CityResponse cityResponse);
       void OnSuccessSelectedCity(SelectedCityResponse selectedCityResponse);
       void onTokenChangeError(String errorMessage);
   }

    interface TestimonialManagerCallBack extends BaseInterface{
        void onSuccessTestimonial(TestimonialResponse testimonialResponse);
    }
    interface FindTestimonialManagerCallBack extends BaseInterface{
        void OnSuccessCityList(CityResponse cityResponse);
        void OnSuccessFindTestimonial(FindTestimonialRespones findTestimonialRespones);
    }

    interface LetsManagerCallBack extends BaseInterface{
        void OnSuccessLetsList(LetsResponse letsResponse);
     }

    interface LetsDetailsManagerCallBack extends BaseInterface{
        void OnSuccessLetsDetails(RatingResponse letsResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface SearchManagerCallBack extends BaseInterface{
        void OnSuccessLetsDetails(GlobleSearchResponse globleSearchResponse);

    }
    interface ViewAdsManagerCallBack extends BaseInterface{
        void OnSuccessLetsDetails(ViewAdsResponse viewAdsResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface ViewDetailUpdateAdsManagerCallBack extends BaseInterface{
        void onSuccessOffer(OfferResponse offerResponse);
        void onSuccessCategory(CategoryResponse categoryResponse);
        void OnSuccessSubCategory(SubCategoryResponse subCategoryResponse);
        void OnSuccessCityList(CityResponse cityResponse);
        void onTokenChangeError(String errorMessage);
        void onSuccessAddPost(AddPostResponse addPostResponse);
    }

    interface MyBloodDonorCallback extends BaseInterface{
        void onTokenChangeError(String errorMessage);
        void onSuccessGetBloodDonor(MyDonorListResponse getBloodDonorResponse);
    }

    interface ClassifiedManagerCallBack extends BaseInterface{
        void onSuccessClassified(ClassifiedResponse classifiedResponse);
        void onTokenChangeError(String errorMessage);
    }

    interface AllClassifiedManagerCallBack extends BaseInterface{
        void onSuccessAllClassified(AllClassifiedResponse allClassifiedResponse);
    }


     interface AddUpdateClassifiedManagerCallBack extends BaseInterface{
        void onSuccessAddUpdateClassified(AddUpdateClassifiedResponse addUpdateClassifiedResponse);
        void onTokenChangeError(String errorMessage);
    }
*//*
     interface CartManagerCallback extends BaseInterface{
        void onSuccessCartList(CartResponse cartResponse);
        void onSuccessDeleteCartItem(DeleteCartItemResponse deleteCartItemResponse);
        void onSuccessUpdateCartItem(UpdateCartItemResponse updateCartItemResponse);
        void onErrorDeleteCartItem(String errorMessage);
        void onTokenChangeError(String errorMessage);
    }

     interface CheckoutManagerCallback extends BaseInterface{
        void onSuccessState(StateResponse stateResponse);
        void onSuccessCity(CityResponse cityResponse);
        void onSuccessCheckout(BookOrderResponse bookOrderResponse);
        void onTokenChangeError(String errorMessage);
    }


     interface OrderManagerCallback extends BaseInterface{
        void onOrderListSuccess(OrderResponse orderResponse);
        void onTokenChangeError(String errorMessage);
    }
     interface  SearchProductCallback extends BaseInterface{
         void onSearchProduct(SearchProductResponse searchProductResponse);
     }
*/
}
