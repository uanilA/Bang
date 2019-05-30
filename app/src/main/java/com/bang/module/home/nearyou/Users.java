package com.bang.module.home.nearyou;

import android.content.Context;
import android.content.SharedPreferences;
import com.bang.R;
import com.bang.application.Bang;
import com.bang.module.home.nearyou.model.Item;


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

    public List<Item> getData() {
        return Arrays.asList(
                new Item(1, "Everyday Candle", "5","121" ,R.drawable.img_one),
                new Item(2, "Small Porcelain Bowl", "10","432", R.drawable.img_two),
                new Item(3, "Favourite Board", "2","12", R.drawable.img_three),
                new Item(4, "Earthenware Bowl", "11","876", R.drawable.img_four),
                new Item(5, "Porcelain Dessert Plate", "100","56", R.drawable.img_five),
                new Item(6, "Detailed Rolling Pin", "54","987", R.drawable.img_six));
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
