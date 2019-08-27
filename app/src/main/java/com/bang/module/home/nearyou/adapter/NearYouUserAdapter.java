package com.bang.module.home.nearyou.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.nearyou.model.NearbyUsersModel;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by anil upadhyay on 01.08.2019.
 */

public class NearYouUserAdapter extends RecyclerView.Adapter<NearYouUserAdapter.ViewHolder> {

    private List<NearbyUsersModel.DataBean.NearbyUserListBean> nearbyUserListBeans;
    private ClickListener.NearYouUserClick nearYouUserClick;
    private Context mContext;


    public NearYouUserAdapter(Context mContext,List<NearbyUsersModel.DataBean.NearbyUserListBean> nearbyUserListBeans,
                              ClickListener.NearYouUserClick nearYouUserClick) {
        this.nearbyUserListBeans = nearbyUserListBeans;
        this.nearYouUserClick = nearYouUserClick;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_near_you, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (nearbyUserListBeans.get(position).getIs_follow() == 0){
            holder.tvFollow.setText(mContext.getString(R.string.follow));
        }else {
            holder.tvFollow.setText(mContext.getString(R.string.unfollow));
        }
        Glide.with(holder.itemView.getContext()).load(nearbyUserListBeans.get(position).getProfile_photo()).into(holder.image);
        String val= String.valueOf(Html.fromHtml(String.valueOf(nearbyUserListBeans.get(position).getDistance())));
        String val1=val.substring(0,val.indexOf("."));
        if (val1.equals("0")){
            holder.tvMiles.setText(val1+" Mile");
        }else if (val1.equals("1")){
            holder.tvMiles.setText(val1+" Mile");
        }else {
            holder.tvMiles.setText(val1+" Miles");
        }
        holder.tvNumbersOfFollowers.setText(String.valueOf(nearbyUserListBeans.get(position).getTotal_followers()));
        holder.tvNearYouUserName.setText(nearbyUserListBeans.get(position).getFull_name());
        if (String.valueOf(nearbyUserListBeans.get(position).getTotal_followers()).equals("0")){
            holder.tvFollowers.setText(mContext.getString(R.string.follower));
        }else if (String.valueOf(nearbyUserListBeans.get(position).getTotal_followers()).equals("1")){
            holder.tvFollowers.setText(mContext.getString(R.string.follower));
        }else {
            holder.tvFollowers.setText(mContext.getString(R.string.followers));
        }

        String requestStatus = String.valueOf(nearbyUserListBeans.get(position).getRequest_status());
        switch (requestStatus){
            case "0":
                holder.tvBangRequest.setText(mContext.getString(R.string.pending));
                holder.llBang.setBackgroundResource(R.drawable.yellow_pending_ico);
                break;
            case "1":
                holder.tvBangRequest.setText(mContext.getString(R.string.messages));
                holder.llBang.setBackgroundResource(R.drawable.nearyou_bang_request);
                break;
            case "2":
                holder.tvBangRequest.setText(mContext.getString(R.string.bang));
                holder.llBang.setBackgroundResource(R.drawable.nearyou_bang_request);
                break;
            case "3":
                holder.tvBangRequest.setText(mContext.getString(R.string.bang));
                holder.llBang.setBackgroundResource(R.drawable.nearyou_bang_request);
                break;
            case "null":
                holder.tvBangRequest.setText(mContext.getString(R.string.bang));
                holder.llBang.setBackgroundResource(R.drawable.nearyou_bang_request);
                break;
        }

        holder.tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearYouUserClick.NearYouFollowClick(position);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearYouUserClick.NearYouProfileClick(position);
            }
        });

        holder.llFollowPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearYouUserClick.NearFollowingListClick(position);
            }
        });
        holder.llBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearYouUserClick.NearYouBangRequest(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearbyUserListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        private ImageView image;
        private TextView tvMiles;
        private TextView tvNearYouUserName,tvNumbersOfFollowers;
        private TextView tvFollow;
        private LinearLayout llFollowPosition;
        private ImageView llBang;
        private TextView tvBangRequest;
        private TextView tvFollowers;

         ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvMiles = itemView.findViewById(R.id.tvMiles);
            tvNearYouUserName = itemView.findViewById(R.id.tvNearYouUserName);
            tvNumbersOfFollowers = itemView.findViewById(R.id.tvNumbersOfFollowers);
            tvFollow =  itemView.findViewById(R.id.tvFollow);
            llFollowPosition = itemView.findViewById(R.id.llFollowPosition);
            llBang = itemView.findViewById(R.id.llBang);
            tvBangRequest = itemView.findViewById(R.id.tvBangRequest);
             tvFollowers = itemView.findViewById(R.id.tvFollowers);
        }

    }
}
