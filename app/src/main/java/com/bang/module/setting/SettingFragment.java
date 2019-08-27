package com.bang.module.setting;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.setting.manager.LogoutManager;
import com.bang.module.setting.model.ChangePasswordResponse;
import com.bang.module.setting.model.LogoutResponse;
import com.bang.network.ApiCallback;

import java.util.Objects;


public class SettingFragment extends BaseFragment implements View.OnClickListener, ApiCallback.LogoutCallback {

    private RelativeLayout rlLogOut;
    private RelativeLayout rlChangePassword;
    private EditText etOldPassword, etNewPassword, etConfirmPassword;
    private Session session;
    private Dialog changePassDialog;
    private long mLastClickTime = 0;

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        SettingFragment settingFragment = new SettingFragment();
        Bundle args = new Bundle();
        settingFragment.setArguments(args);
        return settingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        session = new Session(mContext);
        init(view);
        rlLogOut.setOnClickListener(this);
        rlChangePassword.setOnClickListener(this);
        return view;
    }

    private void init(View view) {
        rlLogOut = view.findViewById(R.id.rlLogOut);
        rlChangePassword = view.findViewById(R.id.rlChangePassword);
    }

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.rlChangePassword:
                openChangePassDialog();
                break;
            case R.id.rlLogOut:
                new LogoutManager(this, mContext).callLogoutApi();
                break;
            case R.id.tvUpdatePassword:
                if (etOldPassword.getText().toString().isEmpty()) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Please enter old password");
                } else if (etNewPassword.getText().toString().isEmpty()) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Please enter new password");
                } else if (etConfirmPassword.getText().toString().isEmpty()) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Please enter confirm password");
                } else if (!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    CustomToast.getInstance(mContext).showToast(mContext, "New password and confirm password is not match");
                } else {
                    if (AppHelper.isConnectingToInternet(mContext)) {
                        new LogoutManager(this, mContext).callChangePasswordApi(etOldPassword.getText().toString(),
                                etNewPassword.getText().toString(), etConfirmPassword.getText().toString());
                    } else {
                        CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
                    }

                }
                break;
            case R.id.ivCrossChangePass:
                changePassDialog.dismiss();
                break;
        }
    }

    @Override
    public void onSuccessLogout(LogoutResponse logoutResponse) {
        if (logoutResponse.getCode().equals(200)) {
            session.logout();
        }
    }

    @Override
    public void onSuccessChangePassword(ChangePasswordResponse changePasswordResponse) {
        CustomToast.getInstance(mContext).showToast(mContext, changePasswordResponse.getMessage());
        changePassDialog.dismiss();
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext, errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        ((SettingActivity) mContext).showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        ((SettingActivity) mContext).hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

    // Dialog to select gender
    private void openChangePassDialog() {
        changePassDialog = new Dialog(mContext);
        Objects.requireNonNull(changePassDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        changePassDialog.setContentView(R.layout.dialog_change_password);
        changePassDialog.setCancelable(false);
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(changePassDialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        changePassDialog.getWindow().setAttributes(lWindowParams);
        TextView tvUpdatePassword = changePassDialog.findViewById(R.id.tvUpdatePassword);
        ImageView ivCrossChangePass = changePassDialog.findViewById(R.id.ivCrossChangePass);
        etOldPassword = changePassDialog.findViewById(R.id.etOldPassword);
        etNewPassword = changePassDialog.findViewById(R.id.etNewPassword);
        etConfirmPassword = changePassDialog.findViewById(R.id.etConfirmPassword);

        tvUpdatePassword.setOnClickListener(this);
        ivCrossChangePass.setOnClickListener(this);

        changePassDialog.getWindow().setGravity(Gravity.CENTER);
        changePassDialog.show();

    }
}
