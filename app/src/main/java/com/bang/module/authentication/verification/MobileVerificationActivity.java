package com.bang.module.authentication.verification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.helper.CustomToast;
import com.bang.helper.Validation;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.login.LoginActivity;
import com.bang.module.authentication.login.manager.SendOtpManager;
import com.bang.module.authentication.login.model.ForgotPasswordResponse;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.module.authentication.otpverification.OtpVerificationActivity;
import com.bang.network.ApiCallback;

import static com.bang.module.authentication.login.LoginActivity.SECOND_ACTIVITY_REQUEST_CODE;

public class MobileVerificationActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.SendOtpCallback {

    private TextView tvSendOtpButton;
    private EditText etLoginEmail;
    private LinearLayout llMobileVerificationGoingToCountryCode;
    private long mLastClickTime = 0;
    private String country_code = "";
    private ImageView ivVerifying;
    private ImageView ivForgotBack;
    private String verifyingKey = "";
    private String emailAddress = "";
    private String fb_name = "";
    private String fb_image = "";
    private String socialType = "";
    private String socialId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        if (getIntent().getStringExtra("verifyingKey") != null) {
            verifyingKey = getIntent().getStringExtra("verifyingKey");
        } else {
            fb_name = getIntent().getStringExtra("fullname");
            fb_image = getIntent().getStringExtra("fb_image");
            socialType = getIntent().getStringExtra("socialType");
            socialId = getIntent().getStringExtra("socialId");
        }
        init();
        tvSendOtpButton.setOnClickListener(this);
        llMobileVerificationGoingToCountryCode.setOnClickListener(this);
        ivForgotBack.setOnClickListener(this);
    }

    private void init() {
        etLoginEmail    = findViewById(R.id.edtSignUpNumber);
        tvSendOtpButton = findViewById(R.id.tvSendOtpButton);
        ivForgotBack    = findViewById(R.id.ivForgotBack);
        ivVerifying     = findViewById(R.id.ivVerifying);
        TextView tvDescription = findViewById(R.id.tvDescription);
        llMobileVerificationGoingToCountryCode = findViewById(R.id.llMobileVerificationGoingToCountryCode);
        TextView tvEmailVerification = findViewById(R.id.tvEmailVerification);
        if (verifyingKey.equals("ForForgotPassword")) {
            ivForgotBack.setVisibility(View.VISIBLE);
            tvSendOtpButton.setText(getResources().getString(R.string.send_label));
            tvEmailVerification.setText(getResources().getString(R.string.forgot_password_label));
            tvDescription.setText(getResources().getString(R.string.please_enter_your_email_we_will_send_password_your_email));
            ivVerifying.setImageResource(R.drawable.ic_forgot_img);
        } else {
            ivForgotBack.setVisibility(View.GONE);
            tvSendOtpButton.setText(getResources().getString(R.string.send_otp));
            tvEmailVerification.setText(getResources().getString(R.string.mobile_verification));
            tvDescription.setText(getResources().getString(R.string.please_enter_your_mobile_number_we_will_send_verification_code));
            ivVerifying.setImageResource(R.drawable.mobile_verification_img);
        }

        /*if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            country_code = "+" + country.getPhoneCode();
            ivMobileVerifyFlag.setImageResource(country.getFlag());
        } else {
            country_code = "+1";
        }*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (verifyingKey.equals("ForForgotPassword")) {
            startActivity(new Intent(MobileVerificationActivity.this, LoginActivity.class));
            finish();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Validation validation = new Validation();
        switch (v.getId()) {
            case R.id.tvSendOtpButton:
                emailAddress = etLoginEmail.getText().toString().trim();
                if (emailAddress.equals("")) {
                    CustomToast.getInstance(MobileVerificationActivity.this).showToast(MobileVerificationActivity.this, "Please enter email");
                } else if (!validation.isEmailValid(etLoginEmail)) {
                    CustomToast.getInstance(MobileVerificationActivity.this).showToast(MobileVerificationActivity.this, "Please enter valid email");
                } else {
                    if (verifyingKey.equals("ForForgotPassword")) {
                        callForgotApi(emailAddress);
                    } else {
                        new SendOtpManager(this, MobileVerificationActivity.this).
                                callSendOtp("signup", emailAddress);
                    }
                }
                break;
            case R.id.llMobileVerificationGoingToCountryCode:
                Intent intent = new Intent(MobileVerificationActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.ivForgotBack:
                startActivity(new Intent(MobileVerificationActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void callForgotApi(String email) {
        new SendOtpManager(this, MobileVerificationActivity.this).callForgotApi(email);
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int country_flag = data.getIntExtra("country_flag", -1);
                System.out.println("*****************" + country_flag);
                if (country_flag != -1) {
                    ivMobileVerifyFlag.setImageResource(country_flag);
                }
            }
        }
    }*/


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
        CustomToast.getInstance(MobileVerificationActivity.this).showToast(MobileVerificationActivity.this, errorMessage);
    }


    @Override
    public void onSuccessSendOtp(SendOtpResponse sendOtpResponse) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^" + sendOtpResponse.getOtp());
        int otp = sendOtpResponse.getOtp();
        if (verifyingKey != null && !verifyingKey.equals("")) {
            startActivity(new Intent(MobileVerificationActivity.this, OtpVerificationActivity.class)
                    .putExtra("emailAddress", emailAddress)
                    .putExtra("countryCode", country_code)
                    .putExtra("loginOtp", String.valueOf(otp))
                    .putExtra("verifyingKey", verifyingKey)
                    .putExtra("type", "signup"));
            finish();
        } else {
            startActivity(new Intent(MobileVerificationActivity.this, OtpVerificationActivity.class)
                    .putExtra("fullname", fb_name)
                    .putExtra("fb_image", fb_image)
                    .putExtra("socialType", socialType)
                    .putExtra("loginOtp", String.valueOf(otp))
                    .putExtra("socialId", socialId)
                    .putExtra("social_email", emailAddress));
            finish();
        }
    }

    @Override
    public void onSuccessForgotPassword(ForgotPasswordResponse forgotPasswordResponse) {
        alertDialog(forgotPasswordResponse.getMessage(), this);
    }

    private void alertDialog(String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Success")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    startActivity(new Intent(MobileVerificationActivity.this, LoginActivity.class));
                    finish();
                })
                .show();

    }

    /*private Country getCurrentCountry() {
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
    }*/
}