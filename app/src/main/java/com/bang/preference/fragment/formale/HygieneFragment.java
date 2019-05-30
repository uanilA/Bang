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
import com.bang.preference.fragment.ConnectionFragment;


public class HygieneFragment extends Fragment implements View.OnClickListener {

    Context mContext;
    private RelativeLayout rlPreferenceHygieneFirstLayout,rlPreferenceHygieneSecondLayout,rlPreferenceHygieneThirdLayout;
    private CheckBox cvPreferenceHygieneFirstCheck,cvPreferenceHygieneSecondCheck,cvPreferenceHygieneThirdCheck;
    private TextView tvFirstPreferenceNext,tvFirstPreferenceSkip;


    public HygieneFragment() {
        // Required empty public constructor
    }


    public static HygieneFragment newInstance() {
        HygieneFragment fragment = new HygieneFragment();
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
        View view= inflater.inflate(R.layout.fragment_hygienek, container, false);
        init(view);

        rlPreferenceHygieneFirstLayout.setOnClickListener(this);
        rlPreferenceHygieneSecondLayout.setOnClickListener(this);
        rlPreferenceHygieneThirdLayout.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);
        tvFirstPreferenceSkip.setOnClickListener(this);

        cvPreferenceHygieneFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceHygieneSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });

        cvPreferenceHygieneThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        cvPreferenceHygieneFirstCheck = view.findViewById(R.id.cvPreferenceHygieneFirstCheck);
        cvPreferenceHygieneSecondCheck = view.findViewById(R.id.cvPreferenceHygieneSecondCheck);
        cvPreferenceHygieneThirdCheck = view.findViewById(R.id.cvPreferenceHygieneThirdCheck);

        rlPreferenceHygieneFirstLayout = view.findViewById(R.id.rlPreferenceHygieneFirstLayout);
        rlPreferenceHygieneSecondLayout = view.findViewById(R.id.rlPreferenceHygieneSecondLayout);
        rlPreferenceHygieneThirdLayout = view.findViewById(R.id.rlPreferenceHygieneThirdLayout);

        tvFirstPreferenceNext = view.findViewById(R.id.tvFirstPreferenceNext);
        tvFirstPreferenceSkip = view.findViewById(R.id.tvFirstPreferenceSkip);
    }

    private void firstcheckviewselection(){
        cvPreferenceHygieneFirstCheck.setChecked(false);
        rlPreferenceHygieneFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceHygieneSecondCheck.setChecked(false);
        rlPreferenceHygieneSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceHygieneThirdCheck.setChecked(false);
        rlPreferenceHygieneThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceHygieneFirstCheck.setChecked(true);
        rlPreferenceHygieneFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }

    private void seconduncheckviewselection(){
        cvPreferenceHygieneSecondCheck.setChecked(true);
        rlPreferenceHygieneSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }

    private void thirduncheckviewselection(){
        cvPreferenceHygieneThirdCheck.setChecked(true);
        rlPreferenceHygieneThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

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
            case R.id.rlPreferenceHygieneFirstLayout:
                if (cvPreferenceHygieneFirstCheck.isChecked()) {
                    firstcheckviewselection();
                }else {firstuncheckviewselection();}
                break;
            case R.id.rlPreferenceHygieneSecondLayout:
                if (cvPreferenceHygieneSecondCheck.isChecked()){
                    secondcheckviewselection();}
                else { seconduncheckviewselection(); }
                break;
            case R.id.rlPreferenceHygieneThirdLayout:
                if (cvPreferenceHygieneThirdCheck.isChecked()){
                    thirdcheckviewselection();}else {
                    thirduncheckviewselection(); }
                break;
            case R.id.tvFirstPreferenceNext:
                ((PreferenceActivity)mContext).addFragment(ConnectionFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
            case R.id.tvFirstPreferenceSkip:
                ((PreferenceActivity)mContext).addFragment(ConnectionFragment.newInstance(),true,R.id.framedPreferenceReplace);
                break;
        }
    }
}
