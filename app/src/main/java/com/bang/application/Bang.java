package com.bang.application;

import android.app.Application;

import com.bang.module.home.nearyou.DiscreteScrollViewOptions;
import com.google.firebase.FirebaseApp;


public class Bang extends Application implements LifeCycleDelegateListner {
    public static final String TAG = Bang.class.getSimpleName();
    //  private RequestQueue mRequestQueue;
    private static Bang mInstance;
    public static Bang getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseApp.initializeApp(this);
        DiscreteScrollViewOptions.init(this);
    }


    /* @Override
     public void onCreate() {
         super.onCreate();
        *//* Fabric.with(this, new Crashlytics());
        mInstance = this;
        FirebaseApp.initializeApp(getApplicationContext());

        Session session = new Session(getApplicationContext());
        session.setFilterUserName("");
        session.setFilterInterests("");
        session.setFilterCity("", "");
        session.setFilterPlaceInterest("", "");
        session.setFilterAgeDistance("18", "100", "0", "300");
        session.setGenderIntentBodyEthnicity("", "", "");
        session.setFilterSortBy("");
        session.setFilterRandomIndex("");

        AppLifeCycle appLifeCycle = new AppLifeCycle(this);
        registerLifeCycle(appLifeCycle);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

    public void cancelAllRequests(String TAG) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }




    public void registerLifeCycle(AppLifeCycle appLifeCycle) {
        registerActivityLifecycleCallbacks(appLifeCycle);
        registerComponentCallbacks(appLifeCycle);
    }

    private Activity activeActivity;

    public Activity getActiveActivity() {
        return activeActivity;
    }

    private void setupActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                activeActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                activeActivity = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });*//*


    }*/
    @Override
    public void onAppBackgrounded() {
        //Utils.goToOnlineStatus(getApplicationContext(), Constant.OFFLINE_STATUS);
    }

    @Override
    public void onAppForegrounded() {
        // Utils.goToOnlineStatus(getApplicationContext(), Constant.ONLINE_STATUS);
    }
}