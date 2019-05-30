package com.bang;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bang.module.authentication.login.LoginActivity;

public class SplashTwoActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private Runnable mRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashTwoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                // }
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mRunnable);
    }

}
