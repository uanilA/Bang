package com.bang.module.home.survey.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.survey.model.ViewSurveyModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ViewSurveyAdapter extends RecyclerView.Adapter<ViewSurveyAdapter.ViewHolder> {

    private Context mContext;
    private List<ViewSurveyModel.DataBean.SurveyAnswersBean> surveyAnswersBeans;
    private ClickListener.SurveySingleClick clickListener;

    public ViewSurveyAdapter(ClickListener.SurveySingleClick clickListener, Context mContext, List<ViewSurveyModel.DataBean.SurveyAnswersBean> surveyAnswersBeans) {
        this.mContext = mContext;
        this.surveyAnswersBeans = surveyAnswersBeans;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ViewSurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.previous_survey_layout, viewGroup, false);
        return new ViewSurveyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSurveyAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        if (i == 0){
            viewHolder.ivViewSurvey.setImageResource(Integer.valueOf(surveyAnswersBeans.get(i).getOption_photo()));
            viewHolder.tvSurveyDescription.setText(surveyAnswersBeans.get(i).getQuestion_description());
            viewHolder.tvTitleSurvey.setText(surveyAnswersBeans.get(i).getQuestion_title());
        }else {
            Glide.with(mContext).load(surveyAnswersBeans.get(i).getOption_photo()).error(R.drawable.logo).into(viewHolder.ivViewSurvey);
        }
        viewHolder.tvSurveyDescription.setText(surveyAnswersBeans.get(i).getQuestion_description());
        viewHolder.tvTitleSurvey.setText(surveyAnswersBeans.get(i).getQuestion_title());
      /* viewHolder.rlSurveyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onSingleClick(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return surveyAnswersBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivViewSurvey;
        private TextView tvTitleSurvey;
        private TextView tvSurveyDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewSurvey = itemView.findViewById(R.id.ivViewSurvey);
            tvTitleSurvey = itemView.findViewById(R.id.tvTitleSurvey);
            tvSurveyDescription = itemView.findViewById(R.id.tvSurveyDescription);
        }
    }

}
