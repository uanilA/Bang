package com.bang.preference.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bang.R;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.preference.fragment.ConnectionFragment;
import com.bang.preference.fragment.PreferenceFragment;
import com.bang.preference.fragment.SkillsFragment;
import com.bang.preference.fragment.formale.HygieneFragment;
import com.bang.preference.fragment.formale.SizePreferenceFragment;
import com.bang.preference.fragment.formale.StaminaPreferenceFragment;
import com.bang.preference.fragment.formale.StrengthPreferenceFragment;

public class PreferenceActivity extends BangParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrence);

        addFragment(PreferenceFragment.newInstance(), false, R.id.framedPreferenceReplace);
    }

    @Override
    public void onBackPressed() {
        //  Handler handler = new Handler();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.framedPreferenceReplace);
                    if (fragment instanceof PreferenceFragment) {
                    } else if (fragment instanceof SizePreferenceFragment) {
                    } else if (fragment instanceof StrengthPreferenceFragment) {
                    } else if (fragment instanceof StaminaPreferenceFragment) {
                    } else if (fragment instanceof SkillsFragment) {
                    } else if (fragment instanceof HygieneFragment) {
                    } else if (fragment instanceof ConnectionFragment) {
                    }
                }
            };
            getSupportFragmentManager().addOnBackStackChangedListener(listener);

        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.framedPreferenceReplace);
            if (fragment instanceof SizePreferenceFragment) {
                replaceFragment(PreferenceFragment.newInstance(), false, R.id.framedPreferenceReplace);

            } else if (fragment instanceof PreferenceFragment) {
                if (getIntent().getStringExtra("EditProfile") != null) {
                    finish();
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }
}
