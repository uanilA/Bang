package com.bang.module.home.newsfeed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;

/**
 * Created by anil
 * Date: 18/07/19
 * Time: 12:05 PM
 */

public class CreatePostActivity extends BangParentActivity implements View.OnClickListener {

    private ImageView ivBack;
    private String screenPost = "";
    private String report_To_userId = "";
    private String report_news_feedId = "";
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        if (getIntent().getStringExtra("post_for_screen") != null) {
            screenPost = getIntent().getStringExtra("post_for_screen");
            if (screenPost.equals("CreatePost")) {
                addFragment(CreatePostFragment.newInstance(), false, R.id.frameCreatePost);
            } else {
                report_To_userId = getIntent().getStringExtra("report_To_userId");
                report_news_feedId = getIntent().getStringExtra("report_news_feedId");
                addFragment(ReportFragment.newInstance(report_To_userId, report_news_feedId), false, R.id.frameCreatePost);
            }
        }
        init();
        ivBack.setOnClickListener(this);

    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        LinearLayout llHomeMenu = findViewById(R.id.llHomeMenu);
        llHomeMenu.setVisibility(View.GONE);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        if (screenPost.equals("CreatePost")) {
            tvHeaderTitle.setText(getString(R.string.create_post));
        } else {
            tvHeaderTitle.setText(getString(R.string.report));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameCreatePost);
        if (fragment instanceof CreatePostFragment) {
            CreatePostFragment createPostFragment = (CreatePostFragment) fragment;
            createPostFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (v.getId() == R.id.ivBack) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}