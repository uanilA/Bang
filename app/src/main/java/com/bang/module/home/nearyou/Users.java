package com.bang.module.home.nearyou;

import android.content.Context;
import android.content.SharedPreferences;
import com.bang.R;
import com.bang.application.Bang;
import com.bang.module.home.nearyou.model.Item;
import com.bang.module.home.nearyou.model.NearbyUsersModel;


import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Users {

    private static final String STORAGE = "shop";

    public static Users get() {
        return new Users();
    }

    private SharedPreferences storage;

    private Users() {
        storage = Bang.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }


    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
