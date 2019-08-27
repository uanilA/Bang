package com.bang.module.home.survey.adapter;

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
import com.bang.module.home.survey.model.SurveyReceiveResponse;

import java.util.List;

public class ReceiveSurveyAdapter extends RecyclerView.Adapter<ReceiveSurveyAdapter.ViewHolder> {

    private Context mContext;
    private List<SurveyReceiveResponse.DataBean.SurveyListBean> surveyReceiveListBeans;
    private ClickListener.SurveySingleClick clickListener;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    public ReceiveSurveyAdapter(ClickListener.SurveySingleClick clickListener, Context mContext, List<SurveyReceiveResponse.DataBean.SurveyListBean> surveyReceiveListBeans) {
        this.mContext = mContext;
        this.surveyReceiveListBeans = surveyReceiveListBeans;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.survey_view, viewGroup, false);
        return new ReceiveSurveyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^"+surveyReceiveListBeans.get(i).getBadge_title());

        if (surveyReceiveListBeans.get(i).getBadge_title().equals("Unsatisfied")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.unsatisfied_ico);
            viewHolder.ivSurveyBg.setImageResource(R.drawable.unsatisfied_bg);
            viewHolder.tvTitleOfHeader.setText("UNSATISFIED");
            viewHolder.tvSurveyUserName.setText(surveyReceiveListBeans.get(i).getBy_user_name());
        } else if (surveyReceiveListBeans.get(i).getBadge_title().equals("Satisfied")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.satisfied_ico);
            viewHolder.tvTitleOfHeader.setText("SATISFIED");
            viewHolder.tvSurveyUserName.setText(surveyReceiveListBeans.get(i).getBy_user_name());
            viewHolder.ivSurveyBg.setImageResource(R.drawable.satisfied_bg);
        } else if (surveyReceiveListBeans.get(i).getBadge_title().equals("Addictive")){
            viewHolder.ivSurveyImg.setImageResource(R.drawable.addicted_ico);
            viewHolder.tvTitleOfHeader.setText("ADDICTIVE");
            viewHolder.ivSurveyBg.setImageResource(R.drawable.addictive_bg);
            viewHolder.tvSurveyUserName.setText(surveyReceiveListBeans.get(i).getBy_user_name());
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
        return surveyReceiveListBeans == null ? 0 : surveyReceiveListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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
