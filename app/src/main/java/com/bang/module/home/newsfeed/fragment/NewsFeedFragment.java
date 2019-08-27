package com.bang.module.home.newsfeed.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.MainActivity;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.bang.module.home.newsfeed.adapter.NewsFeedAdapter;
import com.bang.module.home.newsfeed.model.NewsFeedResponse;
import com.bang.module.home.newsfeed.presenter.NewsFeedPresenter;
import com.bang.module.home.profile.followersfollowing.model.LikeResponse;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.network.ApiCallback;
import com.bang.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NewsFeedFragment extends BaseFragment implements ApiCallback.NewsFeedCallback, View.OnClickListener {

    private BottomSheetDialog dialog;
    private BottomSheetDialog reportUserDialog;
    private long mLastClickTime = 0;
    public List<NewsFeedResponse.DataBean.NewsfeedListBean> newsfeedListBeans;
    private RecyclerView rcvNewsFeedList;
    private NewsFeedAdapter adapter;
    private SwipeRefreshLayout simpleSwipeRefreshLayout;
    private int offset = 0;
    private int index;
    private View no_survey_avail;
    private String reportToUserId = "";
    private String newsFeedId = "";

    public NewsFeedFragment() {
    }

    public static NewsFeedFragment newInstance() {
        NewsFeedFragment fragment = new NewsFeedFragment();
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
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        init(view);
        adapter = new NewsFeedAdapter(mContext, newsfeedListBeans, new ClickListener.NewsFeedClick() {
            @Override
            public void onProfileClick(int position) {
                if (newsfeedListBeans.get(position).getIs_anonymous() != 1)
                    startActivity(new Intent(mContext, OtherUserProfileActivity.class).putExtra("OtherUserId", String.valueOf(newsfeedListBeans.get(position).getPosted_by_user_id())));
            }

            @Override
            public void onReportTUserClick(int position) {
                reportToUserId = String.valueOf(newsfeedListBeans.get(position).getPosted_by_user_id());
                newsFeedId = String.valueOf(newsfeedListBeans.get(position).getNewsfeed_id());
                ReportUserDilaog();
            }

            @Override
            public void onVideoPlayClick(int position) {
                if (!newsfeedListBeans.get(position).getVideo().equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsfeedListBeans.get(position).getVideo()));
                    intent.setDataAndType(Uri.parse(newsfeedListBeans.get(position).getVideo()), "video/mp4");
                    startActivity(intent);
                } else {
                    CustomToast.getInstance(mContext).showToast(mContext, "Video url is not valid");
                }
            }

            @Override
            public void onSingleLikeClick(int position) {
                index = position;
                likeApiCalling(position);
            }

            @Override
            public void onUserNameClick(int position) {
                startActivity(new Intent(mContext, OtherUserProfileActivity.class)
                        .putExtra("OtherUserId", String.valueOf(newsfeedListBeans.get(position).getSurveyed_user_id())));
            }
        });
        rcvNewsFeedList.setAdapter(adapter);
        return view;
    }

    private void likeApiCalling(int i) {
        new NewsFeedPresenter(this, mContext).newsLikeApiCalling(newsfeedListBeans.get(i).getNewsfeed_id());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsfeedListBeans.clear();
        apiCalling(offset);
    }

    public void apiCalling(int myOffset) {
        adapter.notifyDataSetChanged();
        new NewsFeedPresenter(this, mContext).newsFeedApiCalling(myOffset);
    }

    private void init(View view) {
        newsfeedListBeans = new ArrayList<>();
        newsfeedListBeans.clear();
        ((MainActivity) mContext).findViewById(R.id.iv_addSurvey).setOnClickListener(this);
        no_survey_avail = view.findViewById(R.id.no_survey_avail);
        ImageView ivNoRecordFound = view.findViewById(R.id.ivNoRecordFound);
        TextView tvTitleNotYet = view.findViewById(R.id.tvTitleNotYet);
        TextView tvDetailOfNewsFeed = view.findViewById(R.id.tvDetailOfNewsFeed);
        tvDetailOfNewsFeed.setVisibility(View.GONE);

        ivNoRecordFound.setImageResource(R.drawable.ic_unlike_icon);
        tvTitleNotYet.setText(getString(R.string.news_feed_not_found));
        rcvNewsFeedList = view.findViewById(R.id.rcvNewsFeedList);
        rcvNewsFeedList.setHasFixedSize(true);
        simpleSwipeRefreshLayout = view.findViewById(R.id.simpleSwipeRefreshLayout);
        simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorBang);
        /*On Refresh listener*/
        simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                newsfeedListBeans.clear();
                simpleSwipeRefreshLayout.setRefreshing(false);
                offset = 0;
                apiCalling(offset);
                adapter.notifyDataSetChanged();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvNewsFeedList.setLayoutManager(layoutManager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page != 0) {
                    adapter.showLoading(true);
                    offset = offset + 20; //load 20 items in recyclerView
                    apiCalling(offset);
                }
            }
        };
        rcvNewsFeedList.addOnScrollListener(scrollListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void BottomDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_add_post, null);
        dialog = new BottomSheetDialog(mContext, R.style.CustomBottomSheetDialogTheme);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.findViewById(R.id.cardCancel).setOnClickListener(this);
        dialog.findViewById(R.id.llAddNewPost).setOnClickListener(this);
        dialog.findViewById(R.id.llAddBangSurvey).setOnClickListener(this);
        dialog.show();
    }

    private void ReportUserDilaog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.report_user_dialog_view, null);
        reportUserDialog = new BottomSheetDialog(mContext, R.style.CustomBottomSheetDialogTheme);
        reportUserDialog.setContentView(view);
        reportUserDialog.setCancelable(false);
        reportUserDialog.findViewById(R.id.cardReportUserCancel).setOnClickListener(this);
        reportUserDialog.findViewById(R.id.llReportUser).setOnClickListener(this);
        reportUserDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.iv_addSurvey:
                BottomDialog();
                break;
            case R.id.cardCancel:
                dialog.dismiss();
                break;
            case R.id.llAddNewPost:
                dialog.dismiss();
                startActivity(new Intent(mContext, CreatePostActivity.class)
                        .putExtra("post_for_screen", "CreatePost"));
                break;
            case R.id.cardReportUserCancel:
                reportUserDialog.dismiss();
                break;
            case R.id.llReportUser:
                reportUserDialog.dismiss();
                startActivity(new Intent(mContext, CreatePostActivity.class)
                        .putExtra("post_for_screen", "ReportUser")
                        .putExtra("report_To_userId", reportToUserId)
                        .putExtra("report_news_feedId", newsFeedId));
                break;
            case R.id.llAddBangSurvey:
                dialog.dismiss();
                startActivity(new Intent(mContext, AddSurveyActivity.class));
                break;
        }
    }

    @Override
    public void onSuccessNewsFeed(NewsFeedResponse newsFeedResponse) {
        adapter.showLoading(false);
        newsfeedListBeans.addAll(newsFeedResponse.getData().getNewsfeed_list());
        if (newsfeedListBeans.size() > 0) {
            no_survey_avail.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else {
            //  newsfeedListBeans.clear();
            adapter.notifyDataSetChanged();
            no_survey_avail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccessLike(LikeResponse likeResponse) {
        newsfeedListBeans.get(index).setTotal_like_count(likeResponse.getTotal_like());
        newsfeedListBeans.get(index).setIs_like(likeResponse.getIs_like());
        adapter.notifyDataSetChanged();
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
        adapter.showLoading(true);
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }
}
