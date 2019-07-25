package com.bang.module.home.survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.module.home.MainActivity;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.bang.module.home.survey.adapter.ViewPagerAdapter;


public class SurveyFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private long mLastClickTime = 0;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey, container, false);
        return view;
    }



    private void init(View view) {

        view.findViewById(R.id.iv_addSurvey).setOnClickListener(this);
        ((MainActivity) mContext).findViewById(R.id.main_tool_bar).setVisibility(View.GONE);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }*/

  /*  @Override
    public void onDetach() {
        super.onDetach();
    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (v.getId() == R.id.iv_addSurvey) {
            startActivity(new Intent(mContext, AddSurveyActivity.class));
        }
    }



}
