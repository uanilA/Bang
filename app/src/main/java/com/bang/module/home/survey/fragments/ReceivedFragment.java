package com.bang.module.home.survey.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.MainActivity;
import com.bang.module.home.survey.activity.SurveyDetailActivity;
import com.bang.module.home.survey.adapter.ReceiveSurveyAdapter;
import com.bang.module.home.survey.manager.ReceivedSurveyManager;
import com.bang.module.home.survey.model.SurveyReceiveResponse;
import com.bang.network.ApiCallback;
import com.bang.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

public class ReceivedFragment extends BaseFragment implements ApiCallback.GetReceiveSurveyCallback {

    private TextView tvTitleNotYet;
    private Session session;
    private View no_survey_avail;
    private RecyclerView rcvListReceiveSurvey;
    private int offset = 0;
    private ReceiveSurveyAdapter receiveSurveyAdapter;
    GridLayoutManager layoutManager;
    private ArrayList<SurveyReceiveResponse.DataBean.SurveyListBean> surveyReceiveListBeans;

    public ReceivedFragment() {
        // Required empty public constructor
    }


    public static ReceivedFragment newInstance(String param1, String param2) {
        ReceivedFragment fragment = new ReceivedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_received, container, false);
        session = new Session(mContext);
        init(view);
        receiveSurveyAdapter = new ReceiveSurveyAdapter(new ClickListener.SurveySingleClick() {
            @Override
            public void onSingleClick(int position) {
                startActivity(new Intent(mContext, SurveyDetailActivity.class)
                        .putExtra("surveyId", String.valueOf(surveyReceiveListBeans.get(position).getSurvey_id()))
                        .putExtra("type", "received"));
            }
        }, mContext, surveyReceiveListBeans);
        rcvListReceiveSurvey.setAdapter(receiveSurveyAdapter);

        return view;
    }

    private void init(View view) {
        surveyReceiveListBeans = new ArrayList<>();

        no_survey_avail = view.findViewById(R.id.no_survey_avail);
        rcvListReceiveSurvey = view.findViewById(R.id.rcvListReceiveSurvey);
        tvTitleNotYet = view.findViewById(R.id.tvTitleNotYet);
        tvTitleNotYet.setText("No Survey Received Yet!");
        layoutManager = new GridLayoutManager(mContext, 2);
        rcvListReceiveSurvey.setLayoutManager(layoutManager);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page != 1){
                    offset = offset+10; //load 10 items in recyclerview
                    apiCalling(offset);
                }
            }
        };
        rcvListReceiveSurvey.addOnScrollListener(scrollListener);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCalling(offset);
    }

    private void apiCalling(int myoffset) {
        new ReceivedSurveyManager(this, mContext).callAllUserList("received", "10", myoffset);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mContext != null) {
            if (isVisibleToUser) {
           //     apiCalling(offset);
                tvTitleNotYet.setText("No Survey Received Yet!");
            }
        }
    }


    @Override
    public void OnSuccessReceiveSurveyResponse(SurveyReceiveResponse receiveResponse) {
       // surveyReceiveListBeans.clear();
        surveyReceiveListBeans.addAll(receiveResponse.getData().getSurveyList());
        if (surveyReceiveListBeans.size() > 0) {
            no_survey_avail.setVisibility(View.GONE);
            rcvListReceiveSurvey.setVisibility(View.VISIBLE);
            receiveSurveyAdapter.notifyDataSetChanged();
        } else {
            no_survey_avail.setVisibility(View.VISIBLE);
            rcvListReceiveSurvey.setVisibility(View.GONE);
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
