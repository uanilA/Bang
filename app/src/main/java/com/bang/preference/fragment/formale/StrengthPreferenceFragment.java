package com.bang.preference.fragment.formale;

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


public class StrengthPreferenceFragment extends Fragment implements View.OnClickListener {


    private CheckBox cvPreferenceStrengthFirstCheck,cvPreferenceStrengthSecondCheck,cvPreferenceStrengthThirdCheck;
    private RelativeLayout rlPreferenceStrengthFirstLayout,rlPreferenceStrengthSecondLayout,rlPreferenceStrengthThirdLayout;
    private Context mContext;

    private TextView tvFirstPreferenceNext;

    public StrengthPreferenceFragment() {
        // Required empty public constructor
    }


    public static StrengthPreferenceFragment newInstance() {
        StrengthPreferenceFragment fragment = new StrengthPreferenceFragment();
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
        View view= inflater.inflate(R.layout.fragment_strength_preference, container, false);
        init(view);

        rlPreferenceStrengthFirstLayout.setOnClickListener(this);
        rlPreferenceStrengthSecondLayout.setOnClickListener(this);
        rlPreferenceStrengthThirdLayout.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);

        cvPreferenceStrengthFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceStrengthSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });

        cvPreferenceStrengthThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        cvPreferenceStrengthFirstCheck = view.findViewById(R.id.cvPreferenceStrengthFirstCheck);
        cvPreferenceStrengthSecondCheck = view.findViewById(R.id.cvPreferenceStrengthSecondCheck);
        cvPreferenceStrengthThirdCheck = view.findViewById(R.id.cvPreferenceStrengthThirdCheck);

        rlPreferenceStrengthFirstLayout = view.findViewById(R.id.rlPreferenceStrengthFirstLayout);
        rlPreferenceStrengthSecondLayout = view.findViewById(R.id.rlPreferenceStrengthSecondLayout);
        rlPreferenceStrengthThirdLayout = view.findViewById(R.id.rlPreferenceStrengthThirdLayout);

        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);
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

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.rlPreferenceStrengthFirstLayout:
                 if (cvPreferenceStrengthFirstCheck.isChecked()) {
                     firstcheckviewselection();
                 }else {firstuncheckviewselection();}
                 break;
             case R.id.rlPreferenceStrengthSecondLayout:
                 if (cvPreferenceStrengthSecondCheck.isChecked()){
                     secondcheckviewselection();}
                 else { seconduncheckviewselection(); }
                 break;
             case R.id.rlPreferenceStrengthThirdLayout:
                 if (cvPreferenceStrengthThirdCheck.isChecked()){
                     thirdcheckviewselection();}else {
                     thirduncheckviewselection(); }
                 break;
             case R.id.tvFirstPreferenceNext:
                 ((PreferenceActivity)mContext).addFragment(StaminaPreferenceFragment.newInstance(),true,R.id.framedPreferenceReplace);
                 break;
             case R.id.tvFirstPreferenceSkip:
                 ((PreferenceActivity)mContext).addFragment(StaminaPreferenceFragment.newInstance(),true,R.id.framedPreferenceReplace);
                 break;
         }
    }

    private void firstcheckviewselection(){
        cvPreferenceStrengthFirstCheck.setChecked(false);
        rlPreferenceStrengthFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceStrengthSecondCheck.setChecked(false);
        rlPreferenceStrengthSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceStrengthThirdCheck.setChecked(false);
        rlPreferenceStrengthThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceStrengthFirstCheck.setChecked(true);
        rlPreferenceStrengthFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceStrengthSecondCheck.setChecked(true);
        rlPreferenceStrengthSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceStrengthThirdCheck.setChecked(true);
        rlPreferenceStrengthThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }
}
