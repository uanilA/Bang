package com.bang.module.home.nearyou.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.AddressLocationTask;
import com.bang.helper.AppHelper;
import com.bang.helper.Constant;
import com.bang.helper.CustomToast;
import com.bang.helper.LocationRuntimePermission;
import com.bang.module.home.nearyou.adapter.FullMetalAdapter;
import com.bang.module.home.nearyou.model.NearbyUsersModel;
import com.bang.module.home.nearyou.presenter.NearByUserPresenter;
import com.bang.module.home.profile.bandrequest.model.BangRequestsModel;
import com.bang.module.home.profile.followersfollowing.FollowersActivity;
import com.bang.module.home.profile.otheruserProfile.OtherUserProfileActivity;
import com.bang.module.home.profile.otheruserProfile.manager.BangRequestPresenter;
import com.bang.module.home.profile.otheruserProfile.manager.FollowUserPresenter;
import com.bang.module.home.profile.otheruserProfile.model.FollowUnFollowResponse;
import com.bang.network.ApiCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.sandrlab.widgets.MetalRecyclerViewPager;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class NearYouFragment extends BaseFragment implements DiscreteScrollView.OnItemChangedListener,
        ApiCallback.FollowUserCallback, ApiCallback.NearByUserCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, ApiCallback.SendBangRequestCallback,
        View.OnClickListener {

    private List<NearbyUsersModel.DataBean.NearbyUserListBean> nearbyUserListBeans;
    // private DiscreteScrollView item_picker;
    // private InfiniteScrollAdapter infiniteAdapter;
    private int index;
    private int proIndex;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationManager locationManager;
    private Session session;
    private EditText edLocation;
    private TextView tvChange;
    private TextView tvUserNotFoundNear;
    private String mLatitude = "", mLongitude = "";
    private String country = "", state = "", city = "", editLocation = "";

    private MetalRecyclerViewPager viewPager;
    private FullMetalAdapter fullMetalAdapter;

    public NearYouFragment() {
        // Required empty public constructor
    }

    public static NearYouFragment newInstance() {
        NearYouFragment fragment = new NearYouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_yoy, container, false);
        init(view);
        session = new Session(mContext);
        //  item_picker.setOrientation(DSVOrientation.HORIZONTAL);
        //   item_picker.addOnItemChangedListener(this);
        tvChange.setOnClickListener(this);
        edLocation.setOnClickListener(this);

        DisplayMetrics metrics = getDisplayMetrics();
        fullMetalAdapter = new FullMetalAdapter(metrics, mContext, nearbyUserListBeans, new ClickListener.NearYouUserClick() {
            @Override
            public void NearYouFollowClick(int position) {
                index = position;
                followUserApiCall(nearbyUserListBeans.get(position).getUserId());
            }

            @Override
            public void NearFollowingListClick(int position) {
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", String.valueOf(nearbyUserListBeans.get(position).getUserId()))
                        .putExtra("follows_data", "followers"));
            }

            @Override
            public void NearYouProfileClick(int position) {
                startActivity(new Intent(mContext, OtherUserProfileActivity.class)
                        .putExtra("OtherUserId", String.valueOf(nearbyUserListBeans.get(position).getUserId())));
            }

            @Override
            public void NearYouBangRequest(int position) {
                proIndex = position;
                String connectionId = String.valueOf(nearbyUserListBeans.get(position).getUserId());
                if (String.valueOf(nearbyUserListBeans.get(position).getRequest_status()).equals("0")) {
                    System.out.println("NA");
                } else if (String.valueOf(nearbyUserListBeans.get(position).getRequest_status()).equals("1")) {
                    System.out.println("NA");
                } else {
                    updateRequestStatusCall(connectionId);
                }
            }
        });
        viewPager.setAdapter(fullMetalAdapter);

       /* infiniteAdapter = InfiniteScrollAdapter.wrap(new NearYouUserAdapter(mContext,nearbyUserListBeans, new ClickListener.NearYouUserClick() {
            @Override
            public void NearYouFollowClick(int position) {
                index = position;
                followUserApiCall(nearbyUserListBeans.get(position).getUserId());
            }

            @Override
            public void NearFollowingListClick(int position) {
                startActivity(new Intent(mContext, FollowersActivity.class)
                        .putExtra("OtherUserId", String.valueOf(nearbyUserListBeans.get(position).getUserId()))
                        .putExtra("follows_data", "followers"));
            }

            @Override
            public void NearYouProfileClick(int position) {
                startActivity(new Intent(mContext, OtherUserProfileActivity.class)
                        .putExtra("OtherUserId", String.valueOf(nearbyUserListBeans.get(position).getUserId())));
            }

            @Override
            public void NearYouBangRequest(int position) {
               proIndex = position;
               String connectionId = String.valueOf(nearbyUserListBeans.get(position).getUserId());
               if (String.valueOf(nearbyUserListBeans.get(position).getRequest_status()).equals("0")){
                   System.out.println("NA");
               }else if (String.valueOf(nearbyUserListBeans.get(position).getRequest_status()).equals("1")){
                   System.out.println("NA");
               }else {
                   updateRequestStatusCall(connectionId);
               }
            }
        }));*/
        //   item_picker.setAdapter(infiniteAdapter);
        //   item_picker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        ////  item_picker.setItemTransformer(new ScaleTransformer.Builder()
        //          .setMinScale(0.8f)
        //          .build());
        //onItemChanged(nearbyUserListBeans.get(0));
        return view;
    }


    private DisplayMetrics getDisplayMetrics() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics;
    }

    private void updateRequestStatusCall(String connectionId) {
        new BangRequestPresenter(this, mContext).callBangRequest(connectionId);
    }

    private void followUserApiCall(int userId) {
        new FollowUserPresenter(this, mContext).callFollowApi(String.valueOf(userId));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get Current Latitude and Longitude
        callToGetCurrentLocation();
    }

    private void apiCalling(String latitude, String longitude) {
        new NearByUserPresenter(this, mContext).nearByUserCall(latitude, longitude, 0, "");
    }

    private void init(View view) {
        nearbyUserListBeans = new ArrayList<>();
        nearbyUserListBeans.clear();
        viewPager = view.findViewById(R.id.viewPager);
        tvUserNotFoundNear = view.findViewById(R.id.tvUserNotFoundNear);
        edLocation = view.findViewById(R.id.edLocation);
        // item_picker = view.findViewById(R.id.item_picker);
        tvChange = view.findViewById(R.id.tvChange);
    }

   /* private void onItemChanged(NearbyUsersModel.DataBean.NearbyUserListBean item) {
        changeRateButtonState(item);
    }*/

   /* private void changeRateButtonState(NearbyUsersModel.DataBean.NearbyUserListBean item) {
        if (NearbyUsersModel.DataBean.NearbyUserListBean.isRated(item.getId())) {
            rateItemButton.setImageResource(R.drawable.ic_star_black_24dp);
          //  rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopRatedStar));
        } else {
          //  rateItemButton.setImageResource(R.drawable.ic_star_border_black_24dp);
           // rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopSecondary));
        }
    }*/

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        //int positionInDataSet = infiniteAdapter.getRealPosition(position);
        //onItemChanged(nearbyUserListBeans.get(positionInDataSet));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSuccessNearUsers(NearbyUsersModel nearbyUsersModel) {
        nearbyUserListBeans.clear();
        if (nearbyUsersModel.getData().getNearby_user_list().size() > 0) {
            tvUserNotFoundNear.setVisibility(View.GONE);
            nearbyUserListBeans.addAll(nearbyUsersModel.getData().getNearby_user_list());
            //fullMetalAdapter.notifyDataSetChanged();
        } else {
            tvUserNotFoundNear.setVisibility(View.VISIBLE);
        }
        fullMetalAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccessFollowResponse(FollowUnFollowResponse followUnFollowResponse) {
        int count = followUnFollowResponse.getTotal_followers();
        if (followUnFollowResponse.getMessage().equals("Follow successfully")) {
            nearbyUserListBeans.get(index).setIs_follow(followUnFollowResponse.getIs_following());
            nearbyUserListBeans.get(index).setTotal_followers(count);
        } else if (followUnFollowResponse.getMessage().equals("Unfollow successfully")) {
            nearbyUserListBeans.get(index).setIs_follow(followUnFollowResponse.getIs_following());
            nearbyUserListBeans.get(index).setTotal_followers(count);
        }
        //  infiniteAdapter.notifyDataSetChanged();
        fullMetalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBangRequestSuccess(BangRequestsModel bangRequestsModel) {
        nearbyUserListBeans.get(proIndex).setRequest_status("0");
        //  infiniteAdapter.notifyDataSetChanged();
        fullMetalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext, errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        activity.showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        activity.hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

    // Get Current Latitude and Longitude
    private void callToGetCurrentLocation() {
        // Get Latitude and Longitude
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
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
                .isGooglePlayServicesAvailable(mContext);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
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
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
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
            } else {
                displayCurrentLocation();
            }
        }
    }


    // Display current location using Fused Location Api
    public synchronized void displayCurrentLocation() {
        // Runtime Location Permission
        if (LocationRuntimePermission.checkLocationPermission(mContext)) {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (mLastLocation != null) {
                    mLatitude = String.valueOf(mLastLocation.getLatitude());
                    mLongitude = String.valueOf(mLastLocation.getLongitude());
                    // LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    System.out.println("&&&&&&&&&&&&&&&&&" + mLatitude + "\n" + mLongitude);
                    session.setFilterCity(Double.toString(mLastLocation.getLatitude()), Double.toString(mLastLocation.getLongitude()));
                    apiCalling(mLatitude, mLongitude);
                    getCurrentAddress(mContext, Double.parseDouble(mLatitude), Double.parseDouble(mLongitude));
                }
            } else {
                CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.enable_gps));
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Autocomplete Place Api
        if (requestCode == Constant.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == -1) {
                Place place = PlaceAutocomplete.getPlace(mContext, data);
                // Parse Country, State and City from entered address
                getSelectAddress(place);
            }
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
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }


    public void getCurrentAddress(Context context, double LATITUDE, double LONGITUDE) {
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                //String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                edLocation.setText(address);
                Log.d("", "getCurrentAddress:  address" + address);
                Log.d("", "getCurrentAddress:  city" + city);
                Log.d("", "getCurrentAddress:  state" + state);
                Log.d("", "getCurrentAddress:  postalCode" + postalCode);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvChange:
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build((activity));
                    startActivityForResult(intent, Constant.PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.edLocation:
                Intent intent1 = null;
                try {
                    intent1 = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build((activity));
                    startActivityForResult(intent1, Constant.PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    // Parse Country, State and City from entered address
    private void getSelectAddress(final Place place) {
        if (AppHelper.isConnectingToInternet(mContext)) {
            new AddressLocationTask(mContext, place, new AddressLocationTask.AddressLocationListner() {
                @Override
                public void getLocation(String cty, String st, String cntry, String locAddress) {
                    city = cty;
                    state = st;
                    country = cntry;

                    if (!country.equals("") && !state.equals("") && !city.equals("")) {
                        editLocation = city + ", " + state + ", " + country;
                    } else if (!country.equals("") && !state.equals("")) {
                        editLocation = state + ", " + country;
                    } else if (!country.equals("")) {
                        editLocation = country;
                    }
                    if (place.getLatLng() != null) {
                        mLatitude = "" + place.getLatLng().latitude;
                        mLongitude = "" + place.getLatLng().longitude;
                        nearbyUserListBeans.clear();
                        apiCalling(mLatitude, mLongitude);
                    }
                    edLocation.setText(editLocation);
                }
            }).execute();
        }
    }

}
