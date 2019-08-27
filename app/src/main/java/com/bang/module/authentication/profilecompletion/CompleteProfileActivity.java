package com.bang.module.authentication.profilecompletion;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BangParentActivity;
import com.bang.helper.AppHelper;
import com.bang.helper.Constant;
import com.bang.helper.CustomPhoneTextWatcher;
import com.bang.helper.CustomToast;
import com.bang.helper.Utils;
import com.bang.helper.Validation;
import com.bang.image.picker.ImagePicker;
import com.bang.image.picker.ImageRotator;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.authentication.login.LoginActivity;
import com.bang.module.authentication.login.manager.SendOtpManager;
import com.bang.module.authentication.otpverification.OtpVerificationActivity;
import com.bang.module.authentication.profilecompletion.manager.SignUpManager;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.network.ApiCallback;
import com.bang.utils.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.bang.module.authentication.login.LoginActivity.SECOND_ACTIVITY_REQUEST_CODE;


public class CompleteProfileActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.SignUpCallback {

    private ImageView ivCameraSelect;
    private TextView tvProfileDoneButton;
    private EditText etFullName,etLoginEmail,edtSignUpNumber,etSignUpPassword,etSignUpConfirmPassword;
    private Bitmap profileImageBitmap;
    private CircularImageView ivUserProfile;
    private String device_token = "";
    private String countryCode = "1";
    private String countryFlagCode = "US";
    private String emailAddress = "";
    private String fb_image = "";
    private String socialType="";
    private String socialId = "";
    private ImageView ivCountryFlag;
    private Uri imageUri;
    private LinearLayout llSelectCountryGo;
    private Dialog verifyDialog;
    private long mLastClickTime = 0;
   // private LinearLayout llChangeEmail;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();
        session = new Session(CompleteProfileActivity.this);
        if (getIntent().getStringExtra("emailAddress") != null) {
            emailAddress = getIntent().getStringExtra("emailAddress");
           // String verifyingKey = getIntent().getStringExtra("verifyingKey");
            etLoginEmail.setText(emailAddress);
        }
        device_token = FirebaseInstanceId.getInstance().getToken();
        ivCameraSelect.setOnClickListener(this);
        tvProfileDoneButton.setOnClickListener(this);
        llSelectCountryGo.setOnClickListener(this);
    }

    private void init() {
        ivCameraSelect       = findViewById(R.id.ivCameraSelect);
        ivUserProfile        = findViewById(R.id.ivUserProfile);
        tvProfileDoneButton  = findViewById(R.id.tvProfileDoneButton);
        ivCountryFlag        = findViewById(R.id.ivCountryFlag);
        LinearLayout llSignUpPassword = findViewById(R.id.llSignUpPassword);
        LinearLayout llSignUpConfirmPassword = findViewById(R.id.llSignUpConfirmPassword);
        edtSignUpNumber      = findViewById(R.id.edtSignUpNumber);
      //  llChangeEmail        = findViewById(R.id.llChangeEmail);
        etFullName           = findViewById(R.id.etFullName);
        etLoginEmail         = findViewById(R.id.etLoginEmail);
        etSignUpPassword     = findViewById(R.id.etSignUpPassword);
        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        llSelectCountryGo    = findViewById(R.id.llSelectCountryGo);

        ImageView ivCountryFlag = findViewById(R.id.ivCountryFlag);
        LinearLayout llMobileNumberAtCompletePro = findViewById(R.id.llMobileNumberAtCompletePro);
        edtSignUpNumber.addTextChangedListener(new CustomPhoneTextWatcher(edtSignUpNumber));
     //   llChangeEmail.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("fullname") != null) {
            llMobileNumberAtCompletePro.setVisibility(View.VISIBLE);
        //    llChangeEmail.setVisibility(View.GONE);
            etLoginEmail.setFocusable(false);
            llSignUpPassword.setVisibility(View.GONE);
            llSignUpConfirmPassword.setVisibility(View.GONE);
            String fb_name = intent.getStringExtra("fullname");
            fb_image = intent.getStringExtra("fb_image");
            socialType = intent.getStringExtra("socialType");
            socialId = intent.getStringExtra("socialId");
           // emailAddress = intent.getStringExtra("social_email");
            etLoginEmail.setText(intent.getStringExtra("social_email"));
            etFullName.setText(fb_name);
            Glide.with(this).load(fb_image).error(R.drawable.user_img).error(R.drawable.logo).into(ivUserProfile);
        } else {
            etLoginEmail.setFocusable(false);
            llSignUpPassword.setVisibility(View.VISIBLE);
            llSignUpConfirmPassword.setVisibility(View.VISIBLE);
            llMobileNumberAtCompletePro.setVisibility(View.VISIBLE);
        }
        if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            countryCode = "+" + country.getPhoneCode();
            ivCountryFlag.setImageResource(country.getFlag());
        }
    }

    @Override
    public void onClick(View v) {
        Validation val = new Validation();
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.ivCameraSelect:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        ImagePicker.pickImage(CompleteProfileActivity.this);
                    }
                } else {
                    ImagePicker.pickImage(CompleteProfileActivity.this);
                }

                break;
            case R.id.tvProfileDoneButton:
                if (etFullName.getText().toString().equals("")) {
                    CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.select_name));
                }else  if (!val.isNameValid(etFullName)) {
                    CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.acc_name_invalid));
                }else if (!val.isEmailValid(etLoginEmail)){
                    CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.invalid_email));
                } else if (edtSignUpNumber.getText().toString().equals("")){
                  CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this,getString(R.string.invalid_number));
                } else if (fb_image.equals("") && imageUri == null) {
                    CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.select_image));
                }else{
                    String number = edtSignUpNumber.getText().toString().trim();
                    number = number.replace("-", "");
                    if (!countryCode.equals("") && !emailAddress.equals("") ) {
                        if (etSignUpPassword.getText().toString().equals("")) {
                            CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this,getString(R.string.enter_pass));
                        } else if (!etSignUpPassword.getText().toString().equals(etSignUpConfirmPassword.getText().toString())){
                            CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this,getString(R.string.password_match));
                        } else {
                            if (AppHelper.isConnectingToInternet(CompleteProfileActivity.this)) {
                                new SignUpManager(this, CompleteProfileActivity.this).callSignUpApi(etFullName.getText().toString(), etLoginEmail.getText().toString()
                                        , etSignUpPassword.getText().toString(), countryCode, number, device_token, "0", "0", imageUri, socialType, socialId,countryFlagCode, fb_image);
                            } else {
                                CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.alert_no_network));
                            }
                        }
                    } else if (!number.equals("")){
                        if (AppHelper.isConnectingToInternet(CompleteProfileActivity.this)) {
                            new SignUpManager(this, CompleteProfileActivity.this).callSignUpApi(etFullName.getText().toString(),etLoginEmail.getText().toString()
                                    ,etSignUpPassword.getText().toString(), countryCode, number, device_token, "0", "0", imageUri,socialType,socialId,countryFlagCode, fb_image);
                        } else {
                            CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.alert_no_network));
                        }
                    }else {
                        CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.select_mobile));
                    }
                }
                break;
            case R.id.llSelectCountryGo:
                 Intent intent = new Intent(CompleteProfileActivity.this, CountrySelectionActivity.class);
                 startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.llChangeEmail:
                 finish();
            case R.id.tvYesDialog:
                 startActivity(new Intent(CompleteProfileActivity.this, LoginActivity.class));
                 finish();
                break;
            case R.id.tvNoDialog:
                 verifyDialog.dismiss();
                break;
        }
    }

    /**
     * for image picking time
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
            case Constant.MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePicker.pickImage(CompleteProfileActivity.this);
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 234) {    // Image Picker
                imageUri = ImagePicker.getImageURIFromResult(this, requestCode, resultCode, data);
                try {
                    Bitmap tempBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    int orientation = ImageRotator.getRotation(this, imageUri, true);
                    profileImageBitmap = ImageRotator.rotate(tempBitmap, orientation);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError error) {
                    CustomToast.getInstance(this).showToast(this, getResources().getString(R.string.alertOutOfMemory));
                }
                Glide.with(this).load(profileImageBitmap).apply(new RequestOptions().placeholder(ContextCompat.getDrawable(this, R.drawable.user_img))).into(ivUserProfile);
            }
        }
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int country_flag = data.getIntExtra("country_flag", -1);
                String myCode = data.getStringExtra("country_code");
                countryFlagCode = data.getStringExtra("countryFlagCode");
                countryCode = "+"+myCode;
                System.out.println("*****************" + country_flag);
                if (country_flag != -1) {
                    ivCountryFlag.setImageResource(country_flag);
                }
            }
        }

    }


    @Override
    public void onSuccessSignUp(SignUpResponse signUpResponse) {
        if (signUpResponse.getCode() == 200) {
            session.createRegistration(signUpResponse.getData());
            session.setUserLoggedIn();
            startActivity(new Intent(CompleteProfileActivity.this, GenderSelectionActivity.class));
            finish();
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
        System.out.println("^^^^^^^^^^^^^^^^^^^^"+errorMessage);
        String otherMsg = "";
        if (errorMessage.equals("This email is already registered.")){
            otherMsg = "Do you want to change email?";
            openMobileRegisteredDialog(errorMessage,otherMsg);
        }else if (errorMessage.equals("This mobile number is already registered.")){
            otherMsg = "Do you want to change mobile number?";
            openMobileRegisteredDialog(errorMessage,otherMsg);
        }else {
            CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, errorMessage);
        }

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


    // Dialog to mobile registered
    private void openMobileRegisteredDialog(String message , String mysmg) {
        verifyDialog = new Dialog(CompleteProfileActivity.this);
        verifyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        verifyDialog.setContentView(R.layout.custom_mobile_verify_alert_dialog);

        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(verifyDialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        verifyDialog.getWindow().setAttributes(lWindowParams);

        TextView tvMobileRegistered= verifyDialog.findViewById(R.id.tvMobileRegistered);
        tvMobileRegistered.setText(message);
        TextView tvDoYouChangeNumMsg = verifyDialog.findViewById(R.id.tvDoYouChangeNumMsg);
        TextView tvYesDialog = verifyDialog.findViewById(R.id.tvYesDialog);
        TextView tvNoDialog = verifyDialog.findViewById(R.id.tvNoDialog);
        tvDoYouChangeNumMsg.setText(mysmg);

        tvYesDialog.setOnClickListener(this);
        tvNoDialog.setOnClickListener(this);

        verifyDialog.getWindow().setGravity(Gravity.CENTER);
        verifyDialog.show();
    }


}
