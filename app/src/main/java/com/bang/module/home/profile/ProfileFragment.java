package com.bang.module.home.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bang.R;
import com.bang.module.home.MainActivity;
import com.bang.module.setting.SettingActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rlProfileSetting;
    Context mContext;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        rlProfileSetting.setOnClickListener(this);
        return view;
    }

    private void init(View view){
        rlProfileSetting = view.findViewById(R.id.rlProfileSetting);
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
               case R.id.rlProfileSetting:
                   startActivity(new Intent(mContext, SettingActivity.class));
                   break;
           }
    }
}
