package com.bang.base;


/**
 * Created by anil
 * Date: 23/05/19
 * Time: 03:07 PM
 */

public interface ClickListener {


    interface OptionClickListener{
        void onOptionClick(int position);
        void onSingleClick(int position);
    }

    interface SurveyClickListener {
        void onOptionClick(int position,String SurveyId);
        void onSingleClick(int position);
    }

    interface SurveySingleClick {
        void onSingleClick(int position);
    }

    interface ContactNumberClick{
         void onContactClick(String number);
    }

    interface FollowersClick{
        void onFollowSingleClick(int position);
    }

    interface NewsFeedClick{
        void onProfileClick(int position);
        void onReportTUserClick(int position);
        void onVideoPlayClick(int position);
        void onSingleLikeClick(int position);
    }

    interface MyPostClick{
        void onReportTUserClick(int position);
        void onVideoPlayClick(int position);
        void onSingleLikeClick(int position);
    }
}
