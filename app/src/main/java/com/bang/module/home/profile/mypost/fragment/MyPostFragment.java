package com.bang.module.home.profile.mypost.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.model.LikeListResponse;
import com.bang.module.home.profile.mypost.adapter.LikesListAdapter;
import com.bang.module.home.profile.mypost.adapter.MyPostAdapter;
import com.bang.module.home.profile.mypost.model.MyPostResponse;
import com.bang.module.home.profile.mypost.presenter.MyPostPresenter;
import com.bang.network.ApiCallback;
import com.bang.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by anil
 * Date: 23/07/19
 * Time: 03:07 PM
 */
public class MyPostFragment extends BaseFragment implements ApiCallback.MyPostCallback, View.OnClickListener {

    private RecyclerView rcvMyPost;
    private MyPostAdapter myPostAdapter;
    private List<MyPostResponse.DataBean.MyfeedListBean> myfeedListBeans;
    private int offset = 0;
    private int likeOffset = 0;
    private BottomSheetDialog dialog;

    private LikesListAdapter likesListAdapter;
    private List<LikeListResponse.DataBean.FeedLikeListBean> feedLikeListBeans;
    private RecyclerView rcvLikesList;
    private TextView likeCount;
    private String newsfeedId;

    public MyPostFragment() {
        // Required empty public constructor
    }


    public static MyPostFragment newInstance() {
        MyPostFragment fragment = new MyPostFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);
        init(view);
        callMyPostApi(offset);
        myPostSetAdapter();
        return view;
    }

    private void likePostSetAdapter() {
        likesListAdapter = new LikesListAdapter(feedLikeListBeans, mContext);
        rcvLikesList.setAdapter(likesListAdapter);
    }

    private void myPostSetAdapter() {
        myPostAdapter = new MyPostAdapter(myfeedListBeans, mContext, new ClickListener.MyPostClick() {
            @Override
            public void onReportTUserClick(int position) {
                /* ReportUserDilaog();*/
            }

            @Override
            public void onVideoPlayClick(int position) {
                if (!myfeedListBeans.get(position).getVideo().equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myfeedListBeans.get(position).getVideo()));
                    intent.setDataAndType(Uri.parse(myfeedListBeans.get(position).getVideo()), "video/mp4");
                    startActivity(intent);
                } else {
                    CustomToast.getInstance(mContext).showToast(mContext, "Video url is not valid");
                }
            }
            @Override
            public void onSingleLikeClick(int position) {
                feedLikeListBeans.clear();
                newsfeedId = String.valueOf(myfeedListBeans.get(position).getNewsfeedId());
                BottomDialog();
            }
        });
        rcvMyPost.setAdapter(myPostAdapter);
    }

    private void callMyPostApi(int myOffset) {
        new MyPostPresenter(this, mContext).myPostCallApi(myOffset);
    }

    private void init(View view) {
        rcvMyPost = view.findViewById(R.id.rcvMyPost);
        myfeedListBeans   = new ArrayList<>();
        feedLikeListBeans = new ArrayList<>();
        rcvMyPost.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvMyPost.setLayoutManager(layoutManager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page != 0) {
                    myPostAdapter.showLoading(true);
                    offset = offset + 10; //load 10 items in recyclerview
                    callMyPostApi(offset);
                }
            }
        };
        rcvMyPost.addOnScrollListener(scrollListener);
    }

    @Override
    public void onSuccessMyPost(MyPostResponse myPostResponse) {
        myPostAdapter.showLoading(false);
        if (myPostResponse.getData().getMyfeed_list().size() > 0) {
            myfeedListBeans.addAll(myPostResponse.getData().getMyfeed_list());
            myPostAdapter.notifyDataSetChanged();
        } else {
           // myfeedListBeans.clear();
            myPostAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccessListList(LikeListResponse likeListResponse) {
        likesListAdapter.showLoading(false);
        if (likeListResponse.getData().getFeed_like_list().size() > 0) {
            feedLikeListBeans.addAll(likeListResponse.getData().getFeed_like_list());
            if (likeListResponse.getData().getFeed_like_count() == 1){
                likeCount.setText(likeListResponse.getData().getFeed_like_count() + " Like");
            }else {
                likeCount.setText(likeListResponse.getData().getFeed_like_count() + " Likes");
            }
            likesListAdapter.notifyDataSetChanged();
        } else {
            feedLikeListBeans.clear();
            likesListAdapter.notifyDataSetChanged();
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


    private void BottomDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.likes_list_layout, null);
        dialog = new BottomSheetDialog(mContext, R.style.CustomBottomSheetDialogTheme);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.findViewById(R.id.iv_CloseLikesDialog)).setOnClickListener(this);
        rcvLikesList = dialog.findViewById(R.id.rcvLikesList);
        likeCount = dialog.findViewById(R.id.likeCount);
        likePostSetAdapter();
        callLikeListApi(likeOffset);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvLikesList.setLayoutManager(layoutManager1);
        EndlessRecyclerViewScrollListener scrollListener1 = new EndlessRecyclerViewScrollListener(layoutManager1) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page != 0) {
                    likesListAdapter.showLoading(true);
                    likeOffset = likeOffset + 10; //load 10 items in recyclerView
                    callLikeListApi(likeOffset);
                }
            }
        };
        rcvLikesList.addOnScrollListener(scrollListener1);
        dialog.show();
    }

    private void callLikeListApi(int myOffset) {
        new MyPostPresenter(this, mContext).myPostLikeListApi(newsfeedId, myOffset); //newsfeedId
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_CloseLikesDialog) {
            dialog.dismiss();
        }
    }
}