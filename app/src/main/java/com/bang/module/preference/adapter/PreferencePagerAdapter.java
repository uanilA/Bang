package com.bang.module.preference.adapter;

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
import com.bang.module.preference.model.PreferenceResponse;
import com.bang.base.GetClickListener;
import com.bang.base.ClickListener;

import java.util.List;

public class PreferencePagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PreferenceResponse.DataBean.QuestionListBean> questionLists;
    private ClickListener.OptionClickListener clickListener;

    private StringBuilder sb;

    public PreferencePagerAdapter(ClickListener.OptionClickListener clickListener, Context mContext , List<PreferenceResponse.DataBean.QuestionListBean> questionLists) {
        this.mContext = mContext;
        this.questionLists=questionLists;
        this.clickListener=clickListener;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return questionLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ( object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.preference_pager_view, container, false);


        TextView tvQuestionTitle = itemView.findViewById(R.id.tvQuestionTitle);
        TextView tvQuestionDesc=itemView.findViewById(R.id.tvQuestionDesc);
        RecyclerView rcvAnsList=itemView.findViewById(R.id.rcvAnsList);
        itemView.findViewById(R.id.tvFirstPreferenceNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onOptionClick(position);
            }
        });

        itemView.findViewById(R.id.tvFirstPreferenceSkip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickListener.onSingleClick(position);
            }
        });

        tvQuestionTitle.setText(questionLists.get(position).getQuestionTitle().toUpperCase());
        tvQuestionDesc.setText(questionLists.get(position).getPreference_question());
        rcvAnsList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcvAnsList.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new QuestionsAdapter(new GetClickListener() {
            @Override
            public void onClick(int i) {
                sb = new StringBuilder();
                for (int k = 0; k < questionLists.get(position).getOption().size(); k++) {
                    if ("YES".equals(questionLists.get(position).getOption().get(k).getIsSelected())) {
                        if (sb.toString().isEmpty()) {
                            sb = new StringBuilder(questionLists.get(position).getOption().get(k).getOptionId() + "");
                        } else {
                            sb.append(",").append(questionLists.get(position).getOption().get(k).getOptionId());
                        }
                    }
                }
            }


        },questionLists.get(position).getOption(), mContext);
        rcvAnsList.setAdapter(adapter);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
