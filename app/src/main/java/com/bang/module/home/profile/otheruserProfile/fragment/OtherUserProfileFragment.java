package com.bang.module.home.profile.otheruserProfile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.country.model.Country;
import com.bang.module.home.profile.bandrequest.model.AcceptRejectModel;
import com.bang.module.home.profile.bandrequest.model.BangRequestsModel;
import com.bang.module.home.profile.bandrequest.presenter.BangRequestUpdatePresenter;
import com.bang.module.home.profile.followersfollowing.FollowersActivity;
import com.bang.module.home.profile.otheruserProfile.manager.BangRequestPresenter;
import com.bang.module.home.profile.otheruserProfile.manager.FollowUserPresenter;
import com.bang.module.home.profile.otheruserProfile.manager.OtherUserPresenter;
import com.bang.module.home.profile.otheruserProfile.model.FollowUnFollowResponse;
import com.bang.module.home.profile.otheruserProfile.model.OtherUserProfileResponse;
import com.bang.network.ApiCallback;
import com.bang.utils.Utility;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class OtherUserProfileFragment extends BaseFragment implements ApiCallback.OtherUserCallback, ApiCallback.BangRequestUpdateCallback
        ,ApiCallback.FollowUserCallback, View.OnClickListener , ApiCallback.SendBangRequestCallback {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    private CircleImageView ivProfile;
    private TextView tvNameProfile;
    private ImageView ivCountryCodeForProfile;
    private ImageView ivBackProfileImage;
    private ImageView ivBangRequestIco;

    private TextView txtMobileNumber;
    private TextView tvReceiveSurveyCount;
    private TextView tvTotalSentSurveyCount;
    private TextView tvAddictiveCount;
    private TextView tvSatisfiedCount;
    private TextView tvUnsatisfiedCount;
    private TextView tvPostCount;
    private TextView tvFollowingCount;
    private TextView tvFollowersCount;
    private TextView tvFollow;
    private ImageView ivEditProfile;
    private LinearLayout llOtherFollowers;
    private LinearLayout llOtherFollowing;
    private LinearLayout llAcceptReject;
    private TextView tvBangRequestName;
    private TextView tvAccept;
    private TextView tvReject;
    private LinearLayout llBangProfile;

    private long mLastClickTime = 0;
    private boolean isVal = true;
    private List<Country> mCountries;
    private int[] flags = Utility.countryFlags;
    private String bangConnectid;

    public OtherUserProfileFragment() {
        // Required empty public constructor
    }

    public static OtherUserProfileFragment newInstance(String param1) {
        OtherUserProfileFragment fragment = new OtherUserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_user_profile, container, false);
        init(view);

        tvFollow.setOnClickListener(this);
        ivEditProfile.setOnClickListener(this);
        llOtherFollowers.setOnClickListener(this);
        llOtherFollowing.setOnClickListener(this);
        tvAccept.setOnClickListener(this);
        tvReject.setOnClickListener(this);
        return view;
    }

    private void apiCalling() {
        new OtherUserPresenter(this, mContext).callOtherUSerProfile(mParam1);
    }

    private void init(View view) {
        mCountries = new ArrayList<>();

        tvAccept = view.findViewById(R.id.tvAccept);
        tvReject = view.findViewById(R.id.tvReject);
        ivProfile = view.findViewById(R.id.ivProfile);
        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        ivCountryCodeForProfile = view.findViewById(R.id.ivCountryCodeForProfile);
        ivBackProfileImage = view.findViewById(R.id.ivBackProfileImage);
        txtMobileNumber = view.findViewById(R.id.txtMobileNumber);
        tvAddictiveCount = view.findViewById(R.id.tvAddictiveCount);
        tvTotalSentSurveyCount = view.findViewById(R.id.tvTotalSentSurveyCount);
        tvReceiveSurveyCount = view.findViewById(R.id.tvReceiveSurveyCount);
        tvSatisfiedCount = view.findViewById(R.id.tvSatisfiedCount);
        tvUnsatisfiedCount = view.findViewById(R.id.tvUnsatisfiedCount);
        tvPostCount = view.findViewById(R.id.tvPostCount);
        tvFollowingCount = view.findViewById(R.id.tvFollowingCount);
        tvFollowersCount = view.findViewById(R.id.tvFollowersCount);
        tvFollow = view.findViewById(R.id.tvFollow);
        ivEditProfile = view.findViewById(R.id.ivEditProfile);
        llOtherFollowers = view.findViewById(R.id.llOtherFollowers);
        llOtherFollowing = view.findViewById(R.id.llOtherFollowing);
        tvBangRequestName = view.findViewById(R.id.tvBangRequestName);
        ivBangRequestIco = view.findViewById(R.id.ivBangRequestIco);
        llAcceptReject = view.findViewById(R.id.llAcceptReject);
        llBangProfile = view.findViewById(R.id.llBangProfile);
        view.findViewById(R.id.llBangProfile).setOnClickListener(this);
        apiCalling();
    }

    @Override
    public void onSuccessOtherProfile(OtherUserProfileResponse otherUserProfileResponse) {
        bangConnectid = otherUserProfileResponse.getData().getBang_connection_id();
        if (otherUserProfileResponse.getData().getIs_following() == 0) {
            tvFollow.setText(getString(R.string.follow));
        } else if (otherUserProfileResponse.getData().getIs_following() == 1) {
            tvFollow.setText(getString(R.string.unfollow));
        }
        String requestStatus = otherUserProfileResponse.getData().getBang_request_status();
             switch (requestStatus) {
                case "0":
                    if (String.valueOf(otherUserProfileResponse.getData().getUserId()).equals(otherUserProfileResponse.getData().getRequest_sender_user_id())){
                        llBangProfile.setVisibility(View.GONE);
                        llAcceptReject.setVisibility(View.VISIBLE);
                    }else {
                        llBangProfile.setVisibility(View.VISIBLE);
                        llAcceptReject.setVisibility(View.GONE);
                        tvBangRequestName.setText(getString(R.string.pending));
                        ivBangRequestIco.setVisibility(View.GONE);
                    }
                    break;
                case "1":
                    llBangProfile.setVisibility(View.VISIBLE);
                    llAcceptReject.setVisibility(View.GONE);
                    tvBangRequestName.setText(getString(R.string.messages));
                    ivBangRequestIco.setVisibility(View.GONE);
                    break;
                case "2":
                    llBangProfile.setVisibility(View.VISIBLE);
                    llAcceptReject.setVisibility(View.GONE);
                    tvBangRequestName.setText(getString(R.string.bang));
                    ivBangRequestIco.setVisibility(View.VISIBLE);
                    break;
                case "":
                    llBangProfile.setVisibility(View.VISIBLE);
                    llAcceptReject.setVisibility(View.GONE);
                    tvBangRequestName.setText(getString(R.string.bang));
                    ivBangRequestIco.setVisibility(View.VISIBLE);
                    break;
            }

        String myNumber = numberformate(otherUserProfileResponse.getData().getPhone_number());
        txtMobileNumber.setText(myNumber);
        tvNameProfile.setText(otherUserProfileResponse.getData().getFull_name());
        tvReceiveSurveyCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_received_survey()));
        tvTotalSentSurveyCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_send_survey()));
        tvAddictiveCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_addictive()));
        tvSatisfiedCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_satisfied()));
        tvFollowersCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_followers()));
        tvFollowingCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_following()));
        tvPostCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_post()));
        tvUnsatisfiedCount.setText(String.valueOf(otherUserProfileResponse.getData().getTotal_unsatisfied()));
        Glide.with(mContext).load(otherUserProfileResponse.getData().getUser_profile_photo()).error(R.drawable.logo).into(ivProfile);
        Glide.with(mContext).load(otherUserProfileResponse.getData().getUser_profile_photo()).error(R.drawable.logo).into(ivBackProfileImage);
        if (isVal) {
            getCurrentCountry(otherUserProfileResponse.getData().getCountry_code());
        }
    }

    @Override
    public void onSuccessFollowResponse(FollowUnFollowResponse followUnFollowResponse) {
        tvFollowersCount.setText(String.valueOf(followUnFollowResponse.getTotal_followers()));
        if (followUnFollowResponse.getMessage().equals("Follow successfully")) {
            tvFollow.setText(getString(R.string.unfollow));
        } else if (followUnFollowResponse.getMessage().equals("Unfollow successfully")) {
            tvFollow.setText(getString(R.string.follow));
        }
    }

    @Override
    public void onBangRequestSuccess(BangRequestsModel bangRequestsModel) {
        tvBangRequestName.setText(getString(R.string.pending));
        ivBangRequestIco.setVisibility(View.GONE);
    }

    private void getCurrentCountry(String phoneCode) {
        isVal = false;
        try {
            mCountries.addAll(Arrays.asList(
                    new Gson().fromJson(Utility.loadJSONFromAsset(mContext, "countries.json"), Country[].class)));
            for (int i = 0; i < mCountries.size(); i++) {
                mCountries.get(i).setFlag(flags[i]);
            }
            //String tmpString = phoneCode.replaceAll("\\D", "");
            for (int i = 0; i < mCountries.size(); i++) {
                if (mCountries.get(i).getPhoneCode().equals(phoneCode)) {
                    ivCountryCodeForProfile.setImageResource(mCountries.get(i).getFlag());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessRequestUpdate(AcceptRejectModel acceptRejectModel) {
        llAcceptReject.setVisibility(View.GONE);
      //  String reqStatus = String.valueOf(acceptRejectModel.getData().getBang_request_detail().getRequest_status());
        apiCalling();
        /*switch (reqStatus) {
            case "1":
                tvBangRequestName.setText(getString(R.string.accepted));
                break;
            case "2":
                tvBangRequestName.setText(getString(R.string.rejected));
                break;
        }*/
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
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.tvFollow:
                new FollowUserPresenter(this, mContext).callFollowApi(mParam1);
                break;
            case R.id.ivEditProfile:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.llOtherFollowers:
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", mParam1)
                        .putExtra("follows_data", "followers"));
                break;
            case R.id.llOtherFollowing:
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", mParam1)
                        .putExtra("follows_data", "following"));
                break;
            case R.id.llBangProfile:
                if (tvBangRequestName.getText().toString().equals(getString(R.string.messages))){
                    System.out.println("NA");
                }else if (tvBangRequestName.getText().toString().equals(getString(R.string.pending))){
                    System.out.println("NA");
                }else {
                    requestApiCall(mParam1);
                }

                break;
            case R.id.tvAccept:
                statusUpdateCalling("1");
                break;
            case R.id.tvReject:
                statusUpdateCalling("2");
                break;
        }
    }

    private void statusUpdateCalling(String status) {
        new BangRequestUpdatePresenter(this, mContext).requestUpdateApiCall(bangConnectid, status);
    }

    private void requestApiCall(String receiverUserId) {
        new BangRequestPresenter(this, mContext).callBangRequest(receiverUserId);
    }
}