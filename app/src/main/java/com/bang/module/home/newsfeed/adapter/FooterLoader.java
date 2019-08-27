package com.bang.module.home.newsfeed.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bang.R;

public  class FooterLoader extends RecyclerView.ViewHolder {

    public ProgressBar mProgressBar;

    public FooterLoader(@NonNull View itemView) {
        super(itemView);
        mProgressBar = itemView.findViewById(R.id.progressbar);
    }
}