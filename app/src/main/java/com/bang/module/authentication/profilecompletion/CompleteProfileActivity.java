package com.bang.module.authentication.profilecompletion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.helper.Constant;
import com.bang.helper.CustomPhoneTextWatcher;
import com.bang.helper.CustomToast;
import com.bang.helper.Utils;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.authentication.profilecompletion.manager.SignUpManager;
import com.bang.module.authentication.profilecompletion.model.SignUpResponse;
import com.bang.image.picker.ImagePicker;
import com.bang.image.picker.ImageRotator;
import com.bang.serverhandling.ApiCallback;
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

    private ImageView ivCameraSelect, ivCountryFlag;
    private TextView tvProfileDoneButton;
    private EditText etFullName, etLoginMobileNumber;
    private Bitmap profileImageBitmap;
    private CircularImageView ivUserProfile;
    private Uri imageUri;
    private String device_token = "";
    private String countryCode = "1";
    private String mobileNumber = "";
    private String fb_image = "";
    private String socialType="";
    private String socialId = "";
    private LinearLayout llSelectCountryGo, llMobileNumberAtCompletePro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();

        countryCode = getIntent().getStringExtra("countryCode");
        mobileNumber = getIntent().getStringExtra("mobileNumber");


        device_token = FirebaseInstanceId.getInstance().getToken();
        ivCameraSelect.setOnClickListener(this);
        tvProfileDoneButton.setOnClickListener(this);
        llSelectCountryGo.setOnClickListener(this);
    }

    private void init() {
        ivCameraSelect = findViewById(R.id.ivCameraSelect);
        ivUserProfile = findViewById(R.id.ivUserProfile);
        tvProfileDoneButton = findViewById(R.id.tvProfileDoneButton);
        llMobileNumberAtCompletePro = findViewById(R.id.llMobileNumberAtCompletePro);
        etFullName = findViewById(R.id.etFullName);

        llSelectCountryGo = findViewById(R.id.llSelectCountryGo);
        ivCountryFlag = findViewById(R.id.ivCountryFlag);
        etLoginMobileNumber = findViewById(R.id.etLoginMobileNumber);

        Intent intent = getIntent();
        if (intent.getStringExtra("fullname") != null) {
            llMobileNumberAtCompletePro.setVisibility(View.VISIBLE);
            String fb_name = intent.getStringExtra("fullname");
            fb_image = intent.getStringExtra("fb_image");
            socialType = intent.getStringExtra("socialType");
            socialId = intent.getStringExtra("socialId");
            etFullName.setText(fb_name);
            Glide.with(this).load(fb_image).error(R.drawable.user_img).into(ivUserProfile);
        } else {
            llMobileNumberAtCompletePro.setVisibility(View.GONE);
        }

        if (getCurrentCountry() != null) {
            Country country = getCurrentCountry();
            countryCode = "+" + country.getPhoneCode();
            ivCountryFlag.setImageResource(country.getFlag());
        }
        etLoginMobileNumber.addTextChangedListener(new CustomPhoneTextWatcher(etLoginMobileNumber));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCameraSelect:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                                new String[]{Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                Constant.MY_PERMISSIONS_REQUEST_CAMERA);
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
                } else if (!fb_image.equals("") || imageUri != null) {
                    String number = etLoginMobileNumber.getText().toString().trim();
                    number = number.replace("-", "");
                    if (!number.equals("")) {
                        new SignUpManager(this, CompleteProfileActivity.this).callSignUoApi(etFullName.getText().toString(), countryCode,
                                number, device_token, "0", "0", imageUri,socialType,socialId, fb_image);
                    } else {
                        new SignUpManager(this, CompleteProfileActivity.this).callSignUoApi(etFullName.getText().toString(), countryCode,
                                mobileNumber, device_token, "0", "0",  imageUri,socialType,socialId, fb_image);
                    }
                } else {
                    CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, getString(R.string.select_image));
                }
                break;
            case R.id.llSelectCountryGo:
                Intent intent = new Intent(CompleteProfileActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    /**
     * for image picking time
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePicker.pickImage(CompleteProfileActivity.this);
                }
            }
            break;
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
                    //imageUri = getUserImageFile(profileImageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError error) {
                    CustomToast.getInstance(this).showToast(this, getResources().getString(R.string.alertOutOfMemory));
                }
                Glide.with(this).load(profileImageBitmap).apply(new RequestOptions().placeholder(ContextCompat.getDrawable(this, R.drawable.user_img))).into(ivUserProfile);
            } else if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                int country_flag = data.getIntExtra("country_flag", -1);
                String myCode = data.getStringExtra("country_code");
                countryCode = "+" + myCode;
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
        CustomToast.getInstance(CompleteProfileActivity.this).showToast(CompleteProfileActivity.this, errorMessage);
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
