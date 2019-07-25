package com.bang.module.home.survey.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.module.home.survey.model.SurveyDetailModel;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<SurveyDetailModel.DataBean.SurveyCommentListBean> commentListBeanList;
    private Context mContext;

    public CommentsAdapter(Context mContext, List<SurveyDetailModel.DataBean.SurveyCommentListBean> commentListBeanList) {
        this.mContext = mContext;
        this.commentListBeanList = commentListBeanList;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder viewHolder, int i) {

        String input = commentListBeanList.get(i).getCommented_on();
        int index = input.lastIndexOf("T");
        if (index > 0)
            input = input.substring(0, index);
        viewHolder.tvUserCommentName.setText(commentListBeanList.get(i).getCommented_by_user_name());
        viewHolder.tvComment.setText(commentListBeanList.get(i).getComment());
        String[] sdate = input.split("-");
        String day = sdate[2];
        String month = sdate[1];
        String year = sdate[0];
        viewHolder.tvDateOfComment.setText(day + "/" + month + "/" + year);

    }

    @Override
    public int getItemCount() {
        return commentListBeanList == null ? 0 : commentListBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserCommentName;
        TextView tvComment;
        TextView tvDateOfComment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserCommentName = itemView.findViewById(R.id.tvUserCommentName);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvDateOfComment = itemView.findViewById(R.id.tvDateOfComment);
        }
    }
}