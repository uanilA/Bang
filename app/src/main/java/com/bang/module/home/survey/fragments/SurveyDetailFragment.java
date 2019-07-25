package com.bang.module.home.survey.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.module.home.survey.activity.SurveyDetailActivity;
import com.bang.module.home.survey.adapter.CommentsAdapter;
import com.bang.module.home.survey.manager.SurveyDetailManager;
import com.bang.module.home.survey.model.DocommentResponse;
import com.bang.module.home.survey.model.SurveyDetailModel;
import com.bang.network.ApiCallback;
import com.bang.utils.EndlessRecyclerViewScrollListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class SurveyDetailFragment extends BaseFragment implements View.OnClickListener, ApiCallback.ViewSurveyDetailCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RelativeLayout rlBadgeBackground;
    private ImageView ivDetailSurvey;
    private TextView tvBadgeName;
    private TextView tvBadgeUserName;
    private EditText edCommentText;
    private CircleImageView ivUserImageDetail;
    private List<SurveyDetailModel.DataBean.SurveyCommentListBean> surveyCommentListBeans;
    private CommentsAdapter commentsAdapter;
    private RecyclerView rcvCommentList;
    private TextView tvViewSurvey;
    private TextView tvNotYesComment;
    private int offset = 0;
    private String userId = "";
    private long mLastClickTime = 0;

    private SurveyDetailModel.DataBean.SurveyDataBean mSurveyData;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SurveyDetailFragment() {
        // Required empty public constructor
    }

    public static SurveyDetailFragment newInstance(String param1, String param2) {
        SurveyDetailFragment fragment = new SurveyDetailFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_detail, container, false);
        init(view);
        commentsAdapter = new CommentsAdapter(mContext, surveyCommentListBeans);
        rcvCommentList.setAdapter(commentsAdapter);

        tvViewSurvey.setOnClickListener(this);
        tvBadgeUserName.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCalling(offset);
    }

    private void apiCalling(int myOffset) {
        new SurveyDetailManager(this, mContext).callViewSurveyDetail(mParam1, mParam2, String.valueOf(myOffset), "20");
    }

    private void init(View view) {
        surveyCommentListBeans = new ArrayList<>();
        rcvCommentList = view.findViewById(R.id.rcvCommentList);
        tvNotYesComment = view.findViewById(R.id.tvNotYesComment);
        ((SurveyDetailActivity) mContext).findViewById(R.id.ivBack).setOnClickListener(this);
        TextView tvHeaderTitle = ((SurveyDetailActivity) mContext).findViewById(R.id.tvHeaderTitle);
        rlBadgeBackground = view.findViewById(R.id.rlBadgeBackground);
        tvHeaderTitle.setText(mContext.getString(R.string.survey_detail));

        ((SurveyDetailActivity) mContext).findViewById(R.id.ivDetailMenu).setOnClickListener(this);
        edCommentText = view.findViewById(R.id.edCommentText);
        tvViewSurvey = view.findViewById(R.id.tvViewSurvey);
        ivDetailSurvey = view.findViewById(R.id.ivDetailSurvey);
        tvBadgeName = view.findViewById(R.id.tvBadgeName);
        tvBadgeUserName = view.findViewById(R.id.tvBadgeUserName);
        ivUserImageDetail = view.findViewById(R.id.ivUserImageDetail);
        ivUserImageDetail.setOnClickListener(this);
        view.findViewById(R.id.ivSendComment).setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvCommentList.setLayoutManager(layoutManager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //increase the offset for pagination
                if (page != 0){
                    offset = offset+20;
                    apiCalling(offset);
                }
            }
        };
        rcvCommentList.addOnScrollListener(scrollListener);
    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.ivBack:
                ((SurveyDetailActivity) mContext).onBackPressed();
                break;
            case R.id.tvViewSurvey:
                ((SurveyDetailActivity) mContext).addFragment(ViewSurveyFragment.newInstance(mParam1), true, R.id.frameSurveyDetail);
                break;
            case R.id.ivSendComment:
                String strComment = edCommentText.getText().toString().trim();
                if (strComment.equals("")) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Comment box is blank.");
                } else {
                    new SurveyDetailManager(this, mContext).callDoComment(mParam1, strComment);
                }
                break;
            case R.id.ivDetailMenu:
                Toast.makeText(mContext, "Under Development", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ivUserImageDetail:
                startActivity(new Intent(mContext, OtherUserProfileActivity.class)
                        .putExtra("OtherUserId", userId));
                break;
            case R.id.tvBadgeUserName:
                startActivity(new Intent(mContext, OtherUserProfileActivity.class)
                        .putExtra("OtherUserId", userId));
                break;
        }
    }

    @Override
    public void onSuccessViewSurveyDetail(SurveyDetailModel surveyDetailModel) {
        if (mSurveyData == null) {
            mSurveyData = surveyDetailModel.getData().getSurveyData();
        }

        if (surveyDetailModel.getStatus().equals("success")) {
            //surveyCommentListBeans.clear();
            surveyCommentListBeans.addAll(surveyDetailModel.getData().getSurveyCommentList());
            commentsAdapter.notifyDataSetChanged();
            if (mSurveyData != null) {
                Glide.with(mContext).load(mSurveyData.getUser_profile_photo()).error(R.drawable.logo).into(ivUserImageDetail);
                if (mSurveyData.getUser_name() != null) {
                    tvBadgeUserName.setText(mSurveyData.getUser_name());
                }
                switch (mSurveyData.getBadge_title()) {
                    case "Unsatisfied":
                        rlBadgeBackground.setBackgroundResource(R.drawable.survey_detail_green_bg);
                        ivDetailSurvey.setImageResource(R.drawable.unsatisfied_ico);
                        tvBadgeName.setText(mContext.getString(R.string.unsatisfied));
                        break;
                    case "Satisfied":
                        rlBadgeBackground.setBackgroundResource(R.drawable.survey_detail_bg);
                        ivDetailSurvey.setImageResource(R.drawable.satisfied_ico);
                        tvBadgeName.setText(mContext.getString(R.string.satisfied));
                        break;
                    case "Addictive":
                        rlBadgeBackground.setBackgroundResource(R.drawable.survey_detail_yellow_bg);
                        ivDetailSurvey.setImageResource(R.drawable.addicted_ico);
                        tvBadgeName.setText(mContext.getString(R.string.addictive));
                        break;
                }
                if (surveyCommentListBeans.size() > 0) {
                    rcvCommentList.setVisibility(View.VISIBLE);
                    tvNotYesComment.setVisibility(View.GONE);
                } else {
                    rcvCommentList.setVisibility(View.GONE);
                    tvNotYesComment.setVisibility(View.VISIBLE);
                }
            }
            assert mSurveyData != null;
            userId = String.valueOf(mSurveyData.getUser_id());
        }
    }

    @Override
    public void OnSuccessComment(DocommentResponse docommentResponse) {
        edCommentText.setText("");
        SurveyDetailModel.DataBean.SurveyCommentListBean commentDataBean = new SurveyDetailModel.DataBean.SurveyCommentListBean();
        commentDataBean.setSurveyCommentId(docommentResponse.getData().getComment_data().getSurvey_comment_id());
        commentDataBean.setComment(docommentResponse.getData().getComment_data().getComment());
        commentDataBean.setCommented_by_user_name(docommentResponse.getData().getComment_data().getCommented_by_user_name());
        commentDataBean.setCommented_on(docommentResponse.getData().getComment_data().getCommented_on());
        if (surveyCommentListBeans.size() > 0) {
            surveyCommentListBeans.add(0, commentDataBean);
        } else {
            surveyCommentListBeans.add(commentDataBean);
        }
        if (surveyCommentListBeans.size() > 0) {
            rcvCommentList.setVisibility(View.VISIBLE);
            tvNotYesComment.setVisibility(View.GONE);
        } else {
            rcvCommentList.setVisibility(View.GONE);
            tvNotYesComment.setVisibility(View.VISIBLE);
        }
        commentsAdapter.notifyDataSetChanged();
        rcvCommentList.smoothScrollToPosition(0);
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext,errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        ((SurveyDetailActivity) mContext).showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        ((SurveyDetailActivity) mContext).hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

}