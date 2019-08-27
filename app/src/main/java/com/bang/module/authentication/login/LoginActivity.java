package com.bang.module.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.os.SystemClock;
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
import com.bang.base.BangParentActivity;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.helper.Utils;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.authentication.login.manager.CheckSocialManager;
import com.bang.module.authentication.login.model.CheckSocialResponse;
import com.bang.module.authentication.login.model.ForgotPasswordResponse;
import com.bang.module.authentication.profilecompletion.CompleteProfileActivity;
import com.bang.module.authentication.verification.MobileVerificationActivity;
import com.bang.module.authentication.verification.manager.LoginPresenter;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.home.MainActivity;
import com.bang.network.ApiCallback;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BangParentActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener
        , ApiCallback.LoginManagerCallback, ApiCallback.CheckSocialCallback {

    CallbackManager callbackManager;
    LoginManager loginManager;
    private TextView txtLoginButton,tvForgotPassword;
    private ImageView ivCountryFlag;
    private LinearLayout llSelectCountryGo;
    public static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private LinearLayout llSignUp;
    private long mLastClickTime = 0;
    private int RC_SIGN_IN = 100;
    private RelativeLayout rlGoogleSignIn;
    private RelativeLayout rlFacebookLogin;
    private LoginButton btn_facebook;
    private EditText etLoginEmail, etLoginPassword;
    private String socialEmail = "";
    private String fullName = "", profileImage = "";
    private String socialType = "", socialId = "";
    private String device_token = "";
    private Session session;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                device_token = instanceIdResult.getToken();
             }
        });
        init();
        session = new Session(LoginActivity.this);

        // initializing GoogleApiClient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

         // Facebook Integration
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginManager.logOut();
        btn_facebook.setReadPermissions("email");
        facebookLogin();

        txtLoginButton.   setOnClickListener(this);
        llSelectCountryGo.setOnClickListener(this);
        rlGoogleSignIn.   setOnClickListener(this);
        rlFacebookLogin.  setOnClickListener(this);
        llSignUp.         setOnClickListener(this);
        tvForgotPassword. setOnClickListener(this);
    }

    private void init() {
        tvForgotPassword  = findViewById(R.id.tvForgotPassword);
        etLoginEmail      = findViewById(R.id.etLoginEmail);
        etLoginPassword   = findViewById(R.id.etLoginPassword);
        txtLoginButton    = findViewById(R.id.txtLoginButton);
        llSelectCountryGo = findViewById(R.id.llSelectCountryGo);
        ivCountryFlag     = findViewById(R.id.ivCountryFlag);
        rlGoogleSignIn    = findViewById(R.id.rlGoogleSignIn);
        rlFacebookLogin   = findViewById(R.id.rlFacebookLogin);
        btn_facebook      = findViewById(R.id.btn_facebook);
        llSignUp          = findViewById(R.id.llSignUp);

        if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            ivCountryFlag.setImageResource(country.getFlag());
        }
        // etLoginMobileNumber.addTextChangedListener(new CustomPhoneTextWatcher(etLoginMobileNumber));
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
                String email = etLoginEmail.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();

                if (!Utils.checkEmailForValidity(etLoginEmail.getText().toString())) {
                    Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    CustomToast.getInstance(LoginActivity.this).showToast(LoginActivity.this, getString(R.string.enter_pass));
                } else {
                    if (AppHelper.isConnectingToInternet(LoginActivity.this)) {
                        new LoginPresenter(this, LoginActivity.this).callLoginApi(email, password, device_token, "0");
                    } else {
                        CustomToast.getInstance(LoginActivity.this).showToast(LoginActivity.this, getString(R.string.alert_no_network));
                    }
                }
                break;
            case R.id.llSelectCountryGo:
                Intent intent = new Intent(LoginActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;

            case R.id.rlGoogleSignIn:
                socialType = "google";
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                showLoader();
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
                finish();
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginActivity.this, MobileVerificationActivity.class)
                        .putExtra("verifyingKey", "ForForgotPassword"));
                finish();
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
          hideLoader();
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            if (acct.getId() != null)
                socialId = acct.getId();
            else
                socialId = "";
            if (acct.getEmail() != null)
                socialEmail = acct.getEmail();
            else
                socialEmail = "";
            fullName = acct.getDisplayName();
            profileImage = String.valueOf(acct.getPhotoUrl());
            new CheckSocialManager(LoginActivity.this, LoginActivity.this).callSocialCheck(socialId, "google", device_token, "0");
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
                                    socialEmail = "";
                                    if (object.has("email")) {
                                        socialEmail = object.getString("email");
                                    }
                                    socialId = object.getString("id");
                                    final String firstName = object.getString("first_name");
                                    final String lastName = object.getString("last_name");
                                    fullName = firstName + " " + lastName;
                                    profileImage = "https://graph.facebook.com/" + sSocialId + "/picture?width=1024&height=786";
                                    loginManager.logOut();
                                    new CheckSocialManager(LoginActivity.this, LoginActivity.this).callSocialCheck(socialId, "facebook", device_token, "0");
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

    private Country getCurrentCountry() {
        String cCode = Utils.getUserCountry(this);
        List<Country> mCountries = Arrays.asList(
                new Gson().fromJson(Utility.loadJSONFromAsset(this, "countries.json"), Country[].class));
        int[] flags = Utility.countryFlags;
        for (int i = 0; i < mCountries.size(); i++) {
            mCountries.get(i).setFlag(flags[i]);
        }
        for (Country c : mCountries) {
            {
                if (c.getCode().equals(cCode)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public void onSuccessCheckSocial(CheckSocialResponse checkSocialResponse) {
        if (checkSocialResponse.getSocialStatus().equals(0)) {
            if (!socialEmail.equals("")) {
                startActivity(new Intent(LoginActivity.this, CompleteProfileActivity.class)
                        .putExtra("fullname", fullName)
                        .putExtra("fb_image", profileImage)
                        .putExtra("social_email", socialEmail)
                        .putExtra("socialType", socialType)
                        .putExtra("socialId", socialId));
                finish();
            } else {
                startActivity(new Intent(LoginActivity.this, MobileVerificationActivity.class)
                        .putExtra("fullname", fullName)
                        .putExtra("fb_image", profileImage)
                        .putExtra("social_email", socialEmail)
                        .putExtra("socialType", socialType)
                        .putExtra("socialId", socialId));
                finish();
            }

        } else {
            if (checkSocialResponse.getData().getProfileStep().equals(1)) {
                session.createRegistration(checkSocialResponse.getData());
                session.setUserLoggedIn();
                startActivity(new Intent(LoginActivity.this, GenderSelectionActivity.class));
                finish();
            } else {
                session.createRegistration(checkSocialResponse.getData());
                session.setUserLoggedIn();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }
        }
    }

    @Override
    public void onSuccessLogin(LoginResponse loginResponse) {
        if (loginResponse.getCode().equals(200)) {
            session.createRegistration(loginResponse.getData());
            session.setUserLoggedIn();
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + loginResponse.getData().getProfileStep());
            if (loginResponse.getData().getProfileStep().equals(1)) {
                startActivity(new Intent(LoginActivity.this, GenderSelectionActivity.class));
                finish();
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }
    }
}
