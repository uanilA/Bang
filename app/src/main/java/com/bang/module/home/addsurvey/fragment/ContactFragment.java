package com.bang.module.home.addsurvey.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.AppHelper;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.bang.module.home.addsurvey.adapter.ContactListAdapter;
import com.bang.module.home.addsurvey.manager.ContactListManager;
import com.bang.module.home.addsurvey.manager.ShareSurveyPresenter;
import com.bang.module.home.addsurvey.model.GetAllUserResponse;
import com.bang.module.home.survey.model.ContactModel;
import com.bang.network.ApiCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.bang.helper.Constant.REQUEST;


public class ContactFragment extends BaseFragment implements ApiCallback.GetAllUserCallback, View.OnClickListener {

    private RecyclerView rcvContactList;
    private ArrayList<ContactModel> storeContacts;
    private ArrayList<GetAllUserResponse.DataBean.UserListBean> userListBeans;
    private String cnctNumber = "";
    private String forUserId = "";
    private String userFullName = "";
    private ContactListAdapter contactListAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String date = "";
    private String time="";
    private long mLastClickTime = 0;


    public ContactFragment() {
    }

    public static ContactFragment newInstance(String date , String time) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, date);
        args.putString(ARG_PARAM2, time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                date = getArguments().getString(ARG_PARAM1);
                time = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        init(view);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> callAPi(), 1000 );
        storeContacts = new ArrayList<>();
        return view;
    }

    private void init(View view) {
        View main_tool_bar = ((AddSurveyActivity) mContext).findViewById(R.id.main_tool_bar);
        main_tool_bar.setVisibility(View.VISIBLE);
        ((AddSurveyActivity) mContext).findViewById(R.id.ivBack).setOnClickListener(this);
        LinearLayout llHomeMenu = ((AddSurveyActivity) mContext).findViewById(R.id.llHomeMenu);
        llHomeMenu.setVisibility(View.GONE);
        TextView tvHeaderTitle = ((AddSurveyActivity) mContext).findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(mContext.getString(R.string.choose_contact));
        rcvContactList = view.findViewById(R.id.rcvContactList);
        view.findViewById(R.id.tvContactNext).setOnClickListener(this);
        EditText etFilterField = view.findViewById(R.id.etFilterField);
        etFilterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (contactListAdapter != null) {
                    contactListAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void callAPi() {
        if (AppHelper.isConnectingToInternet(mContext)) {
            new ContactListManager(this, mContext).callAllUserList();
        } else {
            CustomToast.getInstance(mContext).showToast(mContext, getString(R.string.alert_no_network));
        }
    }

    private void setContactList() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        contactListAdapter = new ContactListAdapter((number, name) -> {
            try {
                cnctNumber = number;
                userFullName = name;
                for (int j = 0; j < userListBeans.size(); j++) {
                    if (userListBeans.get(j).getPhone_number().equals(number)) {
                        forUserId = String.valueOf(userListBeans.get(j).getUserId());
                        break;
                    }else {
                        forUserId = "0";
                    }
                }
                final Handler handler = new Handler();
                handler.postDelayed(() -> ((BangParentActivity) mContext).replaceFragment(SelectingGenderFragment.newInstance(date,time,forUserId,userFullName), false, R.id.frameAddSurvey), 200);
            }catch (Exception e){e.printStackTrace();}
        }, storeContacts, mContext, userListBeans);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rcvContactList.setLayoutManager(mLayoutManager);
        rcvContactList.setAdapter(contactListAdapter);
    }

    /**
     * Contact access code from mobile 03-06-19
     */
     List<ContactModel> getContacts(Context ctx) {
        ContentResolver contentResolver = ctx.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        assert cursor != null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(),
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id)));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    Bitmap photo = null;
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream);
                    }
                    assert cursorInfo != null;
                    while (cursorInfo.moveToNext()) {
                        ContactModel info = new ContactModel();
                        info.id = id;
                        info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        info.mobileNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        info.photo = photo;
                        info.photoURI = pURI;
                        storeContacts.add(info);
                    }
                    setContactList();
                    cursorInfo.close();
                }
            }
            cursor.close();
        }
        return storeContacts;
    }




    @Override
    public void onSuccessAllUsers(GetAllUserResponse getAllUserResponse) {
        userListBeans = new ArrayList<>();
        userListBeans.addAll(getAllUserResponse.getData().getUserList());
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.READ_CONTACTS};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
            } else {
                getContacts(mContext);
            }
        } else {
            getContacts(mContext);
        }
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext,errorMessage);
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.ivBack:
                ((AddSurveyActivity) mContext).onBackPressed();
                break;
            case R.id.tvContactNext:
                if (cnctNumber.equals("")) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Please select contact first");
                } else {
                    ((BangParentActivity) mContext).replaceFragment(SelectingGenderFragment.newInstance(date,time,forUserId,userFullName), false, R.id.frameAddSurvey);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts(mContext);
            } else {
                Toast.makeText(mContext, "The app was not allowed to read your contact", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AddSurveyActivity) mContext).main_tool_bar.setVisibility(View.VISIBLE);
    }
}