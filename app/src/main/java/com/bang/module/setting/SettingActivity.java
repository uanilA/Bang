package com.bang.module.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.home.profile.bandrequest.BangRequestFragment;

public class SettingActivity extends BangParentActivity implements View.OnClickListener {


    //private long mLastClickTime = 0;
    private ImageView ivSettingBack;
    private String checkVal = "";
    private TextView tvHeaderTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (getIntent().getStringExtra("setting") != null){
            checkVal = getIntent().getStringExtra("setting");
            if (checkVal.equals("GoingToSetting")){
                addFragment(SettingFragment.newInstance(),false,R.id.setting_frame);
            }else {
                addFragment(BangRequestFragment.newInstance(),false,R.id.setting_frame);
            }
        }


        init();
        ivSettingBack.setOnClickListener(this);
    }

    private void init() {
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        if (checkVal.equals("")){
            tvHeaderTitle.setText(getString(R.string.setting));
        }else {
            tvHeaderTitle.setText(getString(R.string.bang_requests));
        }
        ivSettingBack = findViewById(R.id.ivSettingBack);
    }

    @Override
    public void onClick(View v) {

       /* if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();*/

        if (v.getId() == R.id.ivSettingBack) {
            finish();
        }
    }


}
