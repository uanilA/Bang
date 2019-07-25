package com.bang.module.home.profile.updateProfile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.helper.Constant;
import com.bang.helper.CustomToast;
import com.bang.image.picker.ImagePicker;
import com.bang.image.picker.ImageRotator;
import com.bang.module.authentication.country.CountrySelectionActivity;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.home.profile.updateProfile.manager.UpdateProfileManager;
import com.bang.module.home.profile.updateProfile.model.UpdateProfileResponse;
import com.bang.network.ApiCallback;
import com.bang.utils.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bang.module.authentication.login.LoginActivity.SECOND_ACTIVITY_REQUEST_CODE;


public class UpdateProfileActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.UpdateProfileCallback {

     private CircleImageView ivUserProfile;
     private ImageView ivCameraSelect;
     private EditText etFullName;
     private TextView tvProfileDoneButton;
     private ImageView ivBack;
     private EditText etUpdateEmail;
     private EditText etUpdateMobile;
     private ImageView ivCountryFlag;

     private Uri imageUri;
     private Bitmap profileImageBitmap;
     private String myCode="";
     private String countryCode = "";
     private List<Country> mCountries;
     private int[] flags = Utility.countryFlags;
     private boolean isval=true;
    // private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadate_profile);
        init();
        tvProfileDoneButton.setOnClickListener(this);
        ivCameraSelect.setOnClickListener(this);
        ivUserProfile.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    private void init() {
        mCountries = new ArrayList<>();
        findViewById(R.id.llUpdateSelectCountryGo).setOnClickListener(this);
        LinearLayout llHomeMenu = findViewById(R.id.llHomeMenu);
        ivBack = findViewById(R.id.ivBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(getString(R.string.update_profile));
       // ImageView ivNotification = findViewById(R.id.ivNotification);
        llHomeMenu.setVisibility(View.GONE);
        ivUserProfile = findViewById(R.id.ivUserProfile);
        ivCameraSelect = findViewById(R.id.ivCameraSelect);
        etFullName = findViewById(R.id.etFullName);
        etUpdateMobile = findViewById(R.id.etUpdateMobile);
        tvProfileDoneButton = findViewById(R.id.tvProfileDoneButton);
        etUpdateEmail = findViewById(R.id.etUpdateEmail);
        ivCountryFlag = findViewById(R.id.ivCountryFlag);
        setProfileData();
    }

    private void setProfileData(){
        String userName = getIntent().getStringExtra("userName");
        String userImage = getIntent().getStringExtra("userImage");
        countryCode = getIntent().getStringExtra("countryCode");
        String mobileNumber = getIntent().getStringExtra("mobileNumber");
        String emailAddress = getIntent().getStringExtra("emailAddress");
        etFullName.setText(userName);
        etUpdateMobile.setText(mobileNumber);
        etUpdateEmail.setText(emailAddress);
        Glide.with(UpdateProfileActivity.this).load(userImage).error(R.drawable.user_img).into(ivUserProfile);
        if (isval){
            getCurrentCountry(countryCode);
        }
    }


    private void getCurrentCountry(String phoneCode) {
        isval = false;
        try {
            mCountries.addAll(Arrays.asList(
                    new Gson().fromJson(Utility.loadJSONFromAsset(UpdateProfileActivity.this, "country_code.json"), Country[].class)));
            for (int i = 0; i < mCountries.size(); i++) {
                mCountries.get(i).setFlag(flags[i]);
            }
            String tmpString = phoneCode.replaceAll("\\D", "");
            for (int i = 0; i < mCountries.size(); i++) {
                if (mCountries.get(i).getPhoneCode().equals(Integer.parseInt(tmpString))) {
                    ivCountryFlag.setImageResource(mCountries.get(i).getFlag());
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    /**
     * for image picking time
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePicker.pickImage(UpdateProfileActivity.this);
                }
            }
            break;
            case Constant.MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePicker.pickImage(UpdateProfileActivity.this);
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
            } else if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                    int country_flag = data.getIntExtra("country_flag", -1);
                    myCode = data.getStringExtra("country_code");
                    countryCode = "+"+myCode;
                    System.out.println("*****************" + country_flag);
                    if (country_flag != -1) {
                        ivCountryFlag.setImageResource(country_flag);
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {

       /* if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();*/

        switch (v.getId()){
            case R.id.ivCameraSelect:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                                new String[]{Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                Constant.MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        ImagePicker.pickImage(UpdateProfileActivity.this);
                    }
                } else {
                    ImagePicker.pickImage(UpdateProfileActivity.this);
                }
                break;
            case R.id.ivUserProfile:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                                new String[]{Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                Constant.MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        ImagePicker.pickImage(UpdateProfileActivity.this);
                    }
                } else {
                    ImagePicker.pickImage(UpdateProfileActivity.this);
                }
                break;
            case R.id.tvProfileDoneButton:
                  if (etFullName.getText().toString().equals("")){
                      CustomToast.getInstance(UpdateProfileActivity.this).showToast(UpdateProfileActivity.this,"Please enter full name");
                  }else if (etUpdateMobile.getText().toString().equals("")){
                      CustomToast.getInstance(UpdateProfileActivity.this).showToast(UpdateProfileActivity.this,"Please enter Mobile number");
                  }else {
                      new UpdateProfileManager(this,UpdateProfileActivity.this).callGetMyProfile(etFullName.getText().toString()
                              ,imageUri,etUpdateMobile.getText().toString(),countryCode);
                  }

                break;
            case R.id.ivBack:
                 finish();
                break;
            case R.id.llUpdateSelectCountryGo:
                Intent intent = new Intent(UpdateProfileActivity.this, CountrySelectionActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onSuccessUpdateProfile(UpdateProfileResponse updateProfileResponse) {
       CustomToast.getInstance(UpdateProfileActivity.this).showToast(UpdateProfileActivity.this,updateProfileResponse.getMessage());
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
       showDialog(UpdateProfileActivity.this,errorMessage);
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
        CustomToast.getInstance(UpdateProfileActivity.this).showToast(UpdateProfileActivity.this,errorMessage);
    }

}
