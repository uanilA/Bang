package com.bang.module.home.addsurvey.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.adapter.SurveyPagerAdapter;
import com.bang.module.home.addsurvey.manager.SurveyManager;
import com.bang.module.home.addsurvey.model.CreateSurveyResponse;
import com.bang.module.home.addsurvey.model.SendDataModel;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;
import com.bang.module.preference.SelectedPreferencesActivity;
import com.bang.module.preference.manager.SelectedPreferencesManager;
import com.bang.network.ApiCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class AddSurveyFragment extends BaseFragment implements ApiCallback.SurveyCallback, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    private String date= "";
    private String time = "";
    private String forUserId = "";
    private String genderVal = "";
    private String forUserName = "";
    private LinearLayout llDataNotFound;
    private ViewPager mViewPager;
    private int globalPos;
    private JSONArray myCustomArray;
    private List<SurveyQuestionResponse.DataBean.QuestionListBean> questionLists;
    private List<SendDataModel> answers;
    private TextView tvGoBack;

    public AddSurveyFragment() {
    }


    public static AddSurveyFragment newInstance(String param1, String param2, String param3, String param4,String forUserName) {
        AddSurveyFragment fragment = new AddSurveyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, forUserName);
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
            genderVal = getArguments().getString(ARG_PARAM4);
            forUserName = getArguments().getString(ARG_PARAM5);
            System.out.println("%%%%%%%%%%%%%%%%" + date + "\n" + time + "\n" + forUserId + "\n" + genderVal);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_survey, container, false);
        init(view);
        tvGoBack.setOnClickListener(this);
        apiCalling();
        return view;
    }

    private void init(View view) {
        answers = new ArrayList<>();
        llDataNotFound = view.findViewById(R.id.llDataNotFound);
        tvGoBack = view.findViewById(R.id.tvGoBack);
        mViewPager = view.findViewById(R.id.pager);
    }

    private void apiCalling() {
        if (AppHelper.isConnectingToInternet(mContext)) {
            new SurveyManager(this, mContext).callGetSurveyQuestionApi(genderVal, "survey");
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvGoBack) {
            activity.onBackPressed();
        }
    }


    @Override
    public void onSuccessSurvey(SurveyQuestionResponse surveyQuestionResponse) {
        questionLists = new ArrayList<>();
        questionLists.addAll(surveyQuestionResponse.getData().getQuestionList());
        if (questionLists.size() > 0) {
            mViewPager.setVisibility(View.VISIBLE);
            llDataNotFound.setVisibility(View.GONE);
        } else {
            mViewPager.setVisibility(View.GONE);
            llDataNotFound.setVisibility(View.VISIBLE);
        }

        SurveyPagerAdapter pagerAdapter = new SurveyPagerAdapter(new ClickListener.SurveyClickListener() {
            @Override
            public void onOptionClick(int position, String surveyId) {
                SendDataModel sendDataModel = new SendDataModel();
                globalPos = position + 1;
                if (position == questionLists.size() - 1) {
                    Gson gson = new GsonBuilder().create();
                    try {
                        if (!"null".equals(surveyId) && !"".equals(surveyId)) {
                            sendDataModel.setQuestionId(questionLists.get(position).getQuestionId());
                            sendDataModel.setOptionId(Integer.parseInt(surveyId));
                            answers.add(sendDataModel);
                            System.out.println("@@@@@@@@@@@@@@@@@" + answers.size());
                            myCustomArray = new JSONArray(gson.toJson(answers));
                            callCreateSurveyAPi(myCustomArray.toString());
                        } else {
                            CustomToast.getInstance(mContext).showToast(mContext, "Please select Survey");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!"null".equals(surveyId) && !"".equals(surveyId)) {
                        sendDataModel.setQuestionId(questionLists.get(position).getQuestionId());
                        sendDataModel.setOptionId(Integer.parseInt(surveyId));
                        answers.add(sendDataModel);
                        System.out.println("@@@@@@@@@@@@@@@@@" + answers.size());
                        mViewPager.setCurrentItem(globalPos);
                    } else {
                        CustomToast.getInstance(mContext).showToast(mContext, "Please select Survey");
                    }
                }
            }

            @Override
            public void onSingleClick(int position) {
                int size = questionLists.size();
                int skipPos = position + 1;
                if (position == size - 1 || size == 1) {
                    Gson gson = new GsonBuilder().create();
                    try {
                        myCustomArray = new JSONArray(gson.toJson(answers));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callCreateSurveyAPi(myCustomArray.toString());
                } else {
                    mViewPager.setCurrentItem(skipPos);
                }
            }
        }, mContext, questionLists);
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onSuccessCreateSurvey(CreateSurveyResponse createSurveyResponse) {
        if (createSurveyResponse.getCode() == 200) {
            String badge_title_key = createSurveyResponse.getData().getBadge_data().getBadge_title_key();
            String badge_sub_title = createSurveyResponse.getData().getBadge_data().getBadge_sub_title();
            String badge_score = String.valueOf(createSurveyResponse.getData().getBadge_data().getBadge_score());
            String badge_title = createSurveyResponse.getData().getBadge_data().getBadge_title();
            String badge_image = createSurveyResponse.getData().getBadge_data().getBadge_image();
            String SurveyUserId= createSurveyResponse.getData().getSurveyed_user_id();
            String SurveyId = String.valueOf(createSurveyResponse.getData().getSurvey_id());
            System.out.println("********************"+badge_title_key+"\n"+badge_sub_title+"\n"
            +badge_score+"\n"+badge_title);
            System.out.println("^^^^^^^^^^^^^^^^^^^^"+badge_title_key);
            ((BangParentActivity) mContext).replaceFragment(CongratulationSureveyFragment.newInstance(badge_title_key
                    , badge_sub_title
                    , badge_score
                    , SurveyUserId
                    , SurveyId), false, R.id.frameAddSurvey);
        }
    }


    private void callCreateSurveyAPi(String newJson) {
        if (AppHelper.isConnectingToInternet(mContext)) {
            new SurveyManager(this, mContext).callCreateSurveyApi(date, time, forUserId, genderVal,forUserName, newJson);
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }
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
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }
}
