package com.bang.module.home.addsurvey.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.adapter.SurveyPagerAdapter;
import com.bang.module.home.addsurvey.manager.SurveyManager;
import com.bang.module.home.addsurvey.model.CreateSurveyResponse;
import com.bang.module.home.addsurvey.model.SendDataModel;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;
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

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private LinearLayout llDataNotFound;
    private ViewPager mViewPager;
    private int globalPos;
    JSONArray myCustomArray;
    private List<SurveyQuestionResponse.DataBean.QuestionListBean> questionLists;
    List<SendDataModel> answers;

    public AddSurveyFragment() {
    }


    public static AddSurveyFragment newInstance(String param1, String param2, String param3, String param4) {
        AddSurveyFragment fragment = new AddSurveyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            System.out.println("%%%%%%%%%%%%%%%%" + mParam1 + "\n" + mParam2 + "\n" + mParam3 + "\n" + mParam4);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_survey, container, false);
        init(view);
        apiCalling();
        return view;
    }

    private void init(View view) {
        answers = new ArrayList<>();
        llDataNotFound = view.findViewById(R.id.llDataNotFound);
        mViewPager = view.findViewById(R.id.pager);
    }

    private void apiCalling() {
        new SurveyManager(this, mContext).callGetSurveyQuestionApi(mParam4, "survey");
    }

    @Override
    public void onClick(View v) {

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
            System.out.println("^^^^^^^^^^^^^^^^^^^^"+badge_title_key);
            ((BangParentActivity) mContext).replaceFragment(CongratulationSureveyFragment.newInstance(badge_title_key
                    , badge_sub_title
                    , badge_score
                    , badge_title
                    , badge_image), false, R.id.frameAddSurvey);
        }
    }


    private void callCreateSurveyAPi(String newJson) {
        new SurveyManager(this, mContext).callCreateSurveyApi(mParam1, mParam2, mParam3, mParam4, newJson);
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
