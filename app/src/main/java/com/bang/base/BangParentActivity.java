package com.bang.base;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.ProgressDialog;

import java.util.Objects;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

@SuppressLint("Registered")
public class BangParentActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(BangParentActivity.this);
    }

    public void hideLoader() {
        progressDialog.dismiss();
    }

    public void showLoader() {
        if (!isFinishing()) {
            progressDialog.show();
        }
    }


    public void showDialog(Context mContext, String msg){
        final Session session = new Session(mContext);
        final Dialog dialog = new Dialog(mContext);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.confirmation_dialog_view);

        TextView tvTitleOfVal =  dialog.findViewById(R.id.tvTitleOfVal);
        tvTitleOfVal.setText(msg);

        TextView tvOk =  dialog.findViewById(R.id.tvOk);
        tvOk.setOnClickListener(v -> {
            dialog.dismiss();
            session.logout();
        });
        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    // Method to replace fragment
    public void replaceFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        String backStackName = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        int i = fm.getBackStackEntryCount();
        while (i > 0) {
            fm.popBackStackImmediate();
            i--;
        }
        boolean fragmentPopped = getSupportFragmentManager().popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment, backStackName).
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    // Method to add fragment
    public void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        String backStackName = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            transaction.add(containerId, fragment, backStackName);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    public void SharingToSocialMedia(String application) {
        int bmpUri=R.drawable.img_one;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        boolean installed = checkAppInstall(application);
        if (installed) {
            intent.setPackage(application);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Installed application first", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkAppInstall(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        return false;
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    @Nullable
    public Fragment getCurrentFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            return fragmentManager.findFragmentByTag(fragmentTag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}