package com.bang.module.home.profile.mypost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.bang.module.home.profile.mypost.fragment.MyPostFragment;

public class MyPostActivity extends BangParentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        init();

    }

    private void init() {
        RelativeLayout rlNotify = findViewById(R.id.rlNotify);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(getString(R.string.my_post));
        rlNotify.setVisibility(View.GONE);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.iv_addSurvey).setOnClickListener(this);
        addFragment(MyPostFragment.newInstance(), false, R.id.myPostFrameLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.iv_addSurvey:
                startActivity(new Intent(MyPostActivity.this, AddSurveyActivity.class));
                break;
        }

    }
}
