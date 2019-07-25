package com.bang.module.home.profile.followersfollowing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BangParentActivity;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.followersfollowing.adapter.FollowingRecyclerViewAdapter;
import com.bang.module.home.profile.followersfollowing.model.FollowersResponse;
import com.bang.module.home.profile.followersfollowing.model.FollowingResponse;
import com.bang.module.home.profile.followersfollowing.presenter.FollowingFollowerPresenter;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends BangParentActivity implements ApiCallback.FollowersCallback, View.OnClickListener {

    private Session session;
    private String OtherUserId = "";
    private String follows_data = "";
    private ArrayList<FollowingResponse.DataBean.FollowingListBean> followingListBeans;
    private ArrayList<FollowersResponse.DataBean.FollowerListBean> followerListBeans;
    private RecyclerView followersListRecyclerView;
    private TextView tvFollowersTitle;
    private EditText etFilterField;
    private int offset = 0;
    private FollowingRecyclerViewAdapter followingRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        if (getIntent().getStringExtra("OtherUserId") != null) {
            OtherUserId = getIntent().getStringExtra("OtherUserId");
            follows_data = getIntent().getStringExtra("follows_data");
        }
        session = new Session(FollowersActivity.this);
        init();
        apiCalling(offset);
    }

    /* private void setAdapter() {
         FollowingRecyclerViewAdapter followingRecyclerViewAdapter = new FollowingRecyclerViewAdapter(FollowersActivity.this, followingListBeans,
                 new ClickListener.FollowersClick() {
                     @Override
                     public void onFollowSingleClick(int position) {

                     }
                 });
         followersListRecyclerView.setAdapter(followingRecyclerViewAdapter);
     }
 */
    private void apiCalling(int myOffset) {
        if (follows_data.equals("followers")) {
            tvFollowersTitle.setText(getString(R.string.followers));
            new FollowingFollowerPresenter(this, FollowersActivity.this)
                    .followerCallingApi(follows_data, OtherUserId, myOffset);
        } else {
            tvFollowersTitle.setText(getString(R.string.following));
            new FollowingFollowerPresenter(this, FollowersActivity.this)
                    .followingCallingApi(follows_data, OtherUserId, myOffset);

        }
    }

    private void init() {
        findViewById(R.id.ivFollowersBack).setOnClickListener(this);
        etFilterField = findViewById(R.id.etFilterField);
        followersListRecyclerView = findViewById(R.id.followersListRecyclerView);
        tvFollowersTitle = findViewById(R.id.tvFollowersTitle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        followersListRecyclerView.setLayoutManager(layoutManager);

        etFilterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (followingRecyclerViewAdapter != null) {
                    followingRecyclerViewAdapter.filter(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

      /*  EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //increase the offset for pagination
                if (page > 0){
                    offset = offset+10;
                    apiCalling(offset);
                }


            }
        };
        followersListRecyclerView.addOnScrollListener(scrollListener);*/
    }


    @Override
    public void onSuccessFollowing(FollowingResponse followingResponse) {
        if (followingResponse.getData().getFollowing_list() != null) {
            followingListBeans = new ArrayList<>(followingResponse.getData().getFollowing_list());
           // followingListBeans.addAll(followingResponse.getData().getFollowing_list());
            followingRecyclerViewAdapter = new FollowingRecyclerViewAdapter(FollowersActivity.this, followingListBeans,
                    new ClickListener.FollowersClick() {
                        @Override
                        public void onFollowSingleClick(int position) {
                         startActivity(new Intent(FollowersActivity.this, OtherUserProfileActivity.class)
                         .putExtra("OtherUserId",String.valueOf(followingListBeans.get(position).getUserId())));
                        }
                    }, follows_data);
            followersListRecyclerView.setAdapter(followingRecyclerViewAdapter);
        }

    }

    @Override
    public void onSuccessFollowers(FollowersResponse followersResponse) {
        if (followersResponse.getData().getFollower_list() != null) {
            followerListBeans = new ArrayList<>(followersResponse.getData().getFollower_list());
           // followerListBeans.addAll(followersResponse.getData().getFollower_list());
            followingRecyclerViewAdapter = new FollowingRecyclerViewAdapter(follows_data, FollowersActivity.this, followerListBeans,
                    new ClickListener.FollowersClick() {
                        @Override
                        public void onFollowSingleClick(int position) {
                            startActivity(new Intent(FollowersActivity.this, OtherUserProfileActivity.class)
                                    .putExtra("OtherUserId",String.valueOf(followerListBeans.get(position).getUserId())));
                        }
                    });
            followersListRecyclerView.setAdapter(followingRecyclerViewAdapter);
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        showDialog(FollowersActivity.this,errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(FollowersActivity.this).showToast(FollowersActivity.this, errorMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivFollowersBack) {
            finish();
        }
    }
}
