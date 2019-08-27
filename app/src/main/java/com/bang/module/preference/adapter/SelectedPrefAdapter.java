package com.bang.module.preference.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.GetClickListener;
import com.bang.module.preference.model.SelectedReferencesResponse;

import java.util.List;

public class SelectedPrefAdapter extends RecyclerView.Adapter<SelectedPrefAdapter.MyViewHolder> {

    private List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists;
    private Context mContext;
    private GetClickListener clickListener;


    public SelectedPrefAdapter(GetClickListener clickListener, List<SelectedReferencesResponse.DataBean.UserSurveyStatsBean> selectedPrefLists, Context mContext) {
        this.selectedPrefLists = selectedPrefLists;
        this.mContext = mContext;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.status_pref_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
       // myViewHolder.cvPreferenceFirstCheck.setVisibility(View.GONE);
        myViewHolder.llCompleteLayout.setVisibility(View.VISIBLE);
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

        myViewHolder.rlClickSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(i);
            }
        });

    }

    private void setAdapterDate(MyViewHolder viewHolder, int position) {
        if (selectedPrefLists.get(position).getTotal_question() == selectedPrefLists.get(position).getTotal_answered()) {
            viewHolder.llPreferGender.setBackgroundResource(R.drawable.doted_background_corners);
            viewHolder.ivComplete.setVisibility(View.VISIBLE);
            viewHolder.ivComplete.setImageResource(R.drawable.completed_ico);
            viewHolder.tvSelectedGenderPref.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            viewHolder.tvAnswers.setText(mContext.getString(R.string.complete));
            viewHolder.tvAnswers.setTextColor(mContext.getResources().getColor(R.color.completeTextColor));

        } else if (selectedPrefLists.get(position).getTotal_answered() == 0) {
            viewHolder.ivComplete.setImageResource(R.drawable.pending_ico);
            viewHolder.tvAnswers.setText(mContext.getString(R.string.pending));
            viewHolder.tvAnswers.setTextColor(ContextCompat.getColor(mContext,R.color.pendingTextColor));
        } else {
            int remainingAns = selectedPrefLists.get(position).getTotal_question() - selectedPrefLists.get(position).getTotal_answered();
            viewHolder.ivComplete.setVisibility(View.GONE);
            viewHolder.tvAnswers.setText(remainingAns + " " + mContext.getString(R.string.out_of_cat) + " " + selectedPrefLists.get(position).getTotal_question());
            viewHolder.tvAnswers.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
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
       // private CheckBox cvPreferenceFirstCheck;
        private LinearLayout llCompleteLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelectedPref = itemView.findViewById(R.id.ivSelectedPref);
            tvSelectedGenderPref = itemView.findViewById(R.id.tvSelectedGenderPref);
            ivComplete = itemView.findViewById(R.id.ivComplete);
            tvAnswers = itemView.findViewById(R.id.tvAnswers);
            rlClickSelect = itemView.findViewById(R.id.rlClickSelect);
            llPreferGender = itemView.findViewById(R.id.llPreferGender);
           // cvPreferenceFirstCheck = itemView.findViewById(R.id.cvPreferenceFirstCheck);
            llCompleteLayout = itemView.findViewById(R.id.llCompleteLayout);
        }
    }
}