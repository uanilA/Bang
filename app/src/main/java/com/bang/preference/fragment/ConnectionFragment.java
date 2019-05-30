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


public class ConnectionFragment extends Fragment implements View.OnClickListener {

    private CheckBox cvPreferenceConnectionFirstCheck,cvPreferenceConnectionSecondCheck,cvPreferenceConnectionThirdCheck;
    private RelativeLayout rlPreferenceConnectionFirstLayout,rlPreferenceConnectionSecondLayout,rlPreferenceConnectionThirdLayout;
    private Context mContext;
    private TextView tvFirstPreferenceNext;
    private TextView tvFirstPreferenceSkip;


    public ConnectionFragment() {
        // Required empty public constructor
    }


    public static ConnectionFragment newInstance() {
        ConnectionFragment fragment = new ConnectionFragment();
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
        View view= inflater.inflate(R.layout.fragment_connection, container, false);
        init(view);

        rlPreferenceConnectionFirstLayout.setOnClickListener(this);
        rlPreferenceConnectionSecondLayout.setOnClickListener(this);
        rlPreferenceConnectionThirdLayout.setOnClickListener(this);
        tvFirstPreferenceNext.setOnClickListener(this);
        tvFirstPreferenceSkip.setOnClickListener(this);


        cvPreferenceConnectionFirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstuncheckviewselection(); }
                else
                { firstcheckviewselection(); }
            }
        });

        cvPreferenceConnectionSecondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seconduncheckviewselection(); }
                else
                { secondcheckviewselection(); }
            }
        });
        cvPreferenceConnectionThirdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        cvPreferenceConnectionFirstCheck = view.findViewById(R.id.cvPreferenceConnectionFirstCheck);
        cvPreferenceConnectionSecondCheck = view.findViewById(R.id.cvPreferenceConnectionSecondCheck);
        cvPreferenceConnectionThirdCheck = view.findViewById(R.id.cvPreferenceConnectionThirdCheck);

        rlPreferenceConnectionFirstLayout = view.findViewById(R.id.rlPreferenceConnectionFirstLayout);
        rlPreferenceConnectionSecondLayout = view.findViewById(R.id.rlPreferenceConnectionSecondLayout);
        rlPreferenceConnectionThirdLayout = view.findViewById(R.id.rlPreferenceConnectionThirdLayout);

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
           case R.id.rlPreferenceConnectionFirstLayout:
               if (cvPreferenceConnectionFirstCheck.isChecked()) {
                   firstcheckviewselection();
               }else {firstuncheckviewselection();}
               break;
           case R.id.rlPreferenceConnectionSecondLayout:
               if (cvPreferenceConnectionSecondCheck.isChecked()){
                   secondcheckviewselection();}
               else { seconduncheckviewselection(); }
               break;
           case R.id.rlPreferenceConnectionThirdLayout:
               if (cvPreferenceConnectionThirdCheck.isChecked()){
                   thirdcheckviewselection();}else {
                   thirduncheckviewselection(); }
               break;
           case R.id.tvFirstPreferenceNext:
               //((PreferenceActivity)mContext).replaceFragment();
               break;
           case R.id.tvFirstPreferenceSkip:

               break;
       }
    }

    private void firstcheckviewselection(){
        cvPreferenceConnectionFirstCheck.setChecked(false);
        rlPreferenceConnectionFirstLayout.setBackgroundResource(R.drawable.not_selected_image_border);
    }

    private void secondcheckviewselection(){
        cvPreferenceConnectionSecondCheck.setChecked(false);
        rlPreferenceConnectionSecondLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }
    private void thirdcheckviewselection(){
        cvPreferenceConnectionThirdCheck.setChecked(false);
        rlPreferenceConnectionThirdLayout.setBackgroundResource(R.drawable.not_selected_image_border);

    }

    private void firstuncheckviewselection(){
        cvPreferenceConnectionFirstCheck.setChecked(true);
        rlPreferenceConnectionFirstLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }

    private void seconduncheckviewselection(){
        cvPreferenceConnectionSecondCheck.setChecked(true);
        rlPreferenceConnectionSecondLayout.setBackgroundResource(R.drawable.doted_background_corners);
    }
    private void thirduncheckviewselection(){
        cvPreferenceConnectionThirdCheck.setChecked(true);
        rlPreferenceConnectionThirdLayout.setBackgroundResource(R.drawable.doted_background_corners);

    }


}