package com.bang.module.home.addsurvey.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.bang.R;
import com.bang.base.GetClickListener;
import com.bang.module.home.addsurvey.model.SurveyQuestionResponse;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SurveyQuestionAdapter extends RecyclerView.Adapter<SurveyQuestionAdapter.MyViewHolder> {

    private List<SurveyQuestionResponse.DataBean.QuestionListBean.OptionBean> optionLists;
    private Context mContext;
    private GetClickListener clickListener;
    private int selected_position = -1;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        CheckBox cvPreferenceFirstCheck;
        RelativeLayout rlPreferenceFirstLayout;
        RelativeLayout llPreferenceParentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            this.roundedImageView = itemView.findViewById(R.id.ivPreference);
            this.cvPreferenceFirstCheck = itemView.findViewById(R.id.cvPreferenceFirstCheck);
            this.rlPreferenceFirstLayout = itemView.findViewById(R.id.rlPreferenceFirstLayout);
            this.llPreferenceParentLayout = itemView.findViewById(R.id.llPreferenceParentLayout);
        }
    }

    SurveyQuestionAdapter(GetClickListener clickListener ,List<SurveyQuestionResponse.DataBean.QuestionListBean.OptionBean> optionLists, Context mContext) {
        this.optionLists = optionLists;
        this.mContext = mContext;
        this.clickListener= clickListener;
    }

    @NonNull
    @Override
    public SurveyQuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preference_view, parent, false);
        return new SurveyQuestionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SurveyQuestionAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {

        String flag1=optionLists.get(listPosition).getOptionPhotos();
        Picasso.with(mContext).load(flag1).error(R.drawable.user_img).into(holder.roundedImageView);
      /*  if (optionLists.get(listPosition).getIsSelected().equals("YES")){
            holder.rlPreferenceFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);
            holder.cvPreferenceFirstCheck.setChecked(true);
        }else {
            holder.rlPreferenceFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
            holder.cvPreferenceFirstCheck.setChecked(false);
        }*/

        holder.llPreferenceParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPosition!=-1) {
                    notifyItemChanged(selected_position);
                    selected_position = listPosition;
                    notifyItemChanged(selected_position);
                    clickListener.onClick(listPosition);
                }
                clickListener.onClick(listPosition);
            }
        });


        if (listPosition == selected_position) {
            holder.cvPreferenceFirstCheck.setChecked(true);
            holder.rlPreferenceFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

        } else {
            holder.cvPreferenceFirstCheck.setChecked(false);
            holder.rlPreferenceFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
        }

    }

    @Override
    public int getItemCount() {
        return optionLists.size();
    }


}
