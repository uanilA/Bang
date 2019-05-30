package com.bang.preference.fragment.formale;

import android.content.Context;
import android.os.Bundle;
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


public class SizePreferenceFragment extends Fragment implements View.OnClickListener {


    private Context mContext;
    private CheckBox cvPreferenceSizeFirstCheck,cvPreferenceSizeSecondCheck,cvPreferenceSizeThirdCheck;
    private RelativeLayout rlPreferenceSizeFirstLayout,rlPreferenceSizeSecondLayout,rlPreferenceSizeThirdLayout;
    private TextView tvFirstPreferenceNext,tvFirstPreferenceSkip;

    public SizePreferenceFragment() {
        // Required empty public constructor
    }


    public static SizePreferenceFragment newInstance() {
        SizePreferenceFragment fragment = new SizePreferenceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_size_pereference, container, false);
        init(view);

        rlPreferenceSizeFirstLayout.setOnClickListener(this);
        rlPreferenceSizeSecondLayout.setOnClickListener(this);
        rlPreferenceSizeThirdLayout.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);
        tvFirstPreferenceSkip.setOnClickListener(this);

        cvPreferenceSizeFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceSizeSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });

        cvPreferenceSizeThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        cvPreferenceSizeFirstCheck = view.findViewById(R.id.cvPreferenceSizeFirstCheck);
        cvPreferenceSizeSecondCheck = view.findViewById(R.id.cvPreferenceSizeSecondCheck);
        cvPreferenceSizeThirdCheck = view.findViewById(R.id.cvPreferenceSizeThirdCheck);

        rlPreferenceSizeFirstLayout = view.findViewById(R.id.rlPreferenceSizeFirstLayout);
        rlPreferenceSizeSecondLayout = view.findViewById(R.id.rlPreferenceSizeSecondLayout);
        rlPreferenceSizeThirdLayout = view.findViewById(R.id.rlPreferenceSizeThirdLayout);

        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);
        tvFirstPreferenceSkip = view.findViewById(R.id.tvFirstPreferenceSkip);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlPreferenceSizeFirstLayout:
                if (cvPreferenceSizeFirstCheck.isChecked()) {
                    firstcheckviewselection();
                }else {firstuncheckviewselection();}
                break;
            case R.id.rlPreferenceSizeSecondLayout:
                if (cvPreferenceSizeSecondCheck.isChecked()){
                    secondcheckviewselection();}
                else { seconduncheckviewselection(); }
                break;
            case R.id.rlPreferenceSizeThirdLayout:
                if (cvPreferenceSizeThirdCheck.isChecked()){
                    thirdcheckviewselection();}else {
                    thirduncheckviewselection(); }
                break;
            case R.id.tvFirstPreferenceNext:
                ((PreferenceActivity)mContext).addFragment(StrengthPreferenceFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
            case R.id.tvFirstPreferenceSkip:
                ((PreferenceActivity)mContext).addFragment(StrengthPreferenceFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
        }
    }



    private void firstcheckviewselection(){
        cvPreferenceSizeFirstCheck.setChecked(false);
        rlPreferenceSizeFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceSizeSecondCheck.setChecked(false);
        rlPreferenceSizeSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceSizeThirdCheck.setChecked(false);
        rlPreferenceSizeThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceSizeFirstCheck.setChecked(true);
        rlPreferenceSizeFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceSizeSecondCheck.setChecked(true);
        rlPreferenceSizeSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceSizeThirdCheck.setChecked(true);
        rlPreferenceSizeThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

}