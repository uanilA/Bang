package com.bang.module.preference;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.CustomToast;
import com.bang.base.BangParentActivity;
import com.bang.module.authentication.genderselection.GenderSelectionActivity;
import com.bang.module.home.MainActivity;
import com.bang.module.preference.adapter.PreferencePagerAdapter;
import com.bang.module.preference.manager.PreferenceManager;
import com.bang.module.preference.model.PreferenceResponse;
import com.bang.module.preference.model.SaveQuestionResponse;
import com.bang.network.ApiCallback;
import com.bang.base.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class PreferenceActivity extends BangParentActivity implements ApiCallback.PreferenceCallback, View.OnClickListener {

    Session session;
    private ViewPager mViewPager;
    private int globalPos;
    private List<PreferenceResponse.DataBean.QuestionListBean> questionLists;
    private String preferGender="";
    private String preference_key="";
    private LinearLayout llDataNotFound;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrence);

        if (getIntent().getStringExtra("prefer_gender") != null) {
            preferGender = getIntent().getStringExtra("prefer_gender");
            preference_key = getIntent().getStringExtra("preference_key");
        }

        session = new Session(PreferenceActivity.this);
        init();
        apiCalling();
        llDataNotFound.setOnClickListener(this);
    }

    private void apiCalling() {
        new PreferenceManager(this, PreferenceActivity.this).callPreferenceApi(preferGender, "preference");
    }

    private void init() {
        llDataNotFound = findViewById(R.id.llDataNotFound);
        mViewPager = findViewById(R.id.pager);
    }

    @Override
    public void onSuccessPreference(final PreferenceResponse preferenceResponse) {
        questionLists = new ArrayList<>();
        questionLists.addAll(preferenceResponse.getData().getQuestionList());
        if (questionLists.size() > 0) {
            mViewPager.setVisibility(View.VISIBLE);
            llDataNotFound.setVisibility(View.GONE);
        } else {
            mViewPager.setVisibility(View.GONE);
            llDataNotFound.setVisibility(View.VISIBLE);
        }

        PreferencePagerAdapter mCustomPagerAdapter = new PreferencePagerAdapter(new ClickListener.OptionClickListener() {
            @Override
            public void onOptionClick(int position) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < questionLists.get(position).getOption().size(); k++) {
                    if ("YES".equals(questionLists.get(position).getOption().get(k).getIsSelected())) {
                        if (sb.toString().isEmpty()) {
                            sb = new StringBuilder(questionLists.get(position).getOption().get(k).getOptionId() + "");
                        } else {
                            sb.append(",").append(questionLists.get(position).getOption().get(k).getOptionId());
                        }
                    }
                }
                globalPos = position + 1;
                if (position == questionLists.size() - 1) {
                    if (preference_key.equals("notSelectedPref")) {
                        if (!"".equals(sb.toString())) {
                                savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                            } else {
                                CustomToast.getInstance(PreferenceActivity.this).showToast(PreferenceActivity.this, "Please select at least one");
                            }
                       /* startActivity(new Intent(PreferenceActivity.this, MainActivity.class));
                        finish();
                        finishAffinity();*/
                    }else if (preference_key.equals("selectedPref")){
                        savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                    } else {
                         finish();
                    }
                } else {
                    if (!"null".equals(sb.toString()) && !"".equals(sb.toString())) {
                        savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                    }else {
                        CustomToast.getInstance(PreferenceActivity.this).showToast(PreferenceActivity.this,"Please select at least one");
                    }
                }
            }

            @Override
            public void onSingleClick(int position) {
                int size = preferenceResponse.getData().getQuestionList().size();
                int skipPos= position+1;
                if (position == size - 1 || size==1) {
                    if (preference_key.equals("notSelectedPref")) {
                        startActivity(new Intent(PreferenceActivity.this, MainActivity.class));
                        finish();
                        finishAffinity();
                    }else {
                        finish();
                    }
                } else {
                    mViewPager.setCurrentItem(skipPos);
                }
            }
        }, this, preferenceResponse.getData().getQuestionList());
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    private void savePreferenceApiCall(String questionId, String ansId) {
        new PreferenceManager(this, PreferenceActivity.this).callSaveQuestionApi(questionId, ansId);
    }

    @Override
    public void onSuccessSaveQuestions(SaveQuestionResponse saveQuestionResponse) {
        if (saveQuestionResponse.getCode() == 200) {
            if (globalPos-1 == questionLists.size()-1){
                startActivity(new Intent(PreferenceActivity.this, MainActivity.class));
                finish();
                finishAffinity();
             }else {
                mViewPager.setCurrentItem(globalPos);
                CustomToast.getInstance(PreferenceActivity.this).showToast(PreferenceActivity.this, saveQuestionResponse.getMessage());

            }
           } else {
            CustomToast.getInstance(PreferenceActivity.this).showToast(PreferenceActivity.this, saveQuestionResponse.getMessage());
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        showDialog(PreferenceActivity.this,errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(PreferenceActivity.this).showToast(PreferenceActivity.this, errorMessage);
    }


    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (v.getId() == R.id.llDataNotFound) {
            startActivity(new Intent(PreferenceActivity.this, GenderSelectionActivity.class));
            finish();
        }
    }
}
