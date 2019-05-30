package com.bang.module.authentication.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomPhoneTextWatcher;
import com.bang.helper.CustomToast;
import com.bang.helper.Utils;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.authentication.login.manager.CheckSocialManager;
import com.bang.module.authentication.login.manager.SendOtpManager;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.authentication.otpverification.OtpVerificationActivity;
import com.bang.module.authentication.verification.MobileVerificationActivity;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.profilecompletion.CompleteProfileActivity;
import com.bang.module.home.MainActivity;
import com.bang.serverhandling.ApiCallback;
import com.bang.utils.Utility;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends BangParentActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, ApiCallback.SendOtpCallback, ApiCallback.CheckSocialCallback {

    CallbackManager callbackManager;
    LoginManager loginManager;
    private long mLastClickTime = 0;
    private TextView txtLoginButton;
    private ImageView ivCountryFlag;
    private LinearLayout llSelectCountryGo;
    public static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private LinearLayout llSignUp;
    private String country_code = "1";
    private GoogleApiClient gac;
    private int RC_SIGN_IN = 100;
    private RelativeLayout rlGoogleSignIn;
    private RelativeLayout rlFacebookLogin;
    private LoginButton btn_facebook;
    private EditText etLoginMobileNumber;
    private String number;
    private String fullName = "", profileImage = "";
    private String socialType = "",socialId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        // initializing GoogleApiClient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gac = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        FacebookSdk.sdkInitialize(getApplicationContext());
        // Facebook Integration
        loginManager = LoginManager.getInstance();

        callbackManager = CallbackManager.Factory.create();
        loginManager.logOut();
        btn_facebook.setReadPermissions("email");
        facebookLogin();


        txtLoginButton.setOnClickListener(this);
        llSelectCountryGo.setOnClickListener(this);
        rlGoogleSignIn.setOnClickListener(this);
        rlFacebookLogin.setOnClickListener(this);
        llSignUp.setOnClickListener(this);
    }

    private void init() {
        etLoginMobileNumber = findViewById(R.id.etLoginMobileNumber);

        txtLoginButton = findViewById(R.id.txtLoginButton);
        llSelectCountryGo = findViewById(R.id.llSelectCountryGo);
        ivCountryFlag = findViewById(R.id.ivCountryFlag);
        rlGoogleSignIn = findViewById(R.id.rlGoogleSignIn);
        rlFacebookLogin = findViewById(R.id.rlFacebookLogin);
        btn_facebook = findViewById(R.id.btn_facebook);
        llSignUp = findViewById(R.id.llSignUp);

        if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            country_code = "+" + country.getPhoneCode();
            ivCountryFlag.setImageResource(country.getFlag());
        }
        // new CustomPhoneTextWatcher(etLoginMobileNumber);
        etLoginMobileNumber.addTextChangedListener(new CustomPhoneTextWatcher(etLoginMobileNumber));
    }

    @Override
    public void onClick(View view) {
        // Preventing multiple clicks, using threshold of 1/2 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.txtLoginButton:
                number = etLoginMobileNumber.getText().toString().trim();
                number = number.replace("-", "");
                if (number.equals("")) {
                    Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 8) {
                    Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                } else {
                    new SendOtpManager(this, LoginActivity.this).callSendOtp("signin", country_code, number);
                }
                break;
            case R.id.llSelectCountryGo:
                Intent intent = new Intent(LoginActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;

            case R.id.rlGoogleSignIn:
                socialType = "google";
                Intent i = Auth.GoogleSignInApi.getSignInIntent(gac);
                startActivityForResult(i, RC_SIGN_IN);
                showLoader();
                /* Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();*/
                break;
            case R.id.btn_facebook:
            case R.id.rlFacebookLogin:
                socialType = "facebook";
                if (AppHelper.isConnectingToInternet(LoginActivity.this)) {
                    btn_facebook.performClick();
                } else {
                    CustomToast.getInstance(LoginActivity.this).showToast(LoginActivity.this, getString(R.string.alert_no_network));
                }
                break;
            case R.id.llSignUp:
                startActivity(new Intent(LoginActivity.this, MobileVerificationActivity.class)
                        .putExtra("verifyingKey", "ForSignUp"));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int country_flag = data.getIntExtra("country_flag", -1);
                String myCode = data.getStringExtra("country_code");
                country_code = "+" + myCode;
                System.out.println("*****************" + country_flag);
                if (country_flag != -1) {
                    ivCountryFlag.setImageResource(country_flag);
                }
            }
        } else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // after the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        // if the login succeed
        hideLoader();
        if (result.isSuccess()) {
            // getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            if (acct.getId() != null)
                socialId = acct.getId();
            else
                socialId = "";
            fullName = acct.getDisplayName();
            profileImage = String.valueOf(acct.getPhotoUrl());
            new CheckSocialManager(LoginActivity.this, LoginActivity.this).callSocialCheck(socialId, "google");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Log.d("DBG", Objects.requireNonNull(acct.getPhotoUrl()).toString());
            }
        } else {
            CustomToast.getInstance(this).showToast(this, "fail");
        }
    }


    private void facebookLogin() {
        if (AppHelper.isConnectingToInternet(LoginActivity.this)) {
            btn_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    final String sSocialId = loginResult.getAccessToken().getUserId();
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            if (response.getError() != null) {
                                CustomToast.getInstance(LoginActivity.this).showToast(LoginActivity.this, getResources().getString(R.string.alert_api_fail));
                            } else {
                                try {
                                    String email = "";
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    }
                                    socialId = object.getString("id");
                                    final String firstName = object.getString("first_name");
                                    final String lastName = object.getString("last_name");
                                    fullName = firstName + " " + lastName;
                                    profileImage = "https://graph.facebook.com/" + sSocialId + "/picture?width=1024&height=786";
                                    loginManager.logOut();
                                    new CheckSocialManager(LoginActivity.this, LoginActivity.this).callSocialCheck(socialId, "facebook");
                                    // checkSocialLogin(socialId);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, first_name, last_name, email, picture");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                    Log.e("^^^^^^^^^^^^^^", "Cancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.e(LoginActivity.class.getSimpleName(), "Error:", error);
                }
            });

        }
    }


    @Override
    public void onShowBaseLoader() {
        showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(LoginActivity.this).showToast(LoginActivity.this, errorMessage);
    }

    @Override
    public void onSuccessSendOtp(SendOtpResponse sendOtpResponse) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^" + sendOtpResponse.getOtp());
        int otp = sendOtpResponse.getOtp();
        startActivity(new Intent(LoginActivity.this, OtpVerificationActivity.class)
                .putExtra("loginOtp", String.valueOf(otp))
                .putExtra("verifyingKey", "ForSignIn")
                .putExtra("countryCode", country_code)
                .putExtra("mobileNumber", number)
                .putExtra("type", "sign"));
        finish();
    }

    private Country getCurrentCountry() {
        String cCode = Utils.getUserCountry(this);
        List<Country> mCountries = Arrays.asList(
                new Gson().fromJson(Utility.loadJSONFromAsset(this, "country_code.json"), Country[].class));

        int[] flags = Utility.countryFlags;
        for (int i = 0; i < mCountries.size(); i++) {
            mCountries.get(i).setFlag(flags[i]);
        }

        for (Country c : mCountries) { {
                if (c.getCode().equals(cCode)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public void onSuccessCheckSocial(CheckSocialResponse checkSocialResponse) {
        if (checkSocialResponse.getSocialStatus().equals("0")) {
            startActivity(new Intent(LoginActivity.this, CompleteProfileActivity.class)
                    .putExtra("fullname", fullName).putExtra("fb_image", profileImage)
                    .putExtra("socialType",socialType)
                    .putExtra("socialId",socialId));
            finish();
        } else if (checkSocialResponse.getSocialStatus().equals("1")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}
