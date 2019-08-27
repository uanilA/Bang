package com.bang.module.home.addsurvey.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.GetClickListener;
import com.bang.base.ClickListener;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;

import java.util.List;

public class SurveyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SurveyQuestionResponse.DataBean.QuestionListBean> questionLists;
    private ClickListener.SurveyClickListener clickListener;
    private String SurveyId = "";
    int count = 0;
    private StringBuilder sb;

    public SurveyPagerAdapter(ClickListener.SurveyClickListener clickListener, Context mContext, List<SurveyQuestionResponse.DataBean.QuestionListBean> questionLists) {
        this.mContext = mContext;
        this.questionLists = questionLists;
        this.clickListener = clickListener;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return questionLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.preference_pager_view, container, false);

        TextView tvQuestionTitle = itemView.findViewById(R.id.tvQuestionTitle);
        TextView tvQuestionDesc = itemView.findViewById(R.id.tvQuestionDesc);
        RecyclerView rcvAnsList = itemView.findViewById(R.id.rcvAnsList);
        itemView.findViewById(R.id.tvFirstPreferenceNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onOptionClick(position, SurveyId);
                sb = new StringBuilder();
                SurveyId = "";
            }
        });

        TextView tvFirstPreferenceSkip = itemView.findViewById(R.id.tvFirstPreferenceSkip);
        tvFirstPreferenceSkip.setText(mContext.getString(R.string.na));
        if (questionLists.size() - 1 == position && count==0) {
            tvFirstPreferenceSkip.setEnabled(false);
            tvFirstPreferenceSkip.setVisibility(View.GONE);
        } else {
            tvFirstPreferenceSkip.setVisibility(View.VISIBLE);
        }
        tvFirstPreferenceSkip.setOnClickListener(v ->
                clickListener.onSingleClick(position));



        tvQuestionTitle.setText(questionLists.get(position).getQuestionTitle().toUpperCase());
        tvQuestionDesc.setText(questionLists.get(position).getSurvey_question());
        rcvAnsList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcvAnsList.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new SurveyQuestionAdapter(i -> {
            SurveyId = String.valueOf(questionLists.get(position).getOption().get(i).getOptionId());
            count++;
        }, questionLists.get(position).getOption(), mContext);
        rcvAnsList.setAdapter(adapter);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
