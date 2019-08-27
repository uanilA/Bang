package com.bang.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BangParentActivity;
import com.bang.fcm.NotificationModel;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.chatmodule.ChatHistoryFragment;
import com.bang.module.home.nearyou.fragment.NearYouFragment;
import com.bang.module.home.newsfeed.fragment.NewsFeedFragment;
import com.bang.module.home.profile.getprofile.ProfileFragment;
import com.bang.module.home.profile.mypost.MyPostActivity;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.module.home.survey.SurveyFragment;
import com.bang.module.home.survey.activity.SurveyDetailActivity;

public class MainActivity extends BangParentActivity implements View.OnClickListener {


    private RelativeLayout rlNewsFeed, rlNearYou, rlSurvey, rlChat, rlProfile;
    private ImageView ivNewsFeed, ivNearYou, ivSurvey, ivChat, ivProfile;
    private View view_newsFeed_selected, view_near_you_selected, view_survey_selected, view_chat_selected, view_profile_selected;
    private View main_tool_bar;
    private ImageView iv_addSurvey;
    private TextView tvHeaderTitle;
    private boolean doubleBackToExitPressedOnce;
    private TextView txtLogout;
    private Session session;
    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleNotification(getIntent());
        init();

       // setSurveyActive();
        /*addFragment(SurveyFragment.newInstance(), false, R.id.frameLayout);*/
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

    /* ..............for handle notification of group request...............*/
    private void handleNotification(Intent intent) {

        Bundle bundle = getIntent().getExtras();
        if (intent.getSerializableExtra("Notificatons") != null) {
            NotificationModel notificationModel = (NotificationModel) intent.getSerializableExtra("Notificatons");
                       switch (notificationModel.getNotification_type()) {
                            case "survey_comment":
                            case "survey_complete":
                                intent = new Intent(this, SurveyDetailActivity.class);
                                intent.putExtra("surveyId", notificationModel.getSurvey_id());
                                startActivity(intent);
                                break;
                            case "follow":
                            case "bang_request":
                            case "bang_request_accept":
                                intent = new Intent(this, OtherUserProfileActivity.class);
                                intent.putExtra("OtherUserId", notificationModel.getBy_user_id());
                                startActivity(intent);
                                break;
                            case "newsfeed_create":
                            case "survey_share":
                               // intent = new Intent(this,MainActivity.class);
                                break;
                            case "newsfeed_like":
                                intent = new Intent(this, MyPostActivity.class);
                                startActivity(intent);
                                break;
                        }
        } else {
            if (bundle != null) {
                if (bundle.getString("notification_type") != null) {

                    NotificationModel notificationModal = new NotificationModel();
                    notificationModal.setSurvey_id(bundle.getString("survey_id"));
                    notificationModal.setBody(bundle.getString("body"));
                    notificationModal.setNotification_type(bundle.getString("notification_type"));
                    notificationModal.setTitle(bundle.getString("title"));
                    notificationModal.setBang_request_id(bundle.getString("bang_request_id"));
                    notificationModal.setBy_user_id(bundle.getString("by_user_id"));
                    notificationModal.setNewsfeed_id(bundle.getString("newsfeed_id"));
                    assert notificationModal.getNotification_type() != null;
                                switch (notificationModal.getNotification_type()) {
                                    case "survey_comment":
                                    case "survey_complete":
                                        intent = new Intent(this, SurveyDetailActivity.class);
                                        intent.putExtra("surveyId", notificationModal.getSurvey_id());
                                        break;
                                    case "follow":
                                    case "bang_request":
                                    case "bang_request_accept":
                                        intent = new Intent(this, OtherUserProfileActivity.class);
                                        intent.putExtra("OtherUserId", notificationModal.getSurvey_id());
                                        startActivity(intent);
                                        break;
                                    case "newsfeed_create":
                                    case "survey_share":
                                      //  intent = new Intent(this,MainActivity.class);
                                        break;
                                    case "newsfeed_like":
                                        intent = new Intent(this, MyPostActivity.class);
                                        startActivity(intent);
                                        break;

                                }

                } /*else if (bundle.getString("type") != null) {
                    NotificationModal notificationModal = new NotificationModal();
                    notificationModal.body = bundle.getString("body");
                    notificationModal.type = bundle.getString("type");
                    notificationModal.titleMsg = bundle.getString("title");
                    notificationModal.opponentMetaId = bundle.getString("receiverId");
                    notificationModal.userMetaId = bundle.getString("senderId");

                    rtlv_inbox.callOnClick();
                    intent = new Intent(this, ChatActivity.class);
                    intent.putExtra("NotificatonForChat", notificationModal);
                    startActivity(intent);
                }*/
            }
        }
    }


    private void init() {
        txtLogout = findViewById(R.id.txtLogout);
        //txtLogout = findViewById(R.id.txtLogout);
        main_tool_bar = findViewById(R.id.main_tool_bar);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setVisibility(View.GONE);
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

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.rlNewsFeed:
                clicknewsFees();
                break;
            case R.id.rlNearYou:
                clickNearYou();
                break;
            case R.id.rlSurvey:
                clickServey();
                break;
            case R.id.rlChat:
                clickChat();
                break;
            case R.id.rlProfile:
                clickProfile();
                break;
        }
    }


    private void clickServey() {
        if (AppHelper.isConnectingToInternet(MainActivity.this)) {
            setSurveyActive();
            replaceFragment(SurveyFragment.newInstance(), false, R.id.frameLayout);
        }
    }

    private void clicknewsFees() {
        if (AppHelper.isConnectingToInternet(MainActivity.this)) {
            newsFeedActive();
            replaceFragment(NewsFeedFragment.newInstance(), false, R.id.frameLayout);
        }
    }

    private void clickNearYou() {
        if (AppHelper.isConnectingToInternet(MainActivity.this)) {
            setNearYouActive();
            replaceFragment(NearYouFragment.newInstance(), false, R.id.frameLayout);
        }
    }

    private void clickChat() {
        if (AppHelper.isConnectingToInternet(MainActivity.this)) {
            setChatActive();
            replaceFragment(ChatHistoryFragment.newInstance(), false, R.id.frameLayout);
        }
    }

    private void clickProfile() {
        if (AppHelper.isConnectingToInternet(MainActivity.this)) {
            setProfileActive();
            replaceFragment(ProfileFragment.newInstance(), false, R.id.frameLayout);
        }
    }


    private void newsFeedActive() {

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

    private void setNearYouActive() {
        tvHeaderTitle.setText(getString(R.string.near_you));
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

    private void setSurveyActive() {
        main_tool_bar.setVisibility(View.GONE);
        tvHeaderTitle.setText(getString(R.string.give_survey));
        iv_addSurvey.setVisibility(View.VISIBLE);
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

    private void setChatActive() {

        tvHeaderTitle.setText(getString(R.string.messages));
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

    private void setProfileActive() {

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

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frameLayout);
        if (fragment instanceof NewsFeedFragment) {
            NewsFeedFragment infoFragment = (NewsFeedFragment) fragment;
          //  infoFragment.newsfeedListBeans.clear();
          //  infoFragment.apiCalling(0);
        }else if (fragment instanceof  NearYouFragment){
            NearYouFragment nearYouFragment= (NearYouFragment)fragment;
            nearYouFragment.displayCurrentLocation();
        }
    }
}