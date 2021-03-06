package com.bang.module.home.profile.getprofile;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.home.profile.followersfollowing.FollowersActivity;
import com.bang.module.home.profile.getprofile.presenter.GetProfilePresenter;
import com.bang.module.home.profile.getprofile.model.MyProfileResponse;
import com.bang.module.home.profile.mypost.MyPostActivity;
import com.bang.module.home.profile.updateProfile.UpdateProfileActivity;
import com.bang.module.preference.SelectedPreferencesActivity;
import com.bang.module.setting.SettingActivity;
import com.bang.network.ApiCallback;
import com.bang.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends BaseFragment implements View.OnClickListener, ApiCallback.GetProfileCallback {

    private CircleImageView ivProfile;
    private TextView tvNameProfile;
    private TextView txtMobileNumber;
    private ImageView ivEditProfile;
    private ImageView ivBackProfileImage;
    private ImageView ivCountryCodeForProfile;
    private TextView tvUnsatisfiedCount;
    private TextView tvMySatisfiedCount;
    private TextView tvAddictiveCount;
    private TextView tvMyPostCount;
    private TextView tvMyFollowingCount;
    private TextView tvMyFollowerCount;

    private String userName = "", userImage = "", userId = "";
    private String countryCode = "", mobileNumber = "";
    private String emailAddress = "" , flagCode = "";

    private List<Country> mCountries;
    private int[] flags = Utility.countryFlags;
    private boolean isval = true;

    private long mLastClickTime = 0;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);

        mCountries = new ArrayList<>();
        ivEditProfile.setOnClickListener(this);
        return view;
    }

    private void init(View view) {
        view.findViewById(R.id.rlProfileSetting).setOnClickListener(this);
        view.findViewById(R.id.llPreferences).setOnClickListener(this);
        ivProfile = view.findViewById(R.id.ivProfile);
        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        txtMobileNumber = view.findViewById(R.id.txtMobileNumber);
        ivEditProfile = view.findViewById(R.id.ivEditProfile);
        ivBackProfileImage = view.findViewById(R.id.ivBackProfileImage);
        ivCountryCodeForProfile = view.findViewById(R.id.ivCountryCodeForProfile);

        tvUnsatisfiedCount = view.findViewById(R.id.tvMyUnsatisfiedCount);
        tvMySatisfiedCount = view.findViewById(R.id.tvMySatisfiedCount);
        tvAddictiveCount = view.findViewById(R.id.tvAddictiveCount);
        tvMyPostCount = view.findViewById(R.id.tvMyPostCount);
        tvMyFollowingCount = view.findViewById(R.id.tvMyFollowingCount);
        tvMyFollowerCount = view.findViewById(R.id.tvMyFollowerCount);

        view.findViewById(R.id.llFollowers).setOnClickListener(this);
        view.findViewById(R.id.llFollowing).setOnClickListener(this);
        view.findViewById(R.id.llPost).setOnClickListener(this);
        view.findViewById(R.id.rlBangRequest).setOnClickListener(this);
        apiCalling();
    }

    private void apiCalling() {
        new GetProfilePresenter(this, mContext).callGetMyProfile();
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.rlProfileSetting:
                startActivity(new Intent(mContext, SettingActivity.class)
                .putExtra("setting","GoingToSetting"));
                break;
            case R.id.ivEditProfile:
                startActivity(new Intent(mContext, UpdateProfileActivity.class)
                        .putExtra("userName", userName)
                        .putExtra("userImage", userImage)
                        .putExtra("countryCode", countryCode)
                        .putExtra("mobileNumber", mobileNumber)
                        .putExtra("emailAddress", emailAddress)
                        .putExtra("flagCode",flagCode));
                break;
            case R.id.llPreferences:
                startActivity(new Intent(mContext, SelectedPreferencesActivity.class));
                break;
            case R.id.llPost:
                startActivity(new Intent(mContext, MyPostActivity.class));
                break;
            case R.id.llFollowers:
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", userId)
                        .putExtra("follows_data", "followers"));
                break;
            case R.id.llFollowing:
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", userId)
                        .putExtra("follows_data", "following"));
                break;
            case R.id.rlBangRequest:
                startActivity(new Intent(mContext, SettingActivity.class)
                        .putExtra("setting","BangRequest"));
                break;
        }
    }

    @Override
    public void onSuccessGetProfile(MyProfileResponse myProfileResponse) {
        if (myProfileResponse.getCode() == 200) {
            isval = true;
            userId = String.valueOf(myProfileResponse.getData().getUserId());
            userName = myProfileResponse.getData().getFull_name();
            userImage = String.valueOf(myProfileResponse.getData().getProfile_photo());
            countryCode = myProfileResponse.getData().getCountry_code();
            mobileNumber = myProfileResponse.getData().getPhone_number();
          //  String output = String.format("%s (%s) %s-%s", mobileNumber.substring(0,1) ,mobileNumber.substring(1,4), mobileNumber.substring(4,7), mobileNumber.substring(7,11));
            emailAddress = myProfileResponse.getData().getEmail();
            flagCode = myProfileResponse.getData().getCountry_flag_code();

            tvUnsatisfiedCount.setText(String.valueOf(myProfileResponse.getData().getTotal_unsatisfied()));
            tvMySatisfiedCount.setText(String.valueOf(myProfileResponse.getData().getTotal_satisfied()));
            tvAddictiveCount.setText(String.valueOf(myProfileResponse.getData().getTotal_addictive()));
            tvMyPostCount.setText(String.valueOf(myProfileResponse.getData().getTotal_post()));
            tvMyFollowingCount.setText(String.valueOf(myProfileResponse.getData().getTotal_following()));
            tvMyFollowerCount.setText(String.valueOf(myProfileResponse.getData().getTotal_followers()));

            Glide.with(mContext).load(userImage).error(R.drawable.user_img).error(R.drawable.logo).into(ivProfile);
            Glide.with(mContext).load(userImage).error(R.drawable.user_img).error(R.drawable.logo).into(ivBackProfileImage);
            tvNameProfile.setText(userName);
            String myNumber = numberformate(myProfileResponse.getData().getPhone_number());
            txtMobileNumber.setText(myNumber);
            if (isval) {
                getCurrentCountry(myProfileResponse.getData().getCountry_flag_code());
            }
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext, errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        activity.showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        activity.hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCalling();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getCurrentCountry(String phoneCode) {
        isval = false;
        try {
            mCountries.clear();
            mCountries.addAll(Arrays.asList(
                    new Gson().fromJson(Utility.loadJSONFromAsset(mContext, "countries.json"), Country[].class)));
            for (int i = 0; i < mCountries.size(); i++) {
                mCountries.get(i).setFlag(flags[i]);
            }
            phoneCode = phoneCode.replaceAll("\\s","");

            // String tmpString = phoneCode.replaceAll("\\D", "");
            for (int i = 0; i < mCountries.size(); i++) {
                String myCode=mCountries.get(i).getCode();
                myCode=myCode.replaceAll("\\s","");
                if (myCode.equals(phoneCode) || myCode.toLowerCase().equals(phoneCode)) {
                    ivCountryCodeForProfile.setImageResource(mCountries.get(i).getFlag());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}