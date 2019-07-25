package com.bang.module.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bang.R;
import com.bang.base.BangParentActivity;

public class SettingActivity extends BangParentActivity implements View.OnClickListener {


    //private long mLastClickTime = 0;
    private ImageView ivSettingBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        addFragment(SettingFragment.newInstance(),false,R.id.setting_frame);
        init();
        ivSettingBack.setOnClickListener(this);
    }

    private void init() {

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
