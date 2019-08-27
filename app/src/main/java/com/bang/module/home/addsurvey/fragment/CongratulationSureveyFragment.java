package com.bang.module.home.addsurvey.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import androidx.annotation.NonNull;

import com.bang.helper.AppHelper;
import com.bang.module.home.addsurvey.manager.SurveyManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.home.MainActivity;
import com.bang.module.home.addsurvey.manager.ShareSurveyPresenter;
import com.bang.module.home.addsurvey.model.ShareSurveyModel;
import com.bang.network.ApiCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class CongratulationSureveyFragment extends BaseFragment implements View.OnClickListener, ApiCallback.ShareSurveyCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    private String mParam1;
    private String mParam2;
    private String survey_score;
    private String surveyUserId;
    private String surveyId;

    private RelativeLayout rlPrivateSelected;
    private RelativeLayout rlPublicSelected;
    private TextView tvPrivateSelected;
    private TextView tvPublic;
    private ImageView ivPrivateSelected;
    private ImageView ivPublicSelected;
    private BottomSheetDialog dialog;
    private RelativeLayout rlAddictedBg;
    private Session session;
    private long mLastClickTime = 0;
    private String is_anonymous = "";

    private String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


    public CongratulationSureveyFragment() {
        // Required empty public constructor
    }


    public static CongratulationSureveyFragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        CongratulationSureveyFragment fragment = new CongratulationSureveyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * External storage permission in run time
     */
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(mContext, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
                screenShot(rlAddictedBg);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                screenShot(rlAddictedBg);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            survey_score = getArguments().getString(ARG_PARAM3);
            surveyUserId = getArguments().getString(ARG_PARAM4);
            surveyId = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_congratulation_surevey, container, false);
        session = new Session(mContext);
        init(view);
        return view;
    }

    private void BottomDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_view, null);
        dialog = new BottomSheetDialog(mContext, R.style.CustomBottomSheetDialogTheme);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.findViewById(R.id.llShareWithBangFollower).setOnClickListener(this);
        dialog.findViewById(R.id.iv_CloseDialog).setOnClickListener(this);
        dialog.findViewById(R.id.tvShareWithSocialMedia).setOnClickListener(this);
        dialog.show();
    }

    private void init(View view) {

        ImageView ivCong = view.findViewById(R.id.ivCong);
        ImageView ivBackImage = view.findViewById(R.id.ivBackImage);
        TextView tvUserName = view.findViewById(R.id.tvUserName);
        TextView tvSurveyDescription = view.findViewById(R.id.tvSurveyDescription);
        view.findViewById(R.id.tvSkipCongrate).setOnClickListener(this);
        TextView tvShareCongrate = view.findViewById(R.id.tvShareCongrate);
        tvShareCongrate.setOnClickListener(this);
        RelativeLayout rlShowOrHideLayout = view.findViewById(R.id.rlShowOrHideLayout);

        rlAddictedBg = view.findViewById(R.id.rlAddictedBg);
        tvUserName.setText(session.getRegistration().getFullName());

        switch (mParam1) {
            case "unsatisfied":
                ivCong.setImageResource(R.drawable.share_unsatisfied);
                ivBackImage.setImageResource(R.drawable.unsatisfied_bg);
                break;
            case "satisfied":
                ivCong.setImageResource(R.drawable.share_satisfied);
                ivBackImage.setImageResource(R.drawable.satisfied_bg);
                break;
            case "addicted":
                ivCong.setImageResource(R.drawable.share_addicted);
                ivBackImage.setImageResource(R.drawable.addictive_bg);
                break;
        }

        tvSurveyDescription.setText(mParam2);

        rlPrivateSelected = view.findViewById(R.id.rlPrivateSelected);
        rlPublicSelected = view.findViewById(R.id.rlPublicSelected);

        tvPrivateSelected = view.findViewById(R.id.tvPrivateSelected);
        tvPublic = view.findViewById(R.id.tvPublic);

        ivPrivateSelected = view.findViewById(R.id.ivPrivateSelected);
        ivPublicSelected = view.findViewById(R.id.ivPublicSelected);

        rlPrivateSelected.setOnClickListener(this);
        rlPublicSelected.setOnClickListener(this);

        if (surveyUserId.equals("0")){
            rlShowOrHideLayout.setVisibility(View.VISIBLE);
            rlPrivateSelected.setEnabled(false);
            rlPublicSelected.setEnabled(false);
            tvShareCongrate.setEnabled(false);
        }else {
            rlShowOrHideLayout.setVisibility(View.GONE);
            rlPrivateSelected.setEnabled(true);
            rlPublicSelected.setEnabled(true);
            tvShareCongrate.setEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.tvSkipCongrate:
                startActivity(new Intent(mContext, MainActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;

            case R.id.rlPrivateSelected:
                is_anonymous = "1";
                rlPrivateSelected.setBackgroundResource(R.drawable.gender_selected_background);
                rlPublicSelected.setBackgroundResource(R.drawable.login_background);
                ivPublicSelected.setVisibility(View.GONE);
                ivPrivateSelected.setVisibility(View.VISIBLE);
                tvPublic.setTextColor(ContextCompat.getColor(mContext,R.color.colorSelectCountry));
                tvPrivateSelected.setTextColor(getResources().getColor(R.color.colorBang));

                break;

            case R.id.rlPublicSelected:
                is_anonymous = "0";
                rlPublicSelected.setBackgroundResource(R.drawable.gender_selected_background);
                rlPrivateSelected.setBackgroundResource(R.drawable.login_background);
                ivPublicSelected.setVisibility(View.VISIBLE);
                ivPrivateSelected.setVisibility(View.GONE);
                tvPrivateSelected.setTextColor(getResources().getColor(R.color.colorSelectCountry));
                tvPublic.setTextColor(ContextCompat.getColor(mContext,R.color.colorBang));

                break;
            case R.id.tvShareCongrate:
                BottomDialog();
                break;

            case R.id.iv_CloseDialog:
                dialog.dismiss();
                break;

            case R.id.tvShareWithSocialMedia:
                checkPermissions();
                screenShot(rlAddictedBg);
                break;

            case R.id.llShareWithBangFollower:
                if (is_anonymous.equals("")){
                    CustomToast.getInstance(mContext).showToast(mContext,"Please select type.");
                }else {
                    shareApiCalling();
                }
                break;
        }
    }

    private void shareApiCalling() {
        String bangTitle = "["+session.getRegistration().getUserId()+ "] is "+ mParam1 + " with @ ["+ surveyUserId+"]";
        if (AppHelper.isConnectingToInternet(mContext)) {
            new ShareSurveyPresenter(this, mContext).shareSurveyApiCall(bangTitle,surveyUserId,surveyId,is_anonymous,survey_score);
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }
    }

    /**
     * Take screen shot code
     */

    private void screenShot(RelativeLayout scr_shot_view) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".png";
            scr_shot_view.setDrawingCacheEnabled(true);
            scr_shot_view.buildDrawingCache(true);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            Bitmap bitmap = Bitmap.createBitmap(scr_shot_view.getDrawingCache());
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, outputStream);
            scr_shot_view.destroyDrawingCache();
            shareOnSocial(String.valueOf(imageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void shareOnSocial(String sharePath) {
        File imageFile = new File(sharePath);
        Uri uri;
        Intent sharIntent = new Intent(Intent.ACTION_SEND);
        String ext = imageFile.getName().substring(imageFile.getName().lastIndexOf(".") + 1);
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getMimeTypeFromExtension(ext);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sharIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", imageFile);
            sharIntent.setDataAndType(uri, type);
        } else {
            uri = Uri.fromFile(imageFile);
            sharIntent.setDataAndType(uri, type);
        }
        sharIntent.setType("image/png");
        sharIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sharIntent.putExtra(Intent.EXTRA_SUBJECT, "Bang App");
        startActivity(Intent.createChooser(sharIntent, "Share:"));
    }


    @Override
    public void onSuccessSharePost(ShareSurveyModel shareSurveyModel) {
       dialog.dismiss();
       CustomToast.getInstance(mContext).showToast(mContext,shareSurveyModel.getMessage());
       startActivity(new Intent(mContext,MainActivity.class));
       activity.finish();
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
}
