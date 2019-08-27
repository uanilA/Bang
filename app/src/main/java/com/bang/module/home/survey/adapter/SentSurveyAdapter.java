package com.bang.module.home.survey.adapter;

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
import com.bang.module.home.survey.model.SurveySentResponse;

import java.util.List;

public class SentSurveyAdapter extends RecyclerView.Adapter<SentSurveyAdapter.ViewHolder> {

    private Context mContext;
    private List<SurveySentResponse.DataBean.SurveyListBean> surveySentListBeans;
    private ClickListener.SurveySingleClick clickListener;

    public SentSurveyAdapter(ClickListener.SurveySingleClick clickListener, Context mContext, List<SurveySentResponse.DataBean.SurveyListBean> surveySentListBeans) {
        this.mContext = mContext;
        this.surveySentListBeans = surveySentListBeans;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SentSurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.survey_view, viewGroup, false);
        return new SentSurveyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        System.out.println("************************"+surveySentListBeans.get(i).getBadge_title());

        if (surveySentListBeans.get(i).getBadge_title().equals("Unsatisfied")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.unsatisfied_ico);
            viewHolder.ivSurveyBg.setImageResource(R.drawable.unsatisfied_bg);
            viewHolder.tvTitleOfHeader.setText("UNSATISFIED");
            viewHolder.tvSurveyUserName.setText(surveySentListBeans.get(i).getFor_user_name());
        } else if (surveySentListBeans.get(i).getBadge_title().equals("Satisfied")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.satisfied_ico);
            viewHolder.tvTitleOfHeader.setText("SATISFIED");
            viewHolder.tvSurveyUserName.setText(surveySentListBeans.get(i).getFor_user_name());
            viewHolder.ivSurveyBg.setImageResource(R.drawable.satisfied_bg);
        } else if (surveySentListBeans.get(i).getBadge_title().equals("Addictive")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.addicted_ico);
            viewHolder.tvTitleOfHeader.setText("ADDICTIVE");
            viewHolder.ivSurveyBg.setImageResource(R.drawable.addictive_bg);
            viewHolder.tvSurveyUserName.setText(surveySentListBeans.get(i).getFor_user_name());
        }

        viewHolder.rlSurveyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onSingleClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  surveySentListBeans == null ? 0 : surveySentListBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivSurveyImg;
        private TextView tvTitleOfHeader;
        private TextView tvSurveyUserName;
        private ImageView ivSurveyBg;
        private RelativeLayout rlSurveyView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSurveyImg = itemView.findViewById(R.id.ivSurveyImg);
            tvTitleOfHeader = itemView.findViewById(R.id.tvTitleOfHeader);
            tvSurveyUserName = itemView.findViewById(R.id.tvSurveyUserName);
            ivSurveyBg = itemView.findViewById(R.id.ivSurveyBg);
            rlSurveyView = itemView.findViewById(R.id.rlSurveyView);
        }
    }

}
