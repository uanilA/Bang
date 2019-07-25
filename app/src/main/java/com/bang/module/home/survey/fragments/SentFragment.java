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
import com.bang.module.home.survey.activity.SurveyDetailActivity;
import com.bang.module.home.survey.adapter.SentSurveyAdapter;
import com.bang.module.home.survey.manager.SentSurveyManager;
import com.bang.module.home.survey.model.SurveySentResponse;
import com.bang.network.ApiCallback;
import com.bang.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;


public class SentFragment extends BaseFragment implements ApiCallback.GetSurveyCallback {

    private TextView tvTitleNotYet;
    private View no_survey_avail;
    private ArrayList<SurveySentResponse.DataBean.SurveyListBean> surveySentListBeans;
    private Session session;
    private RecyclerView rcvListSentSurvey;
   // SwipeRefreshLayout simpleSwipeRefreshLayout;
    private int offset = 0;
    private SentSurveyAdapter sentSurveyAdapter;


    public SentFragment() {
        // Required empty public constructor
    }


    public static SentFragment newInstance() {
        SentFragment fragment = new SentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sent, container, false);
        session = new Session(mContext);
        init(view);

        sentSurveyAdapter = new SentSurveyAdapter(new ClickListener.SurveySingleClick() {
            @Override
            public void onSingleClick(int position) {
                startActivity(new Intent(mContext, SurveyDetailActivity.class)
                        .putExtra("surveyId", String.valueOf(surveySentListBeans.get(position).getSurvey_id()))
                        .putExtra("type", "sent"));
            }
        }, mContext, surveySentListBeans);
        rcvListSentSurvey.setAdapter(sentSurveyAdapter);
        return view;
    }

    private void init(View view) {
        surveySentListBeans = new ArrayList<>();
        no_survey_avail = view.findViewById(R.id.no_survey_avail);
        tvTitleNotYet = view.findViewById(R.id.tvTitleNotYet);
        rcvListSentSurvey = view.findViewById(R.id.rcvListSentSurvey);
      //  simpleSwipeRefreshLayout = view.findViewById(R.id.simpleSwipeRefreshLayout);
      //  simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorBang);
        tvTitleNotYet.setText("No Survey Sent Yet!");
        /* For Grid Layout */
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        rcvListSentSurvey.setLayoutManager(layoutManager);
        /*On Refresh listener*/
       /* simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
               *//* simpleSwipeRefreshLayout.setRefreshing(false);
                offset = 0;
                apiCalling(offset);
                sentSurveyAdapter.notifyDataSetChanged();*//*
            }
        });*/

        /* Pagination */
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //increase the offset for pagination
                if (page > 0){
                    offset = offset+10;
                    apiCalling(offset);
                }


            }
        };
        rcvListSentSurvey.addOnScrollListener(scrollListener);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCalling(offset);
    }

    private void apiCalling(int myoffset) {
        new SentSurveyManager(this, mContext).callAllUserList("sent", "10", myoffset);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mContext != null) {
            if (isVisibleToUser) {
           //     apiCalling(offset);
                tvTitleNotYet.setText("No Survey Sent Yet!");
            }
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        session.logout();
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
    public void OnSuccessSurveyResponse(SurveySentResponse surveySentResponse) {
       // surveySentListBeans.clear();
        surveySentListBeans.addAll(surveySentResponse.getData().getSurveyList());
        if (surveySentListBeans.size() > 0) {
            no_survey_avail.setVisibility(View.GONE);
            rcvListSentSurvey.setVisibility(View.VISIBLE);
            sentSurveyAdapter.notifyDataSetChanged();
        } else {
            no_survey_avail.setVisibility(View.VISIBLE);
            rcvListSentSurvey.setVisibility(View.GONE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        apiCalling(offset);
    }
}
