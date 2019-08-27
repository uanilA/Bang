package com.bang.module.home.newsfeed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.newsfeed.model.NewsFeedResponse;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.StringTokenizer;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bang.utils.AppUtils.fromDate;

/**
 * Created by anil
 * Date: 18/07/19
 * Time: 4:03 PM
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsFeedResponse.DataBean.NewsfeedListBean> newsFeedListBeans;
    public  ClickListener.NewsFeedClick clickListener;
    private Context mContext;
    private final int VIEWTYPE_ITEM = 1;
    private final int VIEWTYPE_LOADER = 2;
    private boolean showLoader;



    public NewsFeedAdapter(Context mContext, List<NewsFeedResponse.DataBean.NewsfeedListBean> newsFeedListBeans, ClickListener.NewsFeedClick clickListener) {
        this.newsFeedListBeans = newsFeedListBeans;
        this.clickListener = clickListener;
        this.mContext = mContext;
    }

    public void showLoading(boolean status) {
        showLoader = status;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {

        if (holder instanceof FooterLoader) {
            FooterLoader loaderViewHolder = (FooterLoader) holder;
            if (showLoader) {
                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }
            return;
        }
        NewsFeedAdapter.MyViewHolder myviewHolder = ((NewsFeedAdapter.MyViewHolder) holder);

        if (newsFeedListBeans.get(listPosition).getIs_survey_post() == 1) {
            myviewHolder.ivBadgeIcon.setVisibility(View.VISIBLE);
            myviewHolder.imageView.setImageResource(R.drawable.news_feed_pos);
            myviewHolder.rlBottomGradient.setVisibility(View.GONE);
            myviewHolder.ivTopGradient.setVisibility(View.GONE);

            if (newsFeedListBeans.get(listPosition).getPhoto().contains("unsatisfied") || newsFeedListBeans.get(listPosition).getPhoto().contains("Unsatisfied")) {
                myviewHolder.ivBadgeIcon.setImageResource(R.drawable.unsatisfied_ico);
            } else if (newsFeedListBeans.get(listPosition).getPhoto().contains("addictive") || newsFeedListBeans.get(listPosition).getPhoto().contains("Addictive")) {
                myviewHolder.ivBadgeIcon.setImageResource(R.drawable.addicted_ico);
            } else {
                myviewHolder.ivBadgeIcon.setImageResource(R.drawable.satisfied_ico);
            }

            // Glide.with(mContext).load(newsFeedListBeans.get(listPosition).getPhoto()).error(R.drawable.news_feed_pos).into(myviewHolder.ivBadgeIcon);
            if (newsFeedListBeans.get(listPosition).getIs_anonymous() == 1) {
                String s = newsFeedListBeans.get(listPosition).getTitle();
                StringTokenizer st = new StringTokenizer(s, " ");
                String one = st.nextToken();
                String two = st.nextToken();
                String lastToken = st.nextToken();
                System.out.println("!!!!!!!!!!!!!!!" + lastToken);
                myviewHolder.tvLastUserName.setVisibility(View.GONE);
                myviewHolder.tc_addiction.setText("Someone special is " + lastToken + ".");
            } else {
                String s = newsFeedListBeans.get(listPosition).getTitle();
                StringTokenizer st = new StringTokenizer(s, "[]");
                String community = st.nextToken();
                String helpDesk = st.nextToken();
                System.out.println("!!!!!!!!!!!!!!!" + community + "\n" + helpDesk);
                myviewHolder.tvLastUserName.setVisibility(View.VISIBLE);
                myviewHolder.tc_addiction.setText(newsFeedListBeans.get(listPosition).getPosted_by_user_full_name() + helpDesk);
                myviewHolder.tvLastUserName.setText(newsFeedListBeans.get(listPosition).getSurveyed_user_full_name());
            }
        } else {
            myviewHolder.ivBadgeIcon.setVisibility(View.GONE);
            myviewHolder.tc_addiction.setText(newsFeedListBeans.get(listPosition).getTitle());
            myviewHolder.rlBottomGradient.setVisibility(View.VISIBLE);
            myviewHolder.ivTopGradient.setVisibility(View.VISIBLE);
            if (newsFeedListBeans.get(listPosition).getPhoto().equals("")) {
                myviewHolder.ivVideoPlay.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(newsFeedListBeans.get(listPosition).getVideo_thumb()).error(R.drawable.background_img).into(myviewHolder.imageView);
            } else {
                myviewHolder.ivVideoPlay.setVisibility(View.GONE);
                Glide.with(mContext).load(newsFeedListBeans.get(listPosition).getPhoto()).error(R.drawable.background_img).into(myviewHolder.imageView);
            }
        }

        myviewHolder.tvUserNameNews.setText(newsFeedListBeans.get(listPosition).getPosted_by_user_full_name());

        /*if (newsFeedListBeans.get(listPosition).getTotal_like_count() == 0) {
            myviewHolder.tvUserLikes.setText(newsFeedListBeans.get(listPosition).getTotal_like_count() + " Like");
        } else if (newsFeedListBeans.get(listPosition).getTotal_like_count() == 1) {
            myviewHolder.tvUserLikes.setText(newsFeedListBeans.get(listPosition).getTotal_like_count() + " Like");
        } else {
            myviewHolder.tvUserLikes.setText(newsFeedListBeans.get(listPosition).getTotal_like_count() + " Likes");
        }*/
        myviewHolder.tvUserLikes.setText(String.valueOf(newsFeedListBeans.get(listPosition).getTotal_like_count()));
        Glide.with(mContext).load(newsFeedListBeans.get(listPosition).getProfile_photo()).error(R.drawable.logo).into(myviewHolder.ivUserNewsFeed);

        if (newsFeedListBeans.get(listPosition).getIs_like() == 1) {
            myviewHolder.ivHeartImage.setImageResource(R.drawable.ic_like_icon);
        } else {
            myviewHolder.ivHeartImage.setImageResource(R.drawable.ic_unlike_icon);
        }

        /*
         * Dummy Check
         */
        if (newsFeedListBeans.get(listPosition).getPhoto().equals("") && newsFeedListBeans.get(listPosition).getVideo_thumb().equals("")
                && newsFeedListBeans.get(listPosition).getVideo_thumb().equals("")) {
            myviewHolder.ivVideoPlay.setVisibility(View.GONE);
        }

        String input = newsFeedListBeans.get(listPosition).getCreated_on();
        int index = input.lastIndexOf("T");
        if (index > 0)
            input = input.substring(0, index);
        myviewHolder.tvCreatedOnDate.setText(fromDate(input));
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEWTYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsfeed_layout, parent, false);
                return new MyViewHolder(view);

            case VIEWTYPE_LOADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
                return new FooterLoader(view);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == newsFeedListBeans.size() - 1) {
            return showLoader ? VIEWTYPE_LOADER : VIEWTYPE_ITEM;
        }
        return VIEWTYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return newsFeedListBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUserNameNews, tc_addiction;
        private TextView tvUserLikes;
        private CircleImageView ivUserNewsFeed;
        private RelativeLayout rlReportUser;
        private ImageView imageView;
        private ImageView ivVideoPlay;
        private TextView tvCreatedOnDate;
        private LinearLayout llUserProfile;
        private RelativeLayout rlLikes;
        private ImageView ivHeartImage;
        private ImageView ivBadgeIcon;
        private TextView tvLastUserName;
        private  ImageView ivTopGradient;
        private RelativeLayout rlBottomGradient;

        MyViewHolder(View itemView) {
            super(itemView);
            ivVideoPlay = itemView.findViewById(R.id.ivVideoPlay);
            tvUserNameNews = itemView.findViewById(R.id.tvUserNameNews);
            tvUserLikes = itemView.findViewById(R.id.tvUserLikes);
            ivUserNewsFeed = itemView.findViewById(R.id.ivUserNewsFeed);
            tc_addiction = itemView.findViewById(R.id.tc_addiction);
            rlReportUser = itemView.findViewById(R.id.rlReportUser);
            imageView = itemView.findViewById(R.id.imageView);
            tvCreatedOnDate = itemView.findViewById(R.id.tvCreatedOnDate);
            llUserProfile = itemView.findViewById(R.id.llUserProfile);
            rlLikes = itemView.findViewById(R.id.rlLikes);
            ivTopGradient = itemView.findViewById(R.id.ivTopGradient);
            ivHeartImage = itemView.findViewById(R.id.ivHeartImage);
            ivBadgeIcon = itemView.findViewById(R.id.ivBadgeIcon);
            tvLastUserName = itemView.findViewById(R.id.tvLastUserName);
            rlBottomGradient = itemView.findViewById(R.id.rlBottomGradient);
            rlLikes.setOnClickListener(this);
            llUserProfile.setOnClickListener(this);
            ivVideoPlay.setOnClickListener(this);
            rlReportUser.setOnClickListener(this);
            tvLastUserName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rlLikes:
                    clickListener.onSingleLikeClick(getAdapterPosition());
                    break;
                case R.id.llUserProfile:
                    clickListener.onProfileClick(getAdapterPosition());
                    break;
                case R.id.ivVideoPlay:
                    clickListener.onVideoPlayClick(getAdapterPosition());
                    break;
                case R.id.rlReportUser:
                    clickListener.onReportTUserClick(getAdapterPosition());
                    break;
                case R.id.tvLastUserName:
                    clickListener.onUserNameClick(getAdapterPosition());
                    break;
            }
        }
    }
}