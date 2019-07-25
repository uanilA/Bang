package com.bang.base;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.Constant;
import com.bang.helper.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BaseFragment extends Fragment {
   public Context mContext;
   public BangParentActivity activity;
   @SuppressLint("StaticFieldLeak")

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
        if (context instanceof BangParentActivity){
            activity = (BangParentActivity)context;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public static String numberformate(String myNumber){
        String numericString = Utils.removeNonnumeric(myNumber);
        int stringLength = numericString.length();
        boolean startsWithOne = numericString.startsWith("1");
        numericString = numericString.substring(0, Math.min(stringLength, 10 + (startsWithOne ? 1 : 0)));
        int lastHyphenIndex = 6 + (startsWithOne ? 1 : 0);
        int secondToLastHyphenIndex = 3 + (startsWithOne ? 1 : 0);

        if (stringLength >= lastHyphenIndex) {
            numericString = numericString.substring(0, lastHyphenIndex) + "-" + numericString.substring(lastHyphenIndex, numericString.length());
        }
        if (stringLength >= secondToLastHyphenIndex) {
            numericString = numericString.substring(0, secondToLastHyphenIndex) + "-" + numericString.substring(secondToLastHyphenIndex, numericString.length());
        }
        if (numericString.startsWith("1")) {
            numericString = numericString.substring(0, 1) + "-" + numericString.substring(1, numericString.length());
        }
        return numericString;
    }





    public void showSetIntroVideoDialog(final Context context) {

        final CharSequence[] options = {"Take Video", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Video");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take Video")) {

                    Intent intentCaptureVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if (intentCaptureVideo.resolveActivity(context.getPackageManager()) != null) {
                        long maxVideoSize = 12*1024*1024; // 12 MB
                        intentCaptureVideo.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);//120 sec = 2min  //10000sec //10 min
                        intentCaptureVideo.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                        intentCaptureVideo.putExtra(MediaStore.EXTRA_SIZE_LIMIT, maxVideoSize);
                        startActivityForResult(intentCaptureVideo, Constant.REQUEST_VIDEO_CAPTURE);
                    }
                } else if (options[which].equals("Choose from Gallery")) {
                    Intent intent;
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    } else {
                        intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
                    }
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, Constant.SELECT_VIDEO_REQUEST);
                }
            }
        });
        builder.show();
    }


    // from bitmap to file creater"""""""""""
    public File savebitmap(Context mContext, Bitmap bitmap, String name) {
        File filesDir = mContext.getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, name);

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
            Log.e(mContext.getClass().getSimpleName(), "Error writing bitmap", e);
        }
        return null;
    }

}
