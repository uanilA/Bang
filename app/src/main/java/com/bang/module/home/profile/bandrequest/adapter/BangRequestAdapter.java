package com.bang.module.home.profile.bandrequest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.profile.bandrequest.model.BangRequestsModel;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BangRequestAdapter extends RecyclerView.Adapter<BangRequestAdapter.View_Holder> {

    private List<BangRequestsModel.DataBean.BangRequestListBean> bangRequestListBeans;
    private Context context;
    private ClickListener.RequestStatusClick requestStatusClick;

    public BangRequestAdapter(List<BangRequestsModel.DataBean.BangRequestListBean> bangRequestListBeans, Context context
            , ClickListener.RequestStatusClick requestStatusClick) {
        this.bangRequestListBeans = bangRequestListBeans;
        this.context = context;
        this.requestStatusClick = requestStatusClick;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bang_request_view, parent, false);
        return new View_Holder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        holder.tvBangRequestUserName.setText(bangRequestListBeans.get(position).getFull_name());
        holder.tvFollowersList.setText(context.getString(R.string.followers) + " " + bangRequestListBeans.get(position).getTotal_followers());
        Glide.with(context).load(bangRequestListBeans.get(position).getProfile_photo()).error(R.drawable.user_img).into(holder.ivBangRequest);
    }

    @Override
    public int getItemCount() {
        return bangRequestListBeans.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView ivBangRequest;
        TextView tvBangRequestUserName;
        TextView tvFollowersList;
        TextView tvAcceptBang;
        TextView tvRejectBang;

        View_Holder(View itemView) {
            super(itemView);
            ivBangRequest = itemView.findViewById(R.id.ivBangRequest);
            tvBangRequestUserName = itemView.findViewById(R.id.tvBangRequestUserName);
            tvFollowersList = itemView.findViewById(R.id.tvFollowersList);
            tvAcceptBang = itemView.findViewById(R.id.tvAcceptBang);
            tvRejectBang = itemView.findViewById(R.id.tvRejectBang);
            tvAcceptBang.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvAcceptBang:
                    requestStatusClick.StatusUpdateClick("1",getAdapterPosition());
                    break;
                case R.id.tvRejectBang:
                    requestStatusClick.StatusUpdateClick("2",getAdapterPosition());
                    break;
            }
        }
    }

}
