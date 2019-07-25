package com.bang.module.home.addsurvey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.home.addsurvey.fragment.AddSurveyFragment;
import com.bang.module.home.addsurvey.fragment.CongratulationSureveyFragment;
import com.bang.module.home.addsurvey.fragment.ContactFragment;
import com.bang.module.home.addsurvey.fragment.DateEventFragment;
import com.bang.module.home.addsurvey.fragment.SelectingGenderFragment;

public class AddSurveyActivity extends BangParentActivity {

   public View main_tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_survey);
        main_tool_bar = findViewById(R.id.main_tool_bar);
        addFragment(DateEventFragment.newInstance(), false, R.id.frameAddSurvey);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameAddSurvey);
                    if (fragment instanceof DateEventFragment) {
                        main_tool_bar.setVisibility(View.GONE);
                    } else if (fragment instanceof ContactFragment) {
                        main_tool_bar.setVisibility(View.VISIBLE);
                    }else if (fragment instanceof SelectingGenderFragment) {
                        main_tool_bar.setVisibility(View.GONE);
                    } else if (fragment instanceof AddSurveyFragment){

                    }
                }
            };
            getSupportFragmentManager().addOnBackStackChangedListener(listener);
        }else {
          finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameAddSurvey);
        if (fragment instanceof ContactFragment) {
            ContactFragment contactFragment = (ContactFragment) fragment;
            contactFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (fragment instanceof CongratulationSureveyFragment){
            CongratulationSureveyFragment congratulationSureveyFragment= (CongratulationSureveyFragment) fragment;
            congratulationSureveyFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
}