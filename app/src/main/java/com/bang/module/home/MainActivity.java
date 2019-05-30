package com.bang.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.login.LoginActivity;
import com.bang.module.home.chatmodule.ChatHistoryFragment;
import com.bang.module.home.nearyou.fragment.NearYouFragment;
import com.bang.module.home.newsfeed.NewsFeedFragment;
import com.bang.module.home.profile.ProfileFragment;
import com.bang.module.home.survey.SurveyFragment;

public class MainActivity extends BangParentActivity implements View.OnClickListener {


    private RelativeLayout rlNewsFeed,rlNearYou,rlSurvey,rlChat,rlProfile;
    private ImageView ivNewsFeed,ivNearYou,ivSurvey,ivChat,ivProfile;
    private View view_newsFeed_selected,view_near_you_selected,view_survey_selected,view_chat_selected,view_profile_selected;
    private View main_tool_bar;
    private ImageView iv_addSurvey;
    private TextView tvHeaderTitle;
    private boolean doubleBackToExitPressedOnce;
    private TextView txtLogout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        newsFeedActive();
        addFragment(NewsFeedFragment.newInstance(), false, R.id.frameLayout);
        session = new Session(MainActivity.this);
        rlNewsFeed.setOnClickListener(this);
        rlNearYou.setOnClickListener(this);
        rlSurvey.setOnClickListener(this);
        rlChat.setOnClickListener(this);
        rlProfile.setOnClickListener(this);

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logout();
                finishAffinity();
            }
        });

    }

    private void init(){
        txtLogout = findViewById(R.id.txtLogout);
        main_tool_bar = findViewById(R.id.main_tool_bar);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);

        iv_addSurvey = findViewById(R.id.iv_addSurvey);

        rlNewsFeed = findViewById(R.id.rlNewsFeed);
        ivNewsFeed = findViewById(R.id.ivNewsFeed);
        view_newsFeed_selected = findViewById(R.id.view_newsFeed_selected);

        rlNearYou = findViewById(R.id.rlNearYou);
        ivNearYou = findViewById(R.id.ivNearYou);
        view_near_you_selected = findViewById(R.id.view_near_you_selected);

        rlSurvey = findViewById(R.id.rlSurvey);
        ivSurvey = findViewById(R.id.ivSurvey);
        view_survey_selected = findViewById(R.id.view_survey_selected);


        rlChat = findViewById(R.id.rlChat);
        ivChat = findViewById(R.id.ivChat);
        view_chat_selected = findViewById(R.id.view_chat_selected);

        rlProfile = findViewById(R.id.rlProfile);
        ivProfile = findViewById(R.id.ivProfile);
        view_profile_selected = findViewById(R.id.view_profile_selected);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.rlNewsFeed:
                   newsFeedActive();
                   break;
               case R.id.rlNearYou:
                   setNearYouActive();
                   break;
               case R.id.rlSurvey:
                   setSurveyActive();
                   break;
               case R.id.rlChat:
                   setChatActive();
                   break;
               case R.id.rlProfile:
                   setProfileActive();
                   break;
           }
    }

    private void newsFeedActive(){

        replaceFragment(NewsFeedFragment.newInstance(),false,R.id.frameLayout);
        tvHeaderTitle.setText(getString(R.string.news_feed));

        main_tool_bar.setVisibility(View.VISIBLE);
        iv_addSurvey.setVisibility(View.VISIBLE);

        ivNewsFeed.setImageResource(R.drawable.ic_active_newsfeed);
        view_newsFeed_selected.setVisibility(View.VISIBLE);

        ivNearYou.setImageResource(R.drawable.ic_inactive_nearyou);
        view_near_you_selected.setVisibility(View.GONE);

        ivSurvey.setImageResource(R.drawable.ic_inactive_survey);
        view_survey_selected.setVisibility(View.GONE);

        ivChat.setImageResource(R.drawable.ic_inactive_chat);
        view_chat_selected.setVisibility(View.GONE);

        ivProfile.setImageResource(R.drawable.ic_inactive_profile);
        view_profile_selected.setVisibility(View.GONE);
    }

    private void setNearYouActive(){
        tvHeaderTitle.setText(getString(R.string.near_you));
        replaceFragment(NearYouFragment.newInstance(),false,R.id.frameLayout);
        main_tool_bar.setVisibility(View.VISIBLE);

        iv_addSurvey.setVisibility(View.GONE);

        ivNewsFeed.setImageResource(R.drawable.ic_inactive_newsfeed);
        view_newsFeed_selected.setVisibility(View.GONE);

        ivNearYou.setImageResource(R.drawable.ic_active_nearyou);
        view_near_you_selected.setVisibility(View.VISIBLE);

        ivSurvey.setImageResource(R.drawable.ic_inactive_survey);
        view_survey_selected.setVisibility(View.GONE);

        ivChat.setImageResource(R.drawable.ic_inactive_chat);
        view_chat_selected.setVisibility(View.GONE);

        ivProfile.setImageResource(R.drawable.ic_inactive_profile);
        view_profile_selected.setVisibility(View.GONE);
    }
    private void setSurveyActive(){

        tvHeaderTitle.setText(getString(R.string.survey));
        replaceFragment(SurveyFragment.newInstance(),false,R.id.frameLayout);

        iv_addSurvey.setVisibility(View.GONE);
        main_tool_bar.setVisibility(View.VISIBLE);

        ivNewsFeed.setImageResource(R.drawable.ic_inactive_newsfeed);
        view_newsFeed_selected.setVisibility(View.GONE);

        ivNearYou.setImageResource(R.drawable.ic_inactive_nearyou);
        view_near_you_selected.setVisibility(View.GONE);

        ivSurvey.setImageResource(R.drawable.ic_active_survey);
        view_survey_selected.setVisibility(View.VISIBLE);

        ivChat.setImageResource(R.drawable.ic_inactive_chat);
        view_chat_selected.setVisibility(View.GONE);

        ivProfile.setImageResource(R.drawable.ic_inactive_profile);
        view_profile_selected.setVisibility(View.GONE);
    }

    private void setChatActive(){

        tvHeaderTitle.setText(getString(R.string.messages));
        replaceFragment(ChatHistoryFragment.newInstance(),false,R.id.frameLayout);
        main_tool_bar.setVisibility(View.VISIBLE);
        iv_addSurvey.setVisibility(View.GONE);

        ivNewsFeed.setImageResource(R.drawable.ic_inactive_newsfeed);
        view_newsFeed_selected.setVisibility(View.GONE);

        ivNearYou.setImageResource(R.drawable.ic_inactive_nearyou);
        view_near_you_selected.setVisibility(View.GONE);

        ivSurvey.setImageResource(R.drawable.ic_inactive_survey);
        view_survey_selected.setVisibility(View.GONE);

        ivChat.setImageResource(R.drawable.ic_active_chat);
        view_chat_selected.setVisibility(View.VISIBLE);

        ivProfile.setImageResource(R.drawable.ic_inactive_profile);
        view_profile_selected.setVisibility(View.GONE);
    }

    private void setProfileActive(){

        replaceFragment(ProfileFragment.newInstance(),false,R.id.frameLayout);

        main_tool_bar.setVisibility(View.GONE);

        ivNewsFeed.setImageResource(R.drawable.ic_inactive_newsfeed);
        view_newsFeed_selected.setVisibility(View.GONE);

        ivNearYou.setImageResource(R.drawable.ic_inactive_nearyou);
        view_near_you_selected.setVisibility(View.GONE);

        ivSurvey.setImageResource(R.drawable.ic_inactive_survey);
        view_survey_selected.setVisibility(View.GONE);

        ivChat.setImageResource(R.drawable.ic_inactive_chat);
        view_chat_selected.setVisibility(View.GONE);

        ivProfile.setImageResource(R.drawable.ic_active_profile);
        view_profile_selected.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        Handler handler = new Handler();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

            final FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);

                    if (fragment instanceof NewsFeedFragment) {
                      //  newsFeedActive();

                    } else if (fragment instanceof NearYouFragment) {
                     //   setNearYouActive();

                    } else if (fragment instanceof SurveyFragment) {
                         // setSurveyActive();

                    } else if (fragment instanceof ChatHistoryFragment) {
                      //  setChatActive();

                    } else if (fragment instanceof ProfileFragment) {
                       // setProfileActive();

                    }
                }
            };
            getSupportFragmentManager().addOnBackStackChangedListener(listener);

        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            CustomToast.getInstance(this).showToast(this, getString(R.string.click_again_to_exit));

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }
}
