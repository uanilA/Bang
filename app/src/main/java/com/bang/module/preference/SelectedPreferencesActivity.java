package com.bang.module.preference;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BangParentActivity;
import com.bang.helper.CustomToast;
import com.bang.module.preference.adapter.SelectedPrefAdapter;
import com.bang.module.preference.manager.SelectedPreferencesManager;
import com.bang.module.preference.model.SelectedPrefList;
import com.bang.module.preference.model.SelectedReferencesResponse;
import com.bang.network.ApiCallback;
import com.bang.base.GetClickListener;

import java.util.ArrayList;
import java.util.List;

public class SelectedPreferencesActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.SelectedPreferenceCallback {

    private RecyclerView rcvPreferencesList;
    private Session session;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        session = new Session(SelectedPreferencesActivity.this);
        init();
        apiCalling();
    }

    private void init() {
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        LinearLayout llHomeMenu = findViewById(R.id.llHomeMenu);
        rcvPreferencesList = findViewById(R.id.rcvPreferencesList);

        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.tvSelectedPreferenceNext).setOnClickListener(this);

        tvHeaderTitle.setText(getString(R.string.small_pref));
        llHomeMenu.setVisibility(View.GONE);
    }


    private void apiCalling() {
        new SelectedPreferencesManager(this, SelectedPreferencesActivity.this).callPreferenceApi("preference");
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSelectedPreferenceNext:
                break;
        }
    }

    @Override
    public void onSuccessSelectedPreference(SelectedReferencesResponse selectedReferencesResponse) {
        final List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists = new ArrayList<>();
        selectedPrefLists.addAll(selectedReferencesResponse.getData().getUserSurveyStats());
        SelectedPrefAdapter selectedPrefAdapter = new SelectedPrefAdapter(new GetClickListener() {
            @Override
            public void onClick(int position) {
                if (selectedPrefLists.get(position).getTotal_question() != selectedPrefLists.get(position).getTotal_answered()) {
                    startActivity(new Intent(SelectedPreferencesActivity.this, PreferenceActivity.class)
                            .putExtra("prefer_gender", String.valueOf(selectedPrefLists.get(position).getGender()))
                            .putExtra("preference_key", "selectedPref"));
                } else {
                    CustomToast.getInstance(SelectedPreferencesActivity.this).showToast(SelectedPreferencesActivity.this, "Preference already completed");
                }
            }
        }, selectedPrefLists, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvPreferencesList.setLayoutManager(llm);
        rcvPreferencesList.setAdapter(selectedPrefAdapter);

    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        session.logout();
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
      CustomToast.getInstance(SelectedPreferencesActivity.this).showToast(SelectedPreferencesActivity.this,errorMessage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        apiCalling();
    }
}
