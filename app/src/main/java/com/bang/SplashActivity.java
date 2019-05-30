package com.bang;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.bang.application.session.Session;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.authentication.login.LoginActivity;
import com.bang.module.home.MainActivity;

import java.security.MessageDigest;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getKeyHashFacebook();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                session = new Session(SplashActivity.this);
                if (session.getUserLoggedIn() && session.getRegistration() != null) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {    // If user is not logged in, Make user login
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        };
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mRunnable);
    }

    //Get Hask Key for facebook integration
    private void getKeyHashFacebook() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
