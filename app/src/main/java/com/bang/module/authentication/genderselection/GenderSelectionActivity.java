package com.bang.module.authentication.genderselection;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BangParentActivity;
import com.bang.helper.Constant;
import com.bang.helper.CustomToast;
import com.bang.helper.LocationRuntimePermission;
import com.bang.module.authentication.genderselection.manager.GenderManager;
import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.authentication.genderselection.model.UpdateLocationResponse;
import com.bang.module.home.MainActivity;
import com.bang.module.preference.PreferenceActivity;
import com.bang.network.ApiCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GenderSelectionActivity extends BangParentActivity implements View.OnClickListener,
        ApiCallback.GenderSelectionCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private RelativeLayout rlMalePrefers, rlFemalePrefers, rlTransgenderMalePrefers, rlTransgenderFemalePrefersMalePrefers, rlNonGenderPrefers;

    private ImageView ivNonGenderSelected, ivTransGenderFamaleSelected, ivTransGenderMaleSelected, ivFemaleSelected, ivMaleSelected;

    private TextView tvNonGenderPrefer, tvTransgenderFemalePrefer, tvTransgenderMalePrefer, tvFemalePrefer, tvMalePrefer;

    private LinearLayout llMaleGender, llFemaleGender, llTransgenderMale, llTransgemderFemale, llNonGender;

    private TextView tvNotGender, tvTransgenderFemale, txtTransGenderMale, tvFemaleGender, tvMaleGender;

    private TextView tvGenderSelectionSkip, tvGenderSelectionNext;
    private String preference = "", myGender = "";
    private Session session;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationManager locationManager;
   // private long mLastClickTime = 0;
    private String mLatitude = "", mLongitude = "", full_address = "", city = "", getFull_address = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);
        init();
        session = new Session(GenderSelectionActivity.this);
        rlMalePrefers.setOnClickListener(this);
        rlFemalePrefers.setOnClickListener(this);
        rlTransgenderMalePrefers.setOnClickListener(this);
        rlTransgenderFemalePrefersMalePrefers.setOnClickListener(this);
        rlNonGenderPrefers.setOnClickListener(this);

        llMaleGender.setOnClickListener(this);
        llFemaleGender.setOnClickListener(this);
        llTransgenderMale.setOnClickListener(this);
        llTransgemderFemale.setOnClickListener(this);
        llNonGender.setOnClickListener(this);
        tvGenderSelectionSkip.setOnClickListener(this);
        tvGenderSelectionNext.setOnClickListener(this);

        // Get Current Latitude and Longitude
        callToGetCurrentLocation();
    }

    private void callUpdateLocationAPi() {
        new GenderManager(this, GenderSelectionActivity.this).callUpdateLocationApi(mLatitude, mLongitude);
    }


    private void init() {
        rlMalePrefers = findViewById(R.id.rlMalePrefers);
        rlFemalePrefers = findViewById(R.id.rlFemalePrefers);
        rlTransgenderMalePrefers = findViewById(R.id.rlTransgenderMalePrefers);
        rlTransgenderFemalePrefersMalePrefers = findViewById(R.id.rlTransgenderFemalePrefersMalePrefers);
        rlNonGenderPrefers = findViewById(R.id.rlNonGenderPrefers);

        ivTransGenderFamaleSelected = findViewById(R.id.ivTransGenderFamaleSelected);
        ivNonGenderSelected = findViewById(R.id.ivNonGenderSelected);
        ivTransGenderMaleSelected = findViewById(R.id.ivTransGenderMaleSelected);
        ivFemaleSelected = findViewById(R.id.ivFemaleSelected);
        ivMaleSelected = findViewById(R.id.ivMaleSelected);

        tvNonGenderPrefer = findViewById(R.id.tvNonGenderPrefer);
        tvTransgenderFemalePrefer = findViewById(R.id.tvTransgenderFemalePrefer);
        tvTransgenderMalePrefer = findViewById(R.id.tvTransgenderMalePrefer);
        tvFemalePrefer = findViewById(R.id.tvFemalePrefer);
        tvMalePrefer = findViewById(R.id.tvMalePrefer);

        llMaleGender = findViewById(R.id.llMaleGender);
        llFemaleGender = findViewById(R.id.llFemaleGender);
        llTransgenderMale = findViewById(R.id.llTransgenderMale);
        llTransgemderFemale = findViewById(R.id.llTransgemderFemale);
        llNonGender = findViewById(R.id.llNonGender);

        tvNotGender = findViewById(R.id.tvNotGender);
        tvTransgenderFemale = findViewById(R.id.tvTransgenderFemale);
        txtTransGenderMale = findViewById(R.id.txtTransGenderMale);
        tvFemaleGender = findViewById(R.id.tvFemaleGender);
        tvMaleGender = findViewById(R.id.tvMaleGender);
        tvGenderSelectionSkip = findViewById(R.id.tvGenderSelectionSkip);
        tvGenderSelectionNext = findViewById(R.id.tvGenderSelectionNext);
    }

    @Override
    public void onClick(View v) {
       /* if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();*/
        switch (v.getId()) {
            case R.id.rlMalePrefers:
                selectMalePrefer();
                break;
            case R.id.rlFemalePrefers:
                selectFemalePrefer();
                break;
            case R.id.rlTransgenderMalePrefers:
                selectTransgenderMalePrefer();
                break;
            case R.id.rlTransgenderFemalePrefersMalePrefers:
                selectTransgenderFemalePrefer();
                break;
            case R.id.rlNonGenderPrefers:
                selectNonGenderPrefer();
                break;
            case R.id.llMaleGender:
                selectedMaleGender();
                break;
            case R.id.llFemaleGender:
                selectedFemaleGender();
                break;
            case R.id.llTransgenderMale:
                selectedTransgenderMaleGender();
                break;
            case R.id.llTransgemderFemale:
                selectedTransgenderFemaleGender();
                break;
            case R.id.llNonGender:
                selectedNonGender();
                break;
            case R.id.tvGenderSelectionSkip:
                startActivity(new Intent(GenderSelectionActivity.this, MainActivity.class));
                break;
            case R.id.tvGenderSelectionNext:
                if (myGender.equals("")) {
                    CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this, "Please select Preference");
                } else if (preference.equals("")) {
                    CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this, "Please select your gender");
                } else {
                    new GenderManager(this, GenderSelectionActivity.this).callUpdateGenderApi(myGender, preference);

                }
                break;
        }
    }


    private void selectMalePrefer() {

        myGender = "0";

        rlMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.VISIBLE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectFemalePrefer() {

        myGender = "1";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.VISIBLE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectTransgenderMalePrefer() {

        myGender = "2";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.VISIBLE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectTransgenderFemalePrefer() {

        myGender = "3";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.VISIBLE);
        ivNonGenderSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectNonGenderPrefer() {

        myGender = "4";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.gender_selected_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.VISIBLE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorBang));
    }

    private void selectedMaleGender() {

        preference = "0";

        llMaleGender.setBackgroundResource(R.drawable.doted_background_corners);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorBang));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));

    }

    private void selectedFemaleGender() {

        preference = "1";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.doted_background_corners);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorBang));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedTransgenderMaleGender() {

        preference = "2";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.doted_background_corners);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedTransgenderFemaleGender() {

        preference = "3";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.doted_background_corners);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorBang));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedNonGender() {

        preference = "4";
        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.doted_background_corners);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorBang));
    }

    @Override
    public void onGenderSelectionResponse(UpdateGenderResponse updateGenderResponse) {
        session.createRegistration(updateGenderResponse.getData());
        session.setUserLoggedIn();
        startActivity(new Intent(GenderSelectionActivity.this, PreferenceActivity.class)
                .putExtra("prefer_gender", myGender)
                .putExtra("preference_key", "notSelectedPref"));
        finish();
    }

    @Override
    public void onUpdateLocationResponse(UpdateLocationResponse updateLocationResponse) {
       // CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this, updateLocationResponse.getMessage());
    }


    @Override
    public void onTokenChangeError(String errorMessage) {
       showDialog(GenderSelectionActivity.this,errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this, errorMessage);
    }


    // Get Current Latitude and Longitude
    private void callToGetCurrentLocation() {
        // Get Latitude and Longitude
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
            createLocationRequest();
        }
        displayCurrentLocation();
    }


    /**
     * Method to verify google play services on the device
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(GenderSelectionActivity.this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, GenderSelectionActivity.this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    /**
     * Creating google api client object
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(GenderSelectionActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Creating location request object
     */
    protected void createLocationRequest() {
        int UPDATE_INTERVAL = 10000;
        int FASTEST_INTERVAL = 5000;
        int DISPLACEMENT = 10;
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPlayServices();
        // Resuming the periodic location updates
    }


    /**
     * Get Current L0cation
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayCurrentLocation();
            } /*else {
              //  displayCurrentLocation();
            }*/
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Assign the new location
        mLastLocation = location;
        displayCurrentLocation();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayCurrentLocation();
       // boolean mRequestingLocationUpdates = false;
      /*  if (mRequestingLocationUpdates) {
            // startLocationUpdates();
        }*/
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // Display current location using Fused Location Api
    synchronized private void displayCurrentLocation() {
        // Runtime Location Permission
        if (LocationRuntimePermission.checkLocationPermission(GenderSelectionActivity.this)) {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (mLastLocation != null) {
                    mLatitude = String.valueOf(mLastLocation.getLatitude());
                    mLongitude = String.valueOf(mLastLocation.getLongitude());
                   // LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    System.out.println("&&&&&&&&&&&&&&&&&" + mLatitude + "\n" + mLongitude);
                    session.setFilterCity(Double.toString(mLastLocation.getLatitude()), Double.toString(mLastLocation.getLongitude()));
                    callUpdateLocationAPi();
                }
            } else {
                if (session.getUserGetRegistered()) {
                    session.setUserGetRegistered(false);
                    CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this, getString(R.string.enable_gps));
                }
            }
        }
    }

}
