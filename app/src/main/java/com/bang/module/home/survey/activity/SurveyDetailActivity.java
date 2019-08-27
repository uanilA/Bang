package com.bang.module.home.survey.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.home.survey.fragments.SurveyDetailFragment;
import com.bang.module.home.survey.fragments.ViewSurveyFragment;

public class SurveyDetailActivity extends BangParentActivity {

    private TextView tvHeaderTitle;
    private ImageView ivDetailMenu;
    private String surveyId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);
        if (getIntent().getStringExtra("surveyId") != null){
           surveyId = getIntent().getStringExtra("surveyId");
        }
        addFragment(SurveyDetailFragment.newInstance(getIntent().getStringExtra("surveyId"),getIntent().getStringExtra("type")), false, R.id.frameSurveyDetail);
        init();
    }

    private void init() {
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        ivDetailMenu = findViewById(R.id.ivDetailMenu);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            final FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameSurveyDetail);
                    if (fragment instanceof SurveyDetailFragment) {
                        tvHeaderTitle.setText(getString(R.string.survey_detail));
                        ivDetailMenu.setVisibility(View.VISIBLE);
                    } else if (fragment instanceof ViewSurveyFragment) {
                        tvHeaderTitle.setText(getString(R.string.view_survey));
                        ivDetailMenu.setVisibility(View.GONE);
                    }
                }
            };
            getSupportFragmentManager().addOnBackStackChangedListener(listener);
        } else {
            super.onBackPressed();
        }
    }
}
