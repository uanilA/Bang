package com.bang.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bang.R;
import com.bang.helper.Utils;
import com.bang.module.authentication.country.model.Country;
import com.google.gson.Gson;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AppUtils {

    public AppUtils() {
        // This utility class is not publicly instantiable
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static RequestBody createPartFromString(String data) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, data);
    }

    public static MultipartBody.Part prepareFilePart(Context context, String partName, Uri uri) {
        File file = new File(getRealPathFromURI(context, uri));
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(uri)),
                        file);
        return MultipartBody.Part.createFormData(partName, "Image.jpg", requestFile);
    }

     private static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static boolean RequestMultiplePermissionCamera(Activity activity) {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (FirstPermissionResult != PackageManager.PERMISSION_GRANTED | SecondPermissionResult != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            // Creating String Array with Permissions.
            ActivityCompat.requestPermissions(activity, new String[]
                    {
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                    }, 3);
            return false;
        } else {
            return true;
        }
    }
    public static String fromDate(String fdate) {
        String datetime = null;
        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat d = new SimpleDateFormat("MMM dd, yyyy");
        Date convertedDate = null;
        try {
            convertedDate = inputFormat.parse(fdate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        datetime = d.format(convertedDate);

        return datetime;
    }
}
