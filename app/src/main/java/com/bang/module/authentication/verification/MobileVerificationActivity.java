package com.bang.module.authentication.verification;

import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.CustomPhoneTextWatcher;
import com.bang.helper.CustomToast;
import com.bang.helper.Utils;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.authentication.login.LoginActivity;
import com.bang.module.authentication.login.manager.SendOtpManager;
import com.bang.module.authentication.otpverification.OtpVerificationActivity;
import com.bang.module.authentication.login.model.SendOtpResponse;
import com.bang.module.authentication.verification.model.LoginResponse;
import com.bang.serverhandling.ApiCallback;
import com.bang.utils.Utility;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import static com.bang.module.authentication.login.LoginActivity.SECOND_ACTIVITY_REQUEST_CODE;

public class MobileVerificationActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.SendOtpCallback {

    private TextView tvSendOtpButton;
    private EditText edtSignUpNumber;
    private LinearLayout llMobileVerificationGoingToCountryCode;
    private long mLastClickTime = 0;
    private String country_code = "";
    private ImageView ivMobileVerifyFlag;
    private String verifyingKey = "";
    private String myCode = "1";
    private String phoneNumber="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        init();
        if (getIntent().getStringExtra("verifyingKey") != null)
            verifyingKey = getIntent().getStringExtra("verifyingKey");

        tvSendOtpButton.setOnClickListener(this);
        llMobileVerificationGoingToCountryCode.setOnClickListener(this);
    }

    private void init() {
        edtSignUpNumber = findViewById(R.id.edtSignUpNumber);
        tvSendOtpButton = findViewById(R.id.tvSendOtpButton);
        llMobileVerificationGoingToCountryCode = findViewById(R.id.llMobileVerificationGoingToCountryCode);
        ivMobileVerifyFlag = findViewById(R.id.ivMobileVerifyFlag);

        if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            country_code = "+" + country.getPhoneCode();
            ivMobileVerifyFlag.setImageResource(country.getFlag());
        }
        edtSignUpNumber.addTextChangedListener(new CustomPhoneTextWatcher(edtSignUpNumber));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.tvSendOtpButton:
                country_code = "+" + myCode;
                phoneNumber = edtSignUpNumber.getText().toString().trim();
                phoneNumber = phoneNumber.replace("-", "");
                if (phoneNumber.equals("")) {
                    CustomToast.getInstance(MobileVerificationActivity.this).showToast(MobileVerificationActivity.this, "Please enter mobile number");
                } else {
                    new SendOtpManager(this, MobileVerificationActivity.this).
                            callSendOtp("signup", country_code, phoneNumber);
                }
                break;
            case R.id.llMobileVerificationGoingToCountryCode:
                Intent intent = new Intent(MobileVerificationActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int country_flag = data.getIntExtra("country_flag", -1);
                myCode = data.getStringExtra("country_code");
                System.out.println("*****************" + country_flag);
                if (country_flag != -1) {
                    ivMobileVerifyFlag.setImageResource(country_flag);
                }
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
        CustomToast.getInstance(MobileVerificationActivity.this).showToast(MobileVerificationActivity.this, errorMessage);
    }


    @Override
    public void onSuccessSendOtp(SendOtpResponse sendOtpResponse) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^" + sendOtpResponse.getOtp());
        int otp = sendOtpResponse.getOtp();
        startActivity(new Intent(MobileVerificationActivity.this, OtpVerificationActivity.class)
                .putExtra("mobileNumber",phoneNumber)
                .putExtra("countryCode", country_code)
                .putExtra("loginOtp", String.valueOf(otp))
                .putExtra("verifyingKey", "SignUpKey")
                .putExtra("type", "signup"));
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

        for (Country c : mCountries) {
            {
                if (c.getCode().equals(cCode)) {
                    return c;
                }
            }
        }
        return null;
    }
}
