package com.bang.module.home.profile.mypost.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bang.R;
import com.bang.module.home.newsfeed.adapter.FooterLoader;
import com.bang.module.home.newsfeed.model.LikeListResponse;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class LikesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LikeListResponse.DataBean.FeedLikeListBean> feedLikeListBeans;
    private Context mContext;
    private final int VIEWTYPE_ITEM = 1;
    private final int VIEWTYPE_LOADER = 2;
    private boolean showLoader;


    public LikesListAdapter(List<LikeListResponse.DataBean.FeedLikeListBean> feedLikeListBeans, Context mContext) {
        this.feedLikeListBeans = feedLikeListBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case VIEWTYPE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.like_view, viewGroup, false);
                return new LikesListAdapter.MyViewHolder(view);

            case VIEWTYPE_LOADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
                return new FooterLoader(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FooterLoader) {
            FooterLoader loaderViewHolder = (FooterLoader) viewHolder;
            if (showLoader) {
                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }
            return;
        }

        LikesListAdapter.MyViewHolder myViewHolder = ((LikesListAdapter.MyViewHolder) viewHolder);
        myViewHolder.tvContactName.setText(feedLikeListBeans.get(i).getFull_name());
        Glide.with(mContext).load(feedLikeListBeans.get(i).getProfile_photo()).error(R.drawable.logo).into(myViewHolder.ivContact);
    }

    public void showLoading(boolean status) {
        showLoader = status;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == feedLikeListBeans.size() - 1) {
            return showLoader ? VIEWTYPE_LOADER : VIEWTYPE_ITEM;
        }
        return VIEWTYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return feedLikeListBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivContact;
        private TextView tvContactName;

         MyViewHolder(View view) {
            super(view);
            ivContact= view.findViewById(R.id.ivContact);
            tvContactName = view.findViewById(R.id.tvContactName);
        }
    }
}
