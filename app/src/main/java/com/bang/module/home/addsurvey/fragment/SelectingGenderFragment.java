package com.bang.module.home.addsurvey.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.base.BaseFragment;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.MainActivity;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.bang.module.home.addsurvey.adapter.SurveySelectGenderAdapter;
import com.bang.module.preference.manager.SelectedPreferencesManager;
import com.bang.module.preference.model.SelectedReferencesResponse;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SelectingGenderFragment extends BaseFragment implements ApiCallback.SelectedPreferenceCallback, View.OnClickListener {

    private RecyclerView rcvSelectedList;
    private String genderVal = "";
    private int questionSize;
    private int totalAnswer;
    private int strRemainigAns;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    private String date = "";
    private String time = "";
    private String forUserId = "";
    private String forUserName = "";
    private long mLastClickTime = 0;

    public SelectingGenderFragment() {
        // Required empty public constructor
    }


    public static SelectingGenderFragment newInstance(String date, String time, String userId, String userFullName) {
        SelectingGenderFragment fragment = new SelectingGenderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, date);
        args.putString(ARG_PARAM2, time);
        args.putString(ARG_PARAM3, userId);
        args.putString(ARG_PARAM4, userFullName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString(ARG_PARAM1);
            time = getArguments().getString(ARG_PARAM2);
            forUserId = getArguments().getString(ARG_PARAM3);
            forUserName = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selecting_gender, container, false);
        init(view);
        apiCalling();
        return view;
    }

    private void init(View view) {
        View main_tool_bar = ((AddSurveyActivity) mContext).findViewById(R.id.main_tool_bar);
        main_tool_bar.setVisibility(View.GONE);
        rcvSelectedList = view.findViewById(R.id.rcvSelectedList);
        view.findViewById(R.id.tvSelectedGenderNext).setOnClickListener(this);
        view.findViewById(R.id.tvSelectingGenderSkip).setOnClickListener(this);
    }

    private void apiCalling() {
        if (AppHelper.isConnectingToInternet(mContext)) {
            new SelectedPreferencesManager(this, mContext).callPreferenceApi("survey");
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSuccessSelectedPreference(SelectedReferencesResponse selectedReferencesResponse) {
        final List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists = new ArrayList<>(selectedReferencesResponse.getData().getUserSurveyStats());
        if (selectedPrefLists.size() > 0) {
            SurveySelectGenderAdapter selectedPrefAdapter = new SurveySelectGenderAdapter(position -> {
                questionSize = selectedPrefLists.get(position).getTotal_question();
                totalAnswer = selectedPrefLists.get(position).getTotal_answered();
                strRemainigAns = questionSize - totalAnswer;
                genderVal = String.valueOf(selectedPrefLists.get(position).getGender());
            }, selectedPrefLists, mContext);
            LinearLayoutManager llm = new LinearLayoutManager(mContext);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rcvSelectedList.setLayoutManager(llm);
            rcvSelectedList.setAdapter(selectedPrefAdapter);
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext, errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        activity.showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        activity.hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.tvSelectedGenderNext:
                if (!"".equals(genderVal)) {
                    if (strRemainigAns == 0) {
                        activity.addFragment(AddSurveyFragment.newInstance(date, time, forUserId, genderVal, forUserName), false, R.id.frameAddSurvey);
                    } else {
                        activity.addFragment(NoPreferenceFragment.newInstance(genderVal), true, R.id.frameAddSurvey);
                    }
                    genderVal ="";
                }else {
                    CustomToast.getInstance(mContext).showToast(mContext,"Please select gender");
                }
                break;
            case R.id.tvSelectingGenderSkip:
                startActivity(new Intent(mContext, MainActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
        }
    }
}
