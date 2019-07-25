package com.bang.module.home.addsurvey.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bang.R;
import com.bang.base.GetClickListener;
import com.bang.module.preference.model.SelectedPrefList;
import com.bang.module.preference.model.SelectedReferencesResponse;

import java.util.List;

public class SurveySelectGenderAdapter extends RecyclerView.Adapter<SurveySelectGenderAdapter.MyViewHolder> {

    private List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists;
    private Context mContext;
    private GetClickListener clickListener;
    private int selected_position=-1;


    public SurveySelectGenderAdapter(GetClickListener clickListener, List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists, Context mContext) {
        this.selectedPrefLists = selectedPrefLists;
        this.mContext = mContext;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public SurveySelectGenderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selected_pref_view, viewGroup, false);
        return new SurveySelectGenderAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SurveySelectGenderAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        myViewHolder.cvPreferenceFirstCheck.setVisibility(View.VISIBLE);
        myViewHolder.llCompleteLayout.setVisibility(View.GONE);
        if (selectedPrefLists.get(i).getGender() == 0) {
            myViewHolder.ivSelectedPref.setImageResource(R.drawable.boy_icon);
            myViewHolder.tvSelectedGenderPref.setText(mContext.getString(R.string.male));
            setAdapterDate(myViewHolder, i);

        } else if (selectedPrefLists.get(i).getGender() == 1) {
            myViewHolder.tvSelectedGenderPref.setText(mContext.getString(R.string.female));
            myViewHolder.ivSelectedPref.setImageResource(R.drawable.girl_icon);
            setAdapterDate(myViewHolder, i);

        } else if (selectedPrefLists.get(i).getGender() == 2) {
            myViewHolder.tvSelectedGenderPref.setText(mContext.getString(R.string.transgender_male));
            myViewHolder.ivSelectedPref.setImageResource(R.drawable.transgender_male_icon);
            setAdapterDate(myViewHolder, i);

        } else if (selectedPrefLists.get(i).getGender() == 3) {
            myViewHolder.tvSelectedGenderPref.setText(mContext.getString(R.string.transgender_female));
            myViewHolder.ivSelectedPref.setImageResource(R.drawable.transgender_female);
            setAdapterDate(myViewHolder, i);

        } else if (selectedPrefLists.get(i).getGender() == 4) {
            myViewHolder.tvSelectedGenderPref.setText(mContext.getString(R.string.non_gender));
            myViewHolder.ivSelectedPref.setImageResource(R.drawable.non_gender);
            setAdapterDate(myViewHolder, i);
        }

        if (i == selected_position) {
            myViewHolder.llPreferGender.setBackgroundResource(R.drawable.doted_background_corners);
            myViewHolder.cvPreferenceFirstCheck.setChecked(true);
            myViewHolder.tvSelectedGenderPref.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));

        } else {
            myViewHolder.llPreferGender.setBackgroundResource(R.drawable.not_selected_image_border);
            myViewHolder.cvPreferenceFirstCheck.setChecked(false);
            myViewHolder.tvSelectedGenderPref.setTextColor(mContext.getResources().getColor(R.color.colorLoginText));
        }

        myViewHolder.rlClickSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i!=-1) {
                    notifyItemChanged(selected_position);
                    selected_position = i;
                    notifyItemChanged(selected_position);
                    clickListener.onClick(i);
                }
            }
        });

        myViewHolder.cvPreferenceFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              /*  selected_position = i;
                notifyItemChanged(selected_position);*/
            }
        });

    }

    private void setAdapterDate(SurveySelectGenderAdapter.MyViewHolder viewHolder, int position) {
        if (selectedPrefLists.get(position).getTotal_question() == selectedPrefLists.get(position).getTotal_answered()) {
            //  viewHolder.llPreferGender.setBackgroundResource(R.drawable.doted_background_corners);
            viewHolder.ivComplete.setVisibility(View.VISIBLE);
            viewHolder.ivComplete.setImageResource(R.drawable.completed_ico);
            viewHolder.tvAnswers.setText(mContext.getString(R.string.complete));
           // viewHolder.tvAnswers.setTextColor(mContext.getResources().getColor(R.color.completeTextColor));

        } else if (selectedPrefLists.get(position).getTotal_answered() == 0) {
            viewHolder.ivComplete.setImageResource(R.drawable.pending_ico);
            viewHolder.tvAnswers.setText(mContext.getString(R.string.pending));
           // viewHolder.tvAnswers.setTextColor(mContext.getResources().getColor(R.color.pendingTextColor));
        } else {
            int remainingAns = selectedPrefLists.get(position).getTotal_question() - selectedPrefLists.get(position).getTotal_answered();
            viewHolder.ivComplete.setVisibility(View.GONE);
            viewHolder.tvAnswers.setText(remainingAns + " " + mContext.getString(R.string.out_of_cat) + " " + selectedPrefLists.get(position).getTotal_question());
           // viewHolder.tvAnswers.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public int getItemCount() {
        return selectedPrefLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSelectedPref;
        private TextView tvSelectedGenderPref;
        private ImageView ivComplete;
        private TextView tvAnswers;
        private RelativeLayout rlClickSelect;
        private LinearLayout llPreferGender;
        private CheckBox cvPreferenceFirstCheck;
        private LinearLayout llCompleteLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelectedPref = itemView.findViewById(R.id.ivSelectedPref);
            tvSelectedGenderPref = itemView.findViewById(R.id.tvSelectedGenderPref);
            ivComplete = itemView.findViewById(R.id.ivComplete);
            tvAnswers = itemView.findViewById(R.id.tvAnswers);
            rlClickSelect = itemView.findViewById(R.id.rlClickSelect);
            llPreferGender = itemView.findViewById(R.id.llPreferGender);
            cvPreferenceFirstCheck = itemView.findViewById(R.id.cvPreferenceFirstCheck);
            llCompleteLayout = itemView.findViewById(R.id.llCompleteLayout);
        }
    }
}
