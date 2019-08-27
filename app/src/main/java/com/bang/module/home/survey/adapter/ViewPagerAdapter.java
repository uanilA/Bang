package com.bang.module.home.survey.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bang.module.home.survey.fragments.ReceivedFragment;
import com.bang.module.home.survey.fragments.SentFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ReceivedFragment();
            case 1:
                return new SentFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Received";
            case 1:
                return "Sent";
        }
        return null;

    }
}
