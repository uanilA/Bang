package com.bang.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static Date date;
    public static SimpleDateFormat format;

    public static void setTypeface(TextView tv, Context context, int fontres) {
        tv.setTypeface(ResourcesCompat.getFont(context, fontres));
    }

    // Changing date format from YYYY-MM-DD to MM-DD-YYYY
    public static String dateInMDYFormat(String string) {
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(string);
            format = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(date);
    }

    // Changing date format from MM-DD-YYYY to  YYYY-MM-DD
    public static String dateInYMDFormat(String string) {
        try {
            date = new SimpleDateFormat("MM-dd-yyyy", Locale.US).parse(string);
            format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return format.format(date);
    }

   /* public static void goToOnlineStatus(Context context, String status) {
        Session session = new Session(context);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        if (session.getRegistration() != null && session.getRegistration().userDetail != null) {
            if (session.getRegistration().userDetail.userId != null && !session.getRegistration().userDetail.userId.equals("")) {
                OnlineInfo onlineInfo = new OnlineInfo();
                onlineInfo.lastOnline = status;
                onlineInfo.email = session.getRegistration().userDetail.email;
                onlineInfo.uid = session.getRegistration().userDetail.userId;

                database.child(Constant.ONLINE_TABLE)
                        .child(session.getRegistration().userDetail.userId)
                        .setValue(onlineInfo);
            }
        }
    }*/

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * for formatter text watcher
     */
    public static String removeNonnumeric(String text) {
        return text.replaceAll("[^\\d]", "");
    }

}
