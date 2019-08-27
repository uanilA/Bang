package com.bang.module.home.addsurvey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.MainActivity;
import com.bang.module.preference.adapter.PreferencePagerAdapter;
import com.bang.module.preference.manager.PreferenceManager;
import com.bang.module.preference.model.PreferenceResponse;
import com.bang.module.preference.model.SaveQuestionResponse;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;


public class RemainingPreferenceFragment extends BaseFragment implements ApiCallback.PreferenceCallback
           ,View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int globalPos;
    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;
    private LinearLayout llDataNotFound;
    private TextView tvFirstPreferenceNext;
    private List<PreferenceResponse.DataBean.QuestionListBean> questionLists;


    public RemainingPreferenceFragment() {
        // Required empty public constructor
    }


    public static RemainingPreferenceFragment newInstance(String param1, String param2) {
        RemainingPreferenceFragment fragment = new RemainingPreferenceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_remainig_preference, container, false);
        init(view);
        if (AppHelper.isConnectingToInternet(mContext)) {
            apiCalling();
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }
        tvFirstPreferenceNext.setOnClickListener(this);

        return view;
    }

    private void init(View view) {
        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);
        llDataNotFound = view.findViewById(R.id.llDataNotFound);
        mViewPager = view.findViewById(R.id.pager);
    }

    private void apiCalling() {
        new PreferenceManager(this, mContext).callPreferenceApi(mParam1, "preference");
    }


    @Override
    public void onSuccessPreference(PreferenceResponse preferenceResponse) {
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
                    if (mParam2.equals("notSelectedPref")) {
                        if (!"".equals(sb.toString())) {
                            savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                        } else {
                            CustomToast.getInstance(mContext).showToast(mContext, "Please select at least one");
                        }
                    }else if (mParam1.equals("selectedPref")){
                        savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                    } else {
                        activity.onBackPressed();
                    }
                } else {
                    if (!"null".equals(sb.toString()) && !"".equals(sb.toString())) {
                        savePreferenceApiCall(String.valueOf(questionLists.get(position).getQuestionId()), sb.toString());
                    }else {
                        CustomToast.getInstance(mContext).showToast(mContext,"Please select at least one");
                    }
                }
            }

            @Override
            public void onSingleClick(int position) {
                int size = preferenceResponse.getData().getQuestionList().size();
                int skipPos= position+1;
                if (position == size - 1 || size==1) {
                    activity.onBackPressed();
                } else {
                    mViewPager.setCurrentItem(skipPos);
                }
            }
        }, mContext, preferenceResponse.getData().getQuestionList());
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    @Override
    public void onSuccessSaveQuestions(SaveQuestionResponse saveQuestionResponse) {
        if (saveQuestionResponse.getCode() == 200) {
            if (globalPos-1 == questionLists.size()-1){
                activity.onBackPressed();
            }else {
                mViewPager.setCurrentItem(globalPos);
                CustomToast.getInstance(mContext).showToast(mContext, saveQuestionResponse.getMessage());

            }
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, saveQuestionResponse.getMessage());
        }
    }


    private void savePreferenceApiCall(String questionId, String ansId) {
        new PreferenceManager(this, mContext).callSaveQuestionApi(questionId, ansId);
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
       activity.showDialog(mContext,errorMessage);
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
        CustomToast.getInstance(mContext).showToast(mContext,errorMessage);
    }

    @Override
    public void onClick(View v) {
      if (v.getId() == R.id.tvFirstPreferenceNext){
          activity.onBackPressed();
      }
    }
}
