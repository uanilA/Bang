package com.bang.module.home.newsfeed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
        ivBack.setOnClickListener(this);
        if (getIntent().getStringExtra("post_for_screen") != null) {
            screenPost = getIntent().getStringExtra("post_for_screen");
            if (screenPost.equals("CreatePost")) {
                addFragment(CreatePostFragment.newInstance(), false, R.id.frameCreatePost);
            } else {
                addFragment(ReportFragment.newInstance(), false, R.id.frameCreatePost);
            }
        }
    }

    private void init() {
        ivBack = findViewById(R.id.ivBack);
        LinearLayout llHomeMenu = findViewById(R.id.llHomeMenu);
        llHomeMenu.setVisibility(View.GONE);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        if (screenPost.equals("CreatePost"))
        tvHeaderTitle.setText(getString(R.string.create_post));
        else
        tvHeaderTitle.setText(getString(R.string.report));
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