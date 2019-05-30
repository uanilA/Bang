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
import com.bang.preference.fragment.formale.SizePreferenceFragment;


public class PreferenceFragment extends Fragment implements View.OnClickListener {

    private CheckBox cvPreferenceFirstCheck,cvPreferenceSecondCheck,cvPreferenceThirdCheck;
    private RelativeLayout rlPreferenceFirstLayout,rlPreferenceSecondLayout,rlPreferenceThirdLayout;
    private TextView tvFirstPreferenceNext,tvFirstPreferenceSkip;

    private Context mContext;

    public PreferenceFragment() {
        // Required empty public constructor
    }

    public static PreferenceFragment newInstance() {
        PreferenceFragment fragment = new PreferenceFragment();
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
        View view= inflater.inflate(R.layout.fragment_prefrence, container, false);
        init(view);

        rlPreferenceFirstLayout.setOnClickListener(this);
        rlPreferenceSecondLayout.setOnClickListener(this);
        rlPreferenceThirdLayout.setOnClickListener(this);
        tvFirstPreferenceSkip.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);

        cvPreferenceFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });

        cvPreferenceThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        cvPreferenceFirstCheck = view.findViewById(R.id.cvPreferenceFirstCheck);
        cvPreferenceSecondCheck = view.findViewById(R.id.cvPreferenceSecondCheck);
        cvPreferenceThirdCheck = view.findViewById(R.id.cvPreferenceThirdCheck);

        rlPreferenceFirstLayout = view.findViewById(R.id.rlPreferenceFirstLayout);
        rlPreferenceSecondLayout = view.findViewById(R.id.rlPreferenceSecondLayout);
        rlPreferenceThirdLayout = view.findViewById(R.id.rlPreferenceThirdLayout);

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
            case R.id.rlPreferenceFirstLayout:
                if (cvPreferenceFirstCheck.isChecked()) {
                    firstcheckviewselection();
                }else {firstuncheckviewselection();}
                break;
            case R.id.rlPreferenceSecondLayout:
                if (cvPreferenceSecondCheck.isChecked()){
                    secondcheckviewselection();}
                else { seconduncheckviewselection(); }
                break;
            case R.id.rlPreferenceThirdLayout:
                if (cvPreferenceThirdCheck.isChecked()){
                    thirdcheckviewselection();}else {
                    thirduncheckviewselection();
                }
                break;
            case R.id.tvFirstPreferenceSkip:
                ((PreferenceActivity)mContext).addFragment(SizePreferenceFragment.newInstance(),false,R.id.framedPreferenceReplace);
                break;
            case R.id.tvFirstPreferenceNext:
                ((PreferenceActivity)mContext).addFragment(SizePreferenceFragment.newInstance(),false,R.id.framedPreferenceReplace);
                break;
        }
    }

    private void firstcheckviewselection(){
        cvPreferenceFirstCheck.setChecked(false);
        rlPreferenceFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceSecondCheck.setChecked(false);
        rlPreferenceSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceThirdCheck.setChecked(false);
        rlPreferenceThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceFirstCheck.setChecked(true);
        rlPreferenceFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceSecondCheck.setChecked(true);
        rlPreferenceSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceThirdCheck.setChecked(true);
        rlPreferenceThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

}
