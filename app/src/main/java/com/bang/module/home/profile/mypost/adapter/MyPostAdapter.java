package com.bang.module.home.profile.mypost.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.newsfeed.adapter.FooterLoader;
import com.bang.module.home.profile.mypost.model.MyPostResponse;
import com.bumptech.glide.Glide;
import java.util.List;

import static com.bang.utils.AppUtils.fromDate;

/**
 * Created by anil
 * Date: 23/07/19
 * Time: 03:07 PM
 */

public class MyPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyPostResponse.DataBean.MyfeedListBean> myFeedListBeans;
    private Context mContext;
    private ClickListener.MyPostClick clickListener;
    private final int VIEWTYPE_ITEM = 1;
    private final int VIEWTYPE_LOADER = 2;
    private boolean showLoader;

    public MyPostAdapter(List<MyPostResponse.DataBean.MyfeedListBean> myFeedListBeans, Context mContext, ClickListener.MyPostClick clickListener) {
        this.myFeedListBeans = myFeedListBeans;
        this.mContext = mContext;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case VIEWTYPE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mypost_layout, viewGroup, false);
                return new MyPostAdapter.MyViewHolder(view);

            case VIEWTYPE_LOADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
                return new FooterLoader(view);
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
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

        MyPostAdapter.MyViewHolder myViewHolder = ((MyPostAdapter.MyViewHolder) viewHolder);
        myViewHolder.tc_addiction.setText(myFeedListBeans.get(i).getTitle());
        if (myFeedListBeans.get(i).getTotal_like_count() == 0){
            myViewHolder.tvUserLikes.setText("0 Likes");
            myViewHolder.ivHeartImage.setImageResource(R.drawable.ic_unlike_icon);
        }else {
            myViewHolder.tvUserLikes.setText(myFeedListBeans.get(i).getTotal_like_count()+" Likes");
            myViewHolder.ivHeartImage.setImageResource(R.drawable.ic_like_icon);
        }


        if (myFeedListBeans.get(i).getPhoto().equals("") &&
                myFeedListBeans.get(i).getVideo_thumb().equals("")){
            myViewHolder.ivGradient.setVisibility(View.GONE);
        }else {
            myViewHolder.ivGradient.setVisibility(View.GONE);
        }

        if (myFeedListBeans.get(i).getPhoto().equals("") ) {
            myViewHolder.ivVideoPlay.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(myFeedListBeans.get(i).getVideo_thumb()).error(R.drawable.news_feed_pos).into(myViewHolder.imageView);
        } else {
            myViewHolder.ivVideoPlay.setVisibility(View.GONE);
            Glide.with(mContext).load(myFeedListBeans.get(i).getPhoto()).error(R.drawable.news_feed_pos).into(myViewHolder.imageView);
        }

        if (myFeedListBeans.get(i).getPhoto().equals("") && myFeedListBeans.get(i).getVideo_thumb().equals("")
                && myFeedListBeans.get(i).getVideo_thumb().equals("")){
            myViewHolder.ivVideoPlay.setVisibility(View.GONE);
        }


        String input = myFeedListBeans.get(i).getCreated_on();
        int index = input.lastIndexOf("T");
        if (index > 0)
            input = input.substring(0, index);
        myViewHolder.tvCreatedOnDate.setText(fromDate(input));

    }

    public void showLoading(boolean status) {
        showLoader = status;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == myFeedListBeans.size() - 1) {
            return showLoader ? VIEWTYPE_LOADER : VIEWTYPE_ITEM;
        }
        return VIEWTYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return myFeedListBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tc_addiction;
        private TextView tvUserLikes;
        private ImageView imageView;
        private ImageView ivVideoPlay;
        private TextView tvCreatedOnDate;
        private ImageView ivGradient;
        private ImageView ivHeartImage;

        MyViewHolder(View itemView) {
            super(itemView);
            tvUserLikes = itemView.findViewById(R.id.tvUserLikes);
            tc_addiction = itemView.findViewById(R.id.tc_addiction);
            imageView = itemView.findViewById(R.id.imageView);
            tvCreatedOnDate = itemView.findViewById(R.id.tvCreatedOnDate);
            ivGradient = itemView.findViewById(R.id.ivGradient);
            ivHeartImage = itemView.findViewById(R.id.ivHeartImage);
            ivVideoPlay = itemView.findViewById(R.id.ivVideoPlay);
            itemView.findViewById(R.id.rlLikes).setOnClickListener(this);
            itemView.findViewById(R.id.rlReportUser).setOnClickListener(this);
            ivVideoPlay.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rlLikes:
                    clickListener.onSingleLikeClick(getAdapterPosition());
                    break;
                case R.id.ivVideoPlay:
                    clickListener.onVideoPlayClick(getAdapterPosition());
                    break;
                case R.id.rlReportUser:
                    clickListener.onReportTUserClick(getAdapterPosition());
                    break;
            }
        }
    }

}