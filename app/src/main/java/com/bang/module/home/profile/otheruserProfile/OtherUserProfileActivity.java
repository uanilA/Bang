package com.bang.module.home.profile.otheruserProfile;

import android.os.Bundle;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.home.profile.otheruserProfile.fragment.OtherUserProfileFragment;

public class OtherUserProfileActivity extends BangParentActivity {

    private String otherUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);
        if (getIntent().getStringExtra("OtherUserId") != null) {
            otherUserId = getIntent().getStringExtra("OtherUserId");
            addFragment(OtherUserProfileFragment.newInstance(otherUserId), false, R.id.frameOtherUserProfile);
        }
    }
}
