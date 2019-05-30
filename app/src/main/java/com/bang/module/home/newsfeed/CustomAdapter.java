package com.bang.module.home.newsfeed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bang.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

     static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserNameNews,tc_addiction;
        TextView tvUserLikes;
        CircleImageView ivUserNewsFeed;

         MyViewHolder(View itemView) {
            super(itemView);
            this.tvUserNameNews =  itemView.findViewById(R.id.tvUserNameNews);
            this.tvUserLikes =  itemView.findViewById(R.id.tvUserLikes);
            this.ivUserNewsFeed =  itemView.findViewById(R.id.ivUserNewsFeed);
            this.tc_addiction = itemView.findViewById(R.id.tc_addiction);
        }
    }

     CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsfeed_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int listPosition) {

        TextView tvUserNameNews = holder.tvUserNameNews;
        TextView tvUserLikes = holder.tvUserLikes;
        CircleImageView ivUserNewsFeed = holder.ivUserNewsFeed;
        TextView tc_addiction = holder.tc_addiction;

        tc_addiction.setText("William ethan is Addictive with @"+dataSet.get(listPosition).getName());
        tvUserNameNews.setText(dataSet.get(listPosition).getName());
        tvUserLikes.setText(dataSet.get(listPosition).getVersion()+" Likes");
        ivUserNewsFeed.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}