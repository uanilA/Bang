package com.bang.module.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.module.authentication.baseactivity.BangParentActivity;

public class SettingActivity extends BangParentActivity implements View.OnClickListener {

    RelativeLayout rlLogOut;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        session = new Session(SettingActivity.this);
        rlLogOut.setOnClickListener(this);
    }

    private void init() {
        rlLogOut = findViewById(R.id.rlLogOut);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.rlLogOut:
                    session.logout();
                    break;
            }
    }
}
