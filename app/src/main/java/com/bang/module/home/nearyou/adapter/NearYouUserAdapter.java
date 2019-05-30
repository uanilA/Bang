package com.bang.module.home.nearyou.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.module.home.nearyou.model.Item;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class NearYouUserAdapter extends RecyclerView.Adapter<NearYouUserAdapter.ViewHolder> {

    private List<Item> data;

    public NearYouUserAdapter(List<Item> data) {
        this.data = data;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getImage())
                .into(holder.image);
        holder.tvMiles.setText(data.get(position).getDistance()+" Miles");
        holder.tvNearYouUserName.setText(data.get(position).getName());
        holder.tvNumbersOfFollowers.setText(data.get(position).getFollowers() );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView tvMiles;
        private TextView tvNearYouUserName,tvNumbersOfFollowers;

         ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvMiles = itemView.findViewById(R.id.tvMiles);
            tvNearYouUserName = itemView.findViewById(R.id.tvNearYouUserName);
            tvNumbersOfFollowers = itemView.findViewById(R.id.tvNumbersOfFollowers);
        }
    }
}
