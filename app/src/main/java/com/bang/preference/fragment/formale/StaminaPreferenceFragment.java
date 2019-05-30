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
import com.bang.preference.fragment.SkillsFragment;


public class StaminaPreferenceFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlPreferenceStaminaSecondLayout,rlPreferenceStaminaThirdLayout,rlPreferenceStaminaFirstLayout;
    private CheckBox cvPreferenceStaminaFirstCheck,cvPreferenceStaminaSecondCheck,cvPreferenceStaminaThirdCheck;
    private TextView tvFirstPreferenceNext,tvFirstPreferenceSkip;

    public StaminaPreferenceFragment() {
        // Required empty public constructor
    }


    public static StaminaPreferenceFragment newInstance() {
        StaminaPreferenceFragment fragment = new StaminaPreferenceFragment();
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
        View view= inflater.inflate(R.layout.fragment_stamina_preference, container, false);
        init(view);

        rlPreferenceStaminaFirstLayout.setOnClickListener(this);
        rlPreferenceStaminaSecondLayout.setOnClickListener(this);
        rlPreferenceStaminaThirdLayout.setOnClickListener(this);
        tvFirstPreferenceSkip.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);


        cvPreferenceStaminaFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceStaminaSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });
        cvPreferenceStaminaThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);
        tvFirstPreferenceSkip = view.findViewById(R.id.tvFirstPreferenceSkip);

        rlPreferenceStaminaFirstLayout = view.findViewById(R.id.rlPreferenceStaminaFirstLayout);
        rlPreferenceStaminaSecondLayout = view.findViewById(R.id.rlPreferenceStaminaSecondLayout);
        rlPreferenceStaminaThirdLayout =view.findViewById(R.id.rlPreferenceStaminaThirdLayout);

        cvPreferenceStaminaFirstCheck = view.findViewById(R.id.cvPreferenceStaminaFirstCheck);
        cvPreferenceStaminaSecondCheck = view.findViewById(R.id.cvPreferenceStaminaSecondCheck);
        cvPreferenceStaminaThirdCheck = view.findViewById(R.id.cvPreferenceStaminaThirdCheck);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    private void firstcheckviewselection(){
        cvPreferenceStaminaFirstCheck.setChecked(false);
        rlPreferenceStaminaFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceStaminaSecondCheck.setChecked(false);
        rlPreferenceStaminaSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceStaminaThirdCheck.setChecked(false);
        rlPreferenceStaminaThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceStaminaFirstCheck.setChecked(true);
        rlPreferenceStaminaFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceStaminaSecondCheck.setChecked(true);
        rlPreferenceStaminaSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceStaminaThirdCheck.setChecked(true);
        rlPreferenceStaminaThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlPreferenceStaminaFirstLayout:
                if (cvPreferenceStaminaFirstCheck.isChecked()) {
                    firstcheckviewselection();
                } else {
                    firstuncheckviewselection();
                }
                break;
            case R.id.rlPreferenceStaminaSecondLayout:
                if (cvPreferenceStaminaSecondCheck.isChecked()) {
                    secondcheckviewselection();
                } else {
                    seconduncheckviewselection();
                }
                break;
            case R.id.rlPreferenceStaminaThirdLayout:
                if (cvPreferenceStaminaThirdCheck.isChecked()) {
                    thirdcheckviewselection();
                } else {
                    thirduncheckviewselection();
                }
                break;
            case R.id.tvFirstPreferenceSkip:
                ((PreferenceActivity)mContext).addFragment(SkillsFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
            case R.id.tvFirstPreferenceNext:
                ((PreferenceActivity)mContext).addFragment(SkillsFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
        }
    }
}
