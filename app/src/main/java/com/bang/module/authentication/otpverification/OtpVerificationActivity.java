package com.bang.module.authentication.otpverification;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.authentication.login.manager.SendOtpManager;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.module.authentication.profilecompletion.CompleteProfileActivity;
import com.bang.module.authentication.verification.manager.LoginManager;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.module.home.MainActivity;
import com.bang.serverhandling.ApiCallback;
import com.goodiebag.pinview.Pinview;

public class OtpVerificationActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.LoginManagerCallback, ApiCallback.SendOtpCallback {

    private TextView tvSendOtpButton;
    private long mLastClickTime = 0;
    private EditText etOtpOne, etOtpTwo, etOtpThree, etOtpFour;
    private Session session;
    private String myOtp = "", verifyingLKey = "";
    private String country_code = "";
    private String mobileNumber = "";
    private String type = "";
    Pinview pinview;
    private LinearLayout llResendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        init();

        myOtp = getIntent().getStringExtra("loginOtp");
        verifyingLKey = getIntent().getStringExtra("verifyingKey");
        country_code = getIntent().getStringExtra("countryCode");
        mobileNumber = getIntent().getStringExtra("mobileNumber");
        type = getIntent().getStringExtra("type");

        session = new Session(OtpVerificationActivity.this);
        tvSendOtpButton.setOnClickListener(this);
        llResendOtp.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void init() {
        llResendOtp = findViewById(R.id.llResendOtp);
        tvSendOtpButton = findViewById(R.id.tvSendOtpButton);
        etOtpOne = findViewById(R.id.etOtpOne);
        etOtpTwo = findViewById(R.id.etOtpTwo);
        etOtpThree = findViewById(R.id.etOtpThree);
        etOtpFour = findViewById(R.id.etOtpFour);
        pinview = findViewById(R.id.pinview);
        pinview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
            }
        });
        etOtpOne.addTextChangedListener(new GenericTextWatcher(etOtpOne, etOtpTwo, null));
        etOtpTwo.addTextChangedListener(new GenericTextWatcher(etOtpTwo, etOtpThree, etOtpOne));
        etOtpThree.addTextChangedListener(new GenericTextWatcher(etOtpThree, etOtpFour, etOtpTwo));
        etOtpFour.addTextChangedListener(new GenericTextWatcher(etOtpFour, null, etOtpThree));
    }

    @Override
    public void onClick(View v) {
        // Preventing multiple clicks, using threshold of 1/2 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.llResendOtp:
                new SendOtpManager(this, OtpVerificationActivity.this).callSendOtp(type, country_code, mobileNumber);
                break;
            case R.id.tvSendOtpButton:
                String otp = etOtpOne.getText().toString().trim() + etOtpTwo.getText().toString().trim() +
                        etOtpThree.getText().toString().trim() + etOtpFour.getText().toString().trim();
                if (otp.length() < 4) {
                    Toast.makeText(this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
                } else if (!myOtp.equals(otp)) {
                    CustomToast.getInstance(OtpVerificationActivity.this).showToast(OtpVerificationActivity.this, "otp is not valid");
                } else {
                    if (verifyingLKey.equals("ForSignIn")) {
                        new LoginManager(this, OtpVerificationActivity.this).callLoginApi(country_code, mobileNumber);
                    } else {
                        startActivity(new Intent(OtpVerificationActivity.this, CompleteProfileActivity.class)
                                .putExtra("countryCode", country_code)
                                .putExtra("mobileNumber", mobileNumber));
                        finish();
                    }
                }
                break;
        }

    }

    @Override
    public void onSuccessLogin(LoginResponse loginResponse) {
        if (loginResponse.getCode().equals(200)) {
            session.createRegistration(loginResponse);
            session.setUserLoggedIn();
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+loginResponse.getData().getProfileStep());
            if (loginResponse.getData().getProfileStep().equals(1)) {
                startActivity(new Intent(OtpVerificationActivity.this, GenderSelectionActivity.class));
                finish();
            } else  {
                startActivity(new Intent(OtpVerificationActivity.this, MainActivity.class));
                finish();
            }
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
        CustomToast.getInstance(OtpVerificationActivity.this).showToast(OtpVerificationActivity.this, errorMessage);
    }

    @Override
    public void onSuccessSendOtp(SendOtpResponse sendOtpResponse) {
        CustomToast.getInstance(OtpVerificationActivity.this).showToast(OtpVerificationActivity.this, sendOtpResponse.getMessage());
    }
}
