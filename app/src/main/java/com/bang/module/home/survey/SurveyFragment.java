package com.bang.module.home.survey;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bang.R;
import com.bang.module.home.survey.adapter.ViewPagerAdapter;


public class SurveyFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Context mContext;
    private ViewPagerAdapter adapter;

    public SurveyFragment() {
        // Required empty public constructor
    }


    public static SurveyFragment newInstance() {
        SurveyFragment fragment = new SurveyFragment();
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
        View view = inflater.inflate(R.layout.fragment_survey, container, false);
        return view;
    }


    private void init(View view) {
        viewPager = view.findViewById(R.id.viewpager);

        tabLayout = view.findViewById(R.id.tabs);
       /* tabLayout.addTab(tabLayout.newTab().setText("Received"));
        tabLayout.addTab(tabLayout.newTab().setText("Sent"));*/

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
