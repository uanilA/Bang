package com.bang.module.home.profile.followersfollowing.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.profile.followersfollowing.model.FollowersResponse;
import com.bang.module.home.profile.followersfollowing.model.FollowingResponse;
import com.bang.module.home.survey.model.ContactModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingRecyclerViewAdapter extends RecyclerView.Adapter<FollowingRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<FollowingResponse.DataBean.FollowingListBean> followingListBeans;
    private List<FollowingResponse.DataBean.FollowingListBean> globleFollowingListBeans;
    private ArrayList<FollowersResponse.DataBean.FollowerListBean> followerListBeans;
    private List<FollowersResponse.DataBean.FollowerListBean> globlefollowerListBeans;
    private ClickListener.FollowersClick followersClick;
    private String type;

    // private ArrayList<FollowersResponse.DataBean.FollowerListBean> globleFollowerListBeans;


    public void filter(String charText) {
        charText = charText.toLowerCase();
        if (type.equals("followers")) {
            followerListBeans.clear();
            if (charText.length() == 0) {
                followerListBeans.addAll(globlefollowerListBeans);
            } else {
                for (int i = 0; i < globlefollowerListBeans.size(); i++) {
                    if (globlefollowerListBeans.get(i).getFull_name().toLowerCase().startsWith(charText)) {
                        followerListBeans.add(globlefollowerListBeans.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        } else {
            followingListBeans.clear();
            if (charText.length() == 0) {
                followingListBeans.addAll(globleFollowingListBeans);
            } else {
                for (int i = 0; i < globleFollowingListBeans.size(); i++) {
                    if (globleFollowingListBeans.get(i).getFull_name().toLowerCase().startsWith(charText)) {
                        followingListBeans.add(globleFollowingListBeans.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }

    }


    public FollowingRecyclerViewAdapter(Context mContext, ArrayList<FollowingResponse.DataBean.FollowingListBean> followingListBeans, ClickListener.FollowersClick followersClick, String type) {
        this.mContext = mContext;
        this.followingListBeans = followingListBeans;
        this.followersClick = followersClick;
        this.globleFollowingListBeans = new ArrayList<>();
        this.type = type;
        if (followingListBeans != null) {
            globleFollowingListBeans.addAll(followingListBeans);
        }
    }


    public FollowingRecyclerViewAdapter(String type, Context mContext, ArrayList<FollowersResponse.DataBean.FollowerListBean> followerListBeans, ClickListener.FollowersClick followersClick) {
        this.mContext = mContext;
        this.followerListBeans = followerListBeans;
        this.followersClick = followersClick;
        this.globlefollowerListBeans = new ArrayList<>();
        this.type = type;
        if (followerListBeans != null) {
            globlefollowerListBeans.addAll(followerListBeans);
        }

    }


    @NonNull
    @Override
    public FollowingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.followers_view, viewGroup, false);
        return new FollowingRecyclerViewAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FollowingRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        String followerCount;
        if (type.equals("followers")) {
            followerCount = String.valueOf(followerListBeans.get(i).getTotal_followers());
            Glide.with(mContext).load(followerListBeans.get(i).getProfile_photo()).error(R.drawable.logo).into(viewHolder.ivFollowerOrFollowing);
            viewHolder.tvFollowerName.setText(followerListBeans.get(i).getFull_name());
            if (followerCount.equals("0")) {
                viewHolder.tvCountFollowers.setText(mContext.getString(R.string.no_followers));
            } else {
                viewHolder.tvCountFollowers.setText(followerCount + " " + mContext.getString(R.string.followers));
            }
        } else {
            followerCount = String.valueOf(followingListBeans.get(i).getTotal_followers());
            Glide.with(mContext).load(followingListBeans.get(i).getProfile_photo()).error(R.drawable.logo).into(viewHolder.ivFollowerOrFollowing);
            viewHolder.tvFollowerName.setText(followingListBeans.get(i).getFull_name());
            if (followerCount.equals("0")) {
                viewHolder.tvCountFollowers.setText(mContext.getString(R.string.no_followers));
            } else {
                viewHolder.tvCountFollowers.setText(followerCount + " " + mContext.getString(R.string.followers));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (type.equals("followers")) {
            return followerListBeans.size();
        } else {
            return followingListBeans.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView ivFollowerOrFollowing;
        private TextView tvFollowerName;
        private TextView tvCountFollowers;
        private RelativeLayout rlParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFollowerOrFollowing = itemView.findViewById(R.id.ivFollowerOrFollowing);
            tvFollowerName = itemView.findViewById(R.id.tvFollowerName);
            tvCountFollowers = itemView.findViewById(R.id.tvCountFollowers);
            rlParentLayout = itemView.findViewById(R.id.rlParentLayout);
            rlParentLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.rlParentLayout){
                followersClick.onFollowSingleClick(getAdapterPosition());
            }
        }
    }
}
