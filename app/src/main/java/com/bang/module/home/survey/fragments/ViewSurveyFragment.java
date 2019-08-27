package com.bang.module.home.survey.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.survey.activity.SurveyDetailActivity;
import com.bang.module.home.survey.adapter.ViewSurveyAdapter;
import com.bang.module.home.survey.manager.ViewSurveyManager;
import com.bang.module.home.survey.model.ViewSurveyModel;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;


public class ViewSurveyFragment extends BaseFragment implements ApiCallback.ViewSurveyCallback , View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;
    private Session session;
    private RecyclerView rcvViewSurveyList;
    //private long mLastClickTime = 0;


    public ViewSurveyFragment() {
        // Required empty public constructor
    }


    public static ViewSurveyFragment newInstance(String param1) {
        ViewSurveyFragment fragment = new ViewSurveyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_survey, container, false);
        session = new Session(mContext);
        init(view);
        return  view;
    }

    private void init(View view){
        rcvViewSurveyList = view.findViewById(R.id.rcvViewSurveyList);
       ((SurveyDetailActivity) mContext).findViewById(R.id.ivBack).setOnClickListener(this);
        TextView tvHeaderTitle = ((SurveyDetailActivity) mContext).findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(mContext.getString(R.string.view_survey));
        ImageView ivDetailMenu = ((SurveyDetailActivity) mContext).findViewById(R.id.ivDetailMenu);
        ivDetailMenu.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCalling();
    }

    private void apiCalling(){
        new ViewSurveyManager(this,mContext).callViewSurveyList(mParam1);
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
    public void OnSuccessViewResponse(ViewSurveyModel viewSurveyModel) {
        List<ViewSurveyModel.DataBean.SurveyAnswersBean> surveyAnswersBeans = new ArrayList<>(viewSurveyModel.getData().getSurvey_answers());
        ViewSurveyModel.DataBean.SurveyAnswersBean surveyAnswersBean=new ViewSurveyModel.DataBean.SurveyAnswersBean();
        surveyAnswersBean.setQuestion_title(getString(R.string.selecting_partner));
        surveyAnswersBean.setQuestion_description(getString(R.string.was_your_experience_with_a_male_or_female));
        if (viewSurveyModel.getData().getSurvey_partner_gender() == 0){
            surveyAnswersBean.setOption_photo(String.valueOf(R.drawable.boy_icon));
        }else if (viewSurveyModel.getData().getSurvey_partner_gender() == 1){
            surveyAnswersBean.setOption_photo(String.valueOf(R.drawable.girl_icon));
        }else if (viewSurveyModel.getData().getSurvey_partner_gender() == 2){
            surveyAnswersBean.setOption_photo(String.valueOf(R.drawable.transgender_male_icon));
        }else if (viewSurveyModel.getData().getSurvey_partner_gender() == 3){
            surveyAnswersBean.setOption_photo(String.valueOf(R.drawable.transgender_female));
        }else if (viewSurveyModel.getData().getSurvey_partner_gender() == 4){
            surveyAnswersBean.setOption_photo(String.valueOf(R.drawable.non_gender));
        }
        surveyAnswersBeans.add(0,surveyAnswersBean);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1);
        rcvViewSurveyList.setLayoutManager(layoutManager);
        ViewSurveyAdapter receiveSurveyAdapter = new ViewSurveyAdapter(new ClickListener.SurveySingleClick() {
            @Override
            public void onSingleClick(int position) {
            }
        }, mContext, surveyAnswersBeans);
        rcvViewSurveyList.setAdapter(receiveSurveyAdapter);
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
      activity.showDialog(mContext,errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        ((SurveyDetailActivity)mContext).showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        ((SurveyDetailActivity)mContext).hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext,errorMessage);
    }

    @Override
    public void onClick(View v) {

       /* if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();*/

        switch (v.getId()){
            case R.id.ivBack:
                ((SurveyDetailActivity)mContext).onBackPressed();
        }
    }
}
