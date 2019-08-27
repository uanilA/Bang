package com.bang.module.home.addsurvey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;


public class NoPreferenceFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tvSetPreferences;
    private TextView tvNoPreferenceCancel;

    private String mParam1;

    public NoPreferenceFragment() {
        // Required empty public constructor
    }


    public static NoPreferenceFragment newInstance(String param1) {
        NoPreferenceFragment fragment = new NoPreferenceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_preference, container, false);
        init(view);
        tvSetPreferences.setOnClickListener(this);
        tvNoPreferenceCancel.setOnClickListener(this);
        return view;
    }

    private void init(View view) {
        tvSetPreferences = view.findViewById(R.id.tvSetPreferences);
        tvNoPreferenceCancel = view.findViewById(R.id.tvNoPreferenceCancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSetPreferences:
                activity.replaceFragment(RemainingPreferenceFragment.newInstance(mParam1,"notSelectedPref"),true, R.id.frameAddSurvey);
                break;
            case R.id.tvNoPreferenceCancel:
                activity.onBackPressed();
                break;
        }
    }
}