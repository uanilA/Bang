package com.bang.module.home.profile.mypost.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.StringTokenizer;

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

        if (myFeedListBeans.get(i).getIs_survey_post() == 1) {
            myViewHolder.tvLastUserName.setVisibility(View.VISIBLE);
            try {
                String s = myFeedListBeans.get(i).getTitle();
                StringTokenizer st = new StringTokenizer(s, "[]");
                String community = st.nextToken();
                String helpDesk = st.nextToken();
                String cutTrailingWhiteSpaceCharacters = helpDesk.replaceFirst("\\s++$", "");
                System.out.println("!!!!!!!!!!!!!!!" + community + "\n" + cutTrailingWhiteSpaceCharacters);
                myViewHolder.tc_addiction.setText(myFeedListBeans.get(i).getPosted_by_user_full_name() +cutTrailingWhiteSpaceCharacters);
                myViewHolder.tvLastUserName.setText(myFeedListBeans.get(i).getSurveyed_user_full_name());
            }catch (Exception e){e.printStackTrace();}
            myViewHolder.imageView.setImageResource(R.drawable.news_feed_pos);
            myViewHolder.imageView.setImageResource(R.drawable.news_feed_pos);
            myViewHolder.ivBadgeIcon.setVisibility(View.VISIBLE);
            if (myFeedListBeans.get(i).getPhoto().contains("unsatisfied") || myFeedListBeans.get(i).getPhoto().contains("Unsatisfied")){
                myViewHolder.ivTopGradient.setVisibility(View.GONE);
                myViewHolder.rlBottomGradient.setVisibility(View.GONE);
                myViewHolder.ivBadgeIcon.setImageResource(R.drawable.unsatisfied_ico);
            }else if (myFeedListBeans.get(i).getPhoto().contains("addictive") || myFeedListBeans.get(i).getPhoto().contains("Addictive")){
                myViewHolder.rlBottomGradient.setVisibility(View.GONE);
                myViewHolder.ivTopGradient.setVisibility(View.GONE);
                myViewHolder.ivBadgeIcon.setImageResource(R.drawable.addicted_ico);
            }else{
                myViewHolder.ivTopGradient.setVisibility(View.GONE);
                myViewHolder.rlBottomGradient.setVisibility(View.GONE);
                myViewHolder.ivBadgeIcon.setImageResource(R.drawable.satisfied_ico);
            }
            if (myFeedListBeans.get(i).getVideo_thumb().equals("")) {
                myViewHolder.ivVideoPlay.setVisibility(View.GONE);
            }
        } else {
            myViewHolder.tvLastUserName.setVisibility(View.GONE);
            myViewHolder.ivBadgeIcon.setVisibility(View.GONE);
            myViewHolder.tc_addiction.setText(myFeedListBeans.get(i).getTitle());
            Glide.with(mContext).load(myFeedListBeans.get(i).getVideo_thumb()).error(R.drawable.news_feed_pos).into(myViewHolder.imageView);
            if (myFeedListBeans.get(i).getPhoto().equals("") && !myFeedListBeans.get(i).getVideo_thumb().equals("")) {
                myViewHolder.ivVideoPlay.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(myFeedListBeans.get(i).getVideo_thumb()).error(R.drawable.news_feed_pos).into(myViewHolder.imageView);
            } else {
                myViewHolder.ivVideoPlay.setVisibility(View.GONE);
                Glide.with(mContext).load(myFeedListBeans.get(i).getPhoto()).error(R.drawable.news_feed_pos).into(myViewHolder.imageView);
            }

            if (myFeedListBeans.get(i).getPhoto().equals("") && myFeedListBeans.get(i).getVideo_thumb().equals("")) {
                myViewHolder.ivVideoPlay.setVisibility(View.GONE);
            }
        }

        if (myFeedListBeans.get(i).getTotal_like_count() == 0) {
            myViewHolder.tvUserLikes.setText("0");
            myViewHolder.ivHeartImage.setImageResource(R.drawable.ic_unlike_icon);
        } else if (myFeedListBeans.get(i).getTotal_like_count() == 1) {
            myViewHolder.tvUserLikes.setText(String.valueOf(myFeedListBeans.get(i).getTotal_like_count()));
            myViewHolder.ivHeartImage.setImageResource(R.drawable.ic_like_icon);
        } else {
            myViewHolder.tvUserLikes.setText(String.valueOf(myFeedListBeans.get(i).getTotal_like_count()));
            myViewHolder.ivHeartImage.setImageResource(R.drawable.ic_like_icon);
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
        private ImageView ivTopGradient;
        private ImageView ivHeartImage;
        private ImageView ivBadgeIcon;
        private TextView tvLastUserName;
        private RelativeLayout rlBottomGradient;

        MyViewHolder(View itemView) {
            super(itemView);
            tvUserLikes = itemView.findViewById(R.id.tvUserLikes);
            tc_addiction = itemView.findViewById(R.id.tc_addiction);
            imageView = itemView.findViewById(R.id.imageView);
            tvCreatedOnDate = itemView.findViewById(R.id.tvCreatedOnDate);
            ivTopGradient = itemView.findViewById(R.id.ivTopGradient);
            ivHeartImage = itemView.findViewById(R.id.ivHeartImage);
            ivVideoPlay = itemView.findViewById(R.id.ivVideoPlay);
            ivBadgeIcon = itemView.findViewById(R.id.ivBadgeIcon);
            tvLastUserName = itemView.findViewById(R.id.tvLastUserName);
            rlBottomGradient = itemView.findViewById(R.id.rlBottomGradient);
            itemView.findViewById(R.id.rlLikes).setOnClickListener(this);
            tvLastUserName.setOnClickListener(this);
            ivVideoPlay.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rlLikes:
                    clickListener.onSingleLikeClick(getAdapterPosition());
                    break;
                case R.id.ivVideoPlay:
                    clickListener.onVideoPlayClick(getAdapterPosition());
                    break;
                case R.id.tvLastUserName:
                    clickListener.onReportTUserClick(getAdapterPosition());
                    break;
            }
        }
    }
}