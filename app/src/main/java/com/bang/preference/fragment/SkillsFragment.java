package com.bang.preference.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bang.R;
import com.bang.preference.activity.PreferenceActivity;
import com.bang.preference.fragment.formale.HygieneFragment;


public class SkillsFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private CheckBox cvPreferenceSkillsFirstCheck,cvPreferenceSkillsSecondCheck,cvPreferenceSkillsThirdCheck;
    private RelativeLayout rlPreferenceSkillsFirstLayout,rlPreferenceSkillsSecondLayout,rlPreferenceSkillsThirdLayout;
    private TextView tvFirstPreferenceSkip,tvFirstPreferenceNext;

    public SkillsFragment() {
    }

    public static SkillsFragment newInstance() {
        SkillsFragment fragment = new SkillsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_skills, container, false);
        init(view);

        tvFirstPreferenceSkip.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);
        rlPreferenceSkillsFirstLayout.setOnClickListener(this);
        rlPreferenceSkillsSecondLayout.setOnClickListener(this);
        rlPreferenceSkillsThirdLayout.setOnClickListener(this);

        cvPreferenceSkillsFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceSkillsSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });

        cvPreferenceSkillsThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    thirduncheckviewselection(); }
                else
                { thirdcheckviewselection(); }
            }
        });

        return view;
    }

    private void init(View view){
        tvFirstPreferenceSkip = view.findViewById(R.id.tvFirstPreferenceSkip);
        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);

        cvPreferenceSkillsFirstCheck = view.findViewById(R.id.cvPreferenceSkillsFirstCheck);
        cvPreferenceSkillsSecondCheck = view.findViewById(R.id.cvPreferenceSkillsSecondCheck);
        cvPreferenceSkillsThirdCheck = view.findViewById(R.id.cvPreferenceSkillsThirdCheck);

        rlPreferenceSkillsFirstLayout = view.findViewById(R.id.rlPreferenceSkillsFirstLayout);
        rlPreferenceSkillsSecondLayout = view.findViewById(R.id.rlPreferenceSkillsSecondLayout);
        rlPreferenceSkillsThirdLayout = view.findViewById(R.id.rlPreferenceSkillsThirdLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlPreferenceSkillsFirstLayout:
                if (cvPreferenceSkillsFirstCheck.isChecked()) {
                    firstcheckviewselection();
                }else {firstuncheckviewselection();}
                break;

            case R.id.rlPreferenceSkillsSecondLayout:
                if (cvPreferenceSkillsSecondCheck.isChecked()){
                    secondcheckviewselection();}
                else { seconduncheckviewselection(); }
                break;

            case R.id.rlPreferenceSkillsThirdLayout:
                if (cvPreferenceSkillsThirdCheck.isChecked()){
                    thirdcheckviewselection();}else {
                    thirduncheckviewselection();
                }
                break;

            case R.id.tvFirstPreferenceSkip:
                ((PreferenceActivity)mContext).addFragment(HygieneFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
            case R.id.tvFirstPreferenceNext:
                ((PreferenceActivity)mContext).addFragment(HygieneFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
        }
    }

    private void firstcheckviewselection(){
        cvPreferenceSkillsFirstCheck.setChecked(false);
        rlPreferenceSkillsFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceSkillsSecondCheck.setChecked(false);
        rlPreferenceSkillsSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceSkillsThirdCheck.setChecked(false);
        rlPreferenceSkillsThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceSkillsFirstCheck.setChecked(true);
        rlPreferenceSkillsFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceSkillsSecondCheck.setChecked(true);
        rlPreferenceSkillsSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceSkillsThirdCheck.setChecked(true);
        rlPreferenceSkillsThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext= context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
